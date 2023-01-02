package metropolis.xtracted.controller.masterDetail

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import metropolis.cities.editor.controller.cityEditorController
import metropolis.cities.explorer.controller.cityLazyTableController
import metropolis.cities.shared.data.City
import metropolis.countries.shared.data.Country
import metropolis.xtracted.controller.ControllerBase
import metropolis.xtracted.controller.editor.EditorController
import metropolis.xtracted.controller.lazyloading.LazyTableAction
import metropolis.xtracted.controller.lazyloading.LazyTableController
import metropolis.xtracted.model.MasterDetailState
import metropolis.xtracted.repository.CrudRepository
import metropolis.xtracted.repository.Identifiable
import metropolis.xtracted.repository.LazyRepository

class MasterDetailController<D: Identifiable>(
    var title : String,
    private var selectedId : Int?,
    private var initialLazyTableController: LazyTableController<D>,
    private var initialEditorController: EditorController<D>) :

    ControllerBase<MasterDetailState<D>, MasterDetailAction>(
        initialState = MasterDetailState(
            title = title,
            selectedId = selectedId!!,
            lazyTableController = initialLazyTableController,
            editorController = initialEditorController)) {

    init {
        setSelectedInExplorer()
        // was not automatically selected in explorer on initialization
    }

    override fun executeAction(action: MasterDetailAction) : MasterDetailState<D> = when (action) {
        is MasterDetailAction.SetSelected -> setSelectedInExplorer()
        is MasterDetailAction.Open<*> -> showElementInEditor(action.id, action.editor as EditorController<D>)
        is MasterDetailAction.Reload<*> -> reloadTable(action.explorer as LazyTableController<D>)
        is MasterDetailAction.Add -> add()
        is MasterDetailAction.Delete -> delete(action.id)
    }

    // New item not seen in table, depending on table sort. Feature: set filter for this item
    private fun add() : MasterDetailState<D> {
        val newId = state.editorController.repository.createKey()
        // TODO: does not properly reload the table
        // -> new table instance would need to come from Cities/CountriesModuleController
        reloadTable(lazyTableController = initialLazyTableController)
        showElementInEditor(newId, initialEditorController) // this should be a new instance from above...
        setSelectedInExplorer()
        // doesn't work (yet) from this Controller:
        // state.lazyTableController.executeAction(LazyTableAction.SetFilter(column = ))
        return state
    }

    private fun delete(id : Int) : MasterDetailState<D> {
        val repo = state.editorController.repository
        repo.delete(id)
        reloadTable(lazyTableController = initialLazyTableController)
        state.lazyTableController.triggerAction(LazyTableAction.SelectNext)
        // TODO: does not properly reload the table
        // -> new table instance would need to come from Cities/CountriesModuleController
        return state
    }

    private fun setSelectedInExplorer() : MasterDetailState<D> {
        state.lazyTableController.triggerAction(LazyTableAction.Select(selectedId!!))
        return state
    }
    private fun showElementInEditor(id: Int, editorController: EditorController<D>) : MasterDetailState<D> {
        selectedId = id
        state = state.copy(selectedId = id, editorController = editorController)
        return state
    }
    private fun reloadTable(lazyTableController: LazyTableController<D>) : MasterDetailState<D> {
        state = state.copy(lazyTableController = lazyTableController)
        return state
    }

    // Cannot be a MasterDetailAction because it must be a Composable. Therefore, public.
    @Composable
    fun initializeUiScopes() {
        state.editorController.initializeUiScope(rememberCoroutineScope())
        state.lazyTableController.initializeUiScope(rememberCoroutineScope())
    }
}

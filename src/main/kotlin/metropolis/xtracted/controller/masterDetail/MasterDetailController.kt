package metropolis.xtracted.controller.masterDetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import metropolis.cities.editor.controller.cityEditorController
import metropolis.cities.explorer.controller.cityLazyTableController
import metropolis.cities.shared.data.City
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
    private var selectedId : Int,
    private var initialLazyTableController: LazyTableController<D>,
    private var initialEditorController: EditorController<D>) :

    ControllerBase<MasterDetailState<D>, MasterDetailAction>(
        initialState = MasterDetailState(
            title = title,
            selectedId = selectedId,
            lazyTableController = initialLazyTableController,
            editorController = initialEditorController)) {

    override fun executeAction(action: MasterDetailAction) : MasterDetailState<D> = when (action) {
        // TODO make generic
        // is MasterDetailAction.Open -> showElementInEditor(action.id, action.editor)
        //is MasterDetailAction.Reload -> reloadTable(action.explorer)
        is MasterDetailAction.Add -> add()
        is MasterDetailAction.Delete -> delete(action.id)
    }

    // TODO New item not seen in table as long as not saved
    private fun add() : MasterDetailState<D> {
        val newId = state.editorController.repository.createKey()
        reloadTable(lazyTableController = initialLazyTableController)
        showElementInEditor(newId, initialEditorController)
        setSelectedInExplorer()
        //val newState =  state.copy(selectedId = newId, lazyTableController = state.lazyTableController, editorController = state.editorController)
        return state
    }

    private fun delete(id : Int) : MasterDetailState<D> {
        val repo = state.editorController.repository
        repo.delete(id)
        //val newId = if(repo.read(id+1) != null) id+1 else 0
        //selectedId = newId
        state.lazyTableController.triggerAction(LazyTableAction.SelectNext)
        selectedId = state.lazyTableController.state.selectedId!!
        reloadTable(lazyTableController = initialLazyTableController)
        //setSelectedInExplorer()
        return state
    }

    fun setSelectedInExplorer(){  // TODO
        state.lazyTableController.triggerAction(LazyTableAction.Select(selectedId))
    }
    fun showElementInEditor(id: Int, editorController: EditorController<D>) {
        selectedId = id
        state = state.copy(selectedId = id, editorController = editorController)
    }
    fun reloadTable(lazyTableController: LazyTableController<D>) {
        println("reloadTable executed")
        state = state.copy(lazyTableController = lazyTableController)
    }

    @Composable
    fun initializeUiScopes(){
        state.editorController.initializeUiScope(rememberCoroutineScope())
        state.lazyTableController.initializeUiScope(rememberCoroutineScope())
    }
}

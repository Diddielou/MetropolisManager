package metropolis.xtracted.controller.masterDetail

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

    override fun executeAction(action: MasterDetailAction) : MasterDetailState<D> = when (action) {
        // TODO make generic
        is MasterDetailAction.Open<*> -> showElementInEditor(action.id, action.editor as EditorController<D>)
        is MasterDetailAction.Reload<*> -> reloadTable(action.explorer as LazyTableController<D>)
        is MasterDetailAction.Add -> add()
        is MasterDetailAction.Delete -> delete(action.id)
    }

    // TODO New item not seen in table as long as not saved
    private fun add() : MasterDetailState<D> {

        val newId = state.editorController.repository.createKey()

        // if a CityEditor and an item is selected, take the country code of the selected for creation
        /*
        Metropolis:
        if(state.editorController.repository.table == "CITY"){ // TODO: unschön, da table nicht mehr private

        }
        Expenses:
        if(selectedExpense != null){
                newExpense = repository.create(initialExpense.copy(createDate = selectedExpense!!.createDate))
                val index = state.allExpenses.indexOf(selectedExpense)
                allExpenses.add(index, newExpense)
            } else {
                newExpense = repository.create(initialExpense)
                allExpenses.add(0, newExpense)
            }
         */
        state = reloadTable(lazyTableController = initialLazyTableController)
        state = showElementInEditor(newId, initialEditorController)
        state =  state.copy(selectedId = newId, lazyTableController = state.lazyTableController, editorController = state.editorController)
        setSelectedInExplorer()
        return state
    }

    private fun delete(id : Int) : MasterDetailState<D> {
        val repo = state.editorController.repository
        repo.delete(id)
        //val newId = if(repo.read(id+1) != null) id+1 else 0
        //selectedId = newId
        state.lazyTableController.triggerAction(LazyTableAction.SelectNext)
        selectedId = state.lazyTableController.state.selectedId!!
        return reloadTable(lazyTableController = initialLazyTableController)
        //setSelectedInExplorer()
    }

    fun setSelectedInExplorer(){  // TODO
        state.lazyTableController.triggerAction(LazyTableAction.Select(selectedId!!))
    }
    fun showElementInEditor(id: Int, editorController: EditorController<D>) : MasterDetailState<D> {
        selectedId = id
        println("showElementInEditor() executed")
        state = state.copy(selectedId = id, editorController = editorController)
        return state
    }
    fun reloadTable(lazyTableController: LazyTableController<D>) : MasterDetailState<D> {
        println("reloadTable() executed")
        state = state.copy(lazyTableController = lazyTableController)
        return state
    }

    @Composable
    fun initializeUiScopes(){
        state.editorController.initializeUiScope(rememberCoroutineScope())
        state.lazyTableController.initializeUiScope(rememberCoroutineScope())
    }
}

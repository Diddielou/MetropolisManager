package metropolis.xtracted.controller.masterDetail

import androidx.compose.runtime.rememberCoroutineScope
import metropolis.cities.editor.controller.cityEditorController
import metropolis.cities.explorer.controller.cityLazyTableController
import metropolis.xtracted.controller.ControllerBase
import metropolis.xtracted.controller.editor.EditorController
import metropolis.xtracted.controller.lazyloading.LazyTableAction
import metropolis.xtracted.controller.lazyloading.LazyTableController
import metropolis.xtracted.model.MasterDetailState
import metropolis.xtracted.repository.Identifiable

class MasterDetailController<D: Identifiable>(
    var title : String,
    private var selectedCityId : Int,
    private val lazyTableController: LazyTableController<D>,
    private val editorController: EditorController<D>) :

    ControllerBase<MasterDetailState<D>, MasterDetailAction>(
        initialState = MasterDetailState(
            title = title,
            lazyTableController = lazyTableController,
            editorController = editorController)) {
    override fun executeAction(action: MasterDetailAction): MasterDetailState<D> {
        TODO("Not yet implemented")
    }

    // TODO should be Action
    private fun showElementInEditor(id: Int){
        selectedCityId = id
        state = state.copy(editorController = editorController)
    }

    private fun reloadTable(){
        println("reloadTable executed")
        state = state.copy(lazyTableController = lazyTableController)
        setSelectedCityId()
    }

    private fun setSelectedCityId(){
        state.lazyTableController.triggerAction(LazyTableAction.Select(selectedCityId))
    }

    private fun initializeUiScopes(){
        state.editorController.initializeUiScope(rememberCoroutineScope())
        state.lazyTableController.initializeUiScope(rememberCoroutineScope())
    }
}

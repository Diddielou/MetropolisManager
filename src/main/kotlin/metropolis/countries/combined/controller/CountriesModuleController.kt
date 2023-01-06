package metropolis.countries.combined.controller

import metropolis.countries.editor.controller.countryEditorController
import metropolis.countries.explorer.controller.countryLazyTableController
import metropolis.countries.shared.data.Country
import metropolis.xtracted.controller.masterDetail.MasterDetailAction
import metropolis.xtracted.controller.masterDetail.MasterDetailController
import metropolis.xtracted.repository.CrudRepository
import metropolis.xtracted.repository.LazyRepository

class CountriesModuleController (
    private val initiallySelectedCountryId : Int,
    private val lazyRepository    : LazyRepository<Country>,
    private val crudRepository    : CrudRepository<Country>,
    private val onCountrySelection: (Country?) -> Unit){

    val controller = MasterDetailController(
        title                 = "Countries",
        selectedId            = initiallySelectedCountryId,
        onNewTableController  = { createNewCountryExplorer() },
        onNewEditorController = { createNewCountryEditor(it) }
    )

    private fun createNewCountryExplorer() =
        countryLazyTableController(lazyRepository, onSelectionChange = { showCountryInEditor(it) })

    private fun createNewCountryEditor(id: Int) =
        countryEditorController(id, crudRepository, onEditorAction = { reloadCountryExplorer() })

    private fun showCountryInEditor(id: Int){
        val selectedId = controller.state.selectedId
        //println("showCountryInEditor() | MasterDetailAction.Open:")
        controller.executeAction(MasterDetailAction.Open(id = id, editor = createNewCountryEditor(id = id)))
        if(selectedId != id){
            val country = controller.state.lazyTableController.getData(id)
            println("showCountryInEditor() | onCountrySelection: ${country.name}")
            onCountrySelection(country)
        }
    }
    private fun reloadCountryExplorer(){
        controller.executeAction(MasterDetailAction.Reload(explorer = createNewCountryExplorer()))
        controller.executeAction(MasterDetailAction.SetSelected(null))
    }

}
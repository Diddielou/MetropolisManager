package metropolis.cities.combined.controller

import metropolis.cities.editor.controller.cityEditorController
import metropolis.cities.explorer.controller.cityLazyTableController
import metropolis.cities.shared.data.City
import metropolis.countries.shared.data.Country
import metropolis.xtracted.controller.masterDetail.MasterDetailAction
import metropolis.xtracted.controller.masterDetail.MasterDetailController
import metropolis.xtracted.repository.CrudRepository
import metropolis.xtracted.repository.LazyRepository

class CitiesModuleController(
    private val initiallySelectedCityId : Int,
    private val lazyRepository : LazyRepository<City>,
    private val crudRepository : CrudRepository<City>,
    private val onCitySelection: (City?) -> Unit){

    val controller = MasterDetailController(
        title                      = "Cities",
        selectedId                 = initiallySelectedCityId,
        onNewTableController = { createNewCityExplorer() },
        onNewEditorController = { createNewCityEditor(it) }
        )

    private fun createNewCityExplorer() =
        cityLazyTableController(lazyRepository, onSelectionChange = { showCityInEditor(it) })

    private fun createNewCityEditor(id: Int) =
        cityEditorController(id, crudRepository, onEditorAction = { reloadCityExplorer() })

    private fun showCityInEditor(id: Int){
        controller.executeAction(MasterDetailAction.Open(id = id, editor = createNewCityEditor(id = id)))
        val city = controller.state.lazyTableController.getData(id)
        onCitySelection(city)
    }
    private fun reloadCityExplorer(){
        controller.executeAction(MasterDetailAction.Reload(explorer = createNewCityExplorer()))
        controller.executeAction(MasterDetailAction.SetSelected(null))
    }

}
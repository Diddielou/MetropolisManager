package metropolis.cities.combined.controller

import metropolis.cities.editor.controller.cityEditorController
import metropolis.cities.explorer.controller.cityLazyTableController
import metropolis.cities.shared.data.City
import metropolis.xtracted.controller.editor.EditorController
import metropolis.xtracted.controller.lazyloading.LazyTableController
import metropolis.xtracted.controller.masterDetail.MasterDetailAction
import metropolis.xtracted.controller.masterDetail.MasterDetailController
import metropolis.xtracted.repository.CrudRepository
import metropolis.xtracted.repository.LazyRepository

class CitiesModuleController(
    initiallySelectedCityId : Int,
    private val lazyRepository : LazyRepository<City>,
    private val crudRepository : CrudRepository<City>) {

    private val cityLazyTableController = cityLazyTableController(
                                                                lazyRepository,
                                                                onSelectionChange = { showCityInEditor(it) })
    private val cityEditorController = cityEditorController(
                                                                initiallySelectedCityId,
                                                                crudRepository,
                                                                onEditorAction = { reloadCityExplorer() })

    val controller = MasterDetailController(
        title                      = "Cities MasterDetail",
        selectedId                 = initiallySelectedCityId,
        initialLazyTableController = cityLazyTableController,
        initialEditorController    = cityEditorController,
        onNewTableController = { createNewCityExplorer() },
        onNewEditorController = { createNewCityEditor(it) }
        )

    private fun showCityInEditor(id: Int){
        controller.executeAction(MasterDetailAction.Open(id = id, editor = createNewCityEditor(id = id)))
    }

    private fun reloadCityExplorer(){
        controller.executeAction(MasterDetailAction.Reload(explorer = createNewCityExplorer()))
        controller.executeAction(MasterDetailAction.SetSelected())
    }

    private fun createNewCityEditor(id: Int): EditorController<City> {
        return cityEditorController(id, crudRepository, onEditorAction = { reloadCityExplorer() })
    }

    private fun createNewCityExplorer(): LazyTableController<City> {
        return cityLazyTableController(lazyRepository, onSelectionChange = { showCityInEditor(it) })
    }

}
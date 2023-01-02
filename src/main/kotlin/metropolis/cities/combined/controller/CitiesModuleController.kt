package metropolis.cities.combined.controller

import metropolis.cities.editor.controller.cityEditorController
import metropolis.cities.explorer.controller.cityLazyTableController
import metropolis.cities.shared.data.City
import metropolis.xtracted.controller.masterDetail.MasterDetailAction
import metropolis.xtracted.controller.masterDetail.MasterDetailController
import metropolis.xtracted.repository.CrudRepository
import metropolis.xtracted.repository.LazyRepository

class CitiesModuleController(
    private var initiallySelectedCityId : Int,
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
        initialEditorController    = cityEditorController)

    private fun showCityInEditor(id: Int){
        //controller.showElementInEditor(id = id, editorController = cityEditorController(id, crudRepository, onEditorAction = { reloadCityExplorer() }))
        controller.executeAction(MasterDetailAction.Open(id = id, editor = cityEditorController(id, crudRepository, onEditorAction = { reloadCityExplorer() })))
    }

    private fun reloadCityExplorer(){
        controller.executeAction(MasterDetailAction.Reload(explorer = cityLazyTableController(lazyRepository, onSelectionChange = { showCityInEditor(it) })))
        //controller.reloadTable(lazyTableController = cityLazyTableController(lazyRepository, onSelectionChange = { showCityInEditor(it) }))
        controller.setSelectedInExplorer()
    }
}
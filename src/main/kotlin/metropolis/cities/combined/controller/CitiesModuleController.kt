package metropolis.cities.combined.controller

import metropolis.cities.editor.controller.cityEditorController
import metropolis.cities.explorer.controller.cityLazyTableController
import metropolis.cities.shared.data.City
import metropolis.xtracted.controller.masterDetail.MasterDetailController
import metropolis.xtracted.repository.CrudRepository
import metropolis.xtracted.repository.LazyRepository

class CitiesModuleController(
    private var initiallySelectedCityId : Int,
    private val lazyRepository : LazyRepository<City>,
    private val crudRepository : CrudRepository<City>) {

    private var cityLazyTableController = cityLazyTableController(
                                                                lazyRepository,
                                                                onSelectionChange = { showCityInEditor(it) })
    private var cityEditorController = cityEditorController(
                                                                initiallySelectedCityId,
                                                                crudRepository,
                                                                onEditorAction = { reloadCityExplorer() })

    val controller = MasterDetailController(
        title                      = "Cities MasterDetail",
        selectedId                 = initiallySelectedCityId,
        initialLazyTableController = cityLazyTableController,
        initialEditorController    =  cityEditorController)

    private fun showCityInEditor(id: Int){
        //controller.executeAction(MasterDetailAction.Open(id, editor = cityEditorController(selectedCityId, crudRepository, onEditorAction = { reloadCityExplorer() })))
        controller.showElementInEditor(id = id, editorController = cityEditorController(id, crudRepository, onEditorAction = { reloadCityExplorer() }))
    }

    private fun reloadCityExplorer(){
        //controller.executeAction(MasterDetailAction.Reload())
        controller.reloadTable(lazyTableController = cityLazyTableController(lazyRepository, onSelectionChange = { showCityInEditor(it) }))
        controller.setSelectedInExplorer()
    }
}
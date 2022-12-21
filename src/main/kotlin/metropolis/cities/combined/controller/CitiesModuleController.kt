package metropolis.cities.combined.controller

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import metropolis.cities.combined.model.CitiesModuleState
import metropolis.cities.editor.controller.cityEditorController
import metropolis.cities.explorer.controller.cityLazyTableController
import metropolis.cities.shared.data.City
import metropolis.xtracted.controller.lazyloading.LazyTableAction
import metropolis.xtracted.controller.masterDetail.MasterDetailController
import metropolis.xtracted.repository.CrudRepository
import metropolis.xtracted.repository.LazyRepository

class CitiesModuleController(
    private var selectedCityId : Int,
    private val lazyRepository : LazyRepository<City>,
    private val crudRepository : CrudRepository<City>) {

    var cityLazyTableController = cityLazyTableController(lazyRepository, onSelectionChange = { showCityInEditor(it) })
    var cityEditorController = cityEditorController(selectedCityId, crudRepository, onEditorAction = { reloadTable() })

    val controller = MasterDetailController("Cities MasterDetail", selectedCityId, cityLazyTableController, cityEditorController)

    fun showCityInEditor(id: Int){
        // TODO: controller.triggerAction(Open....)
    }

    fun reloadTable(){
        // TODO: controller.triggerActionReload
    }
}
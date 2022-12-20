package metropolis.cities.combined.controller

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import metropolis.cities.combined.model.CitiesModuleState
import metropolis.cities.editor.controller.cityEditorController
import metropolis.cities.explorer.controller.cityLazyTableController
import metropolis.cities.shared.data.City
import metropolis.xtracted.controller.lazyloading.LazyTableAction
import metropolis.xtracted.repository.CrudRepository
import metropolis.xtracted.repository.LazyRepository

class CitiesModuleController(
    private var selectedCityId : Int,
    private val lazyRepository : LazyRepository<City>,
    private val crudRepository : CrudRepository<City>) {

    var state by mutableStateOf(
        CitiesModuleState(
            title = "Cities Module Demo",
            cityLazyTableController = cityLazyTableController(lazyRepository, onSelectionChange = { showCityInEditor(it) }),
            cityEditorController = cityEditorController(selectedCityId, crudRepository, onEditorAction = { reloadTable() })
        )
    )

    private fun showCityInEditor(id: Int){
        selectedCityId = id
        state = state.copy(cityEditorController = cityEditorController(selectedCityId, crudRepository, onEditorAction = { reloadTable() }))
    }

    private fun reloadTable(){
        println("reloadTable executed")
        state = state.copy(cityLazyTableController = cityLazyTableController(lazyRepository, onSelectionChange = { showCityInEditor(it) }),)
        setSelectedCityId()
    }

    private fun setSelectedCityId(){
        state.cityLazyTableController.triggerAction(LazyTableAction.Select(selectedCityId))
    }

}
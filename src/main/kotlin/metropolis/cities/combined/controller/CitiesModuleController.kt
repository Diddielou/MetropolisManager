package metropolis.cities.combined.controller

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import metropolis.cities.combined.model.CitiesModuleState
import metropolis.cities.editor.controller.cityEditorController
import metropolis.cities.explorer.controller.cityLazyTableController
import metropolis.cities.shared.data.City
import metropolis.xtracted.controller.editor.EditorAction
import metropolis.xtracted.controller.lazyloading.LazyTableAction
import metropolis.xtracted.repository.CrudRepository
import metropolis.xtracted.repository.LazyRepository

class CitiesModuleController(
    selectedCityId        : Int?,
    val lazyRepository    : LazyRepository<City>,
    val crudRepository    : CrudRepository<City>) {

    var state by mutableStateOf(
        CitiesModuleState(
            title = "Cities Module Demo",
            cityLazyTableController = cityLazyTableController(lazyRepository, onSelectionChange = { selectNewCity(it) }),
            cityEditorController = cityEditorController(selectedCityId!!, crudRepository)
        )
    )

    private fun selectNewCity(id: Int){
        state = state.copy(cityEditorController = cityEditorController(id, crudRepository))
    }

}
package metropolis.cities.combined.controller

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import metropolis.cities.combined.model.CitiesModuleState
import metropolis.cities.editor.controller.cityEditorController
import metropolis.cities.explorer.controller.cityLazyTableController
import metropolis.cities.shared.data.City
import metropolis.xtracted.repository.CrudRepository
import metropolis.xtracted.repository.LazyRepository

class CitiesModuleController(
    private val lazyRepository: LazyRepository<City>,
    private val crudRepository: CrudRepository<City>) {

    var state by mutableStateOf(
        CitiesModuleState(
            title = "Cities Module Demo",
            cityLazyTableController = cityLazyTableController(lazyRepository),
            cityEditorController = cityEditorController(2661552, crudRepository)
        )
    )

    /*
    TODO setSelectedCity
     */
}
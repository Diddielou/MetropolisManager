package metropolis.countries.combined.controller

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import metropolis.cities.editor.controller.cityEditorController
import metropolis.countries.combined.model.CountriesModuleState
import metropolis.countries.editor.controller.countryEditorController
import metropolis.countries.explorer.controller.countryLazyTableController
import metropolis.countries.shared.data.Country
import metropolis.xtracted.repository.CrudRepository
import metropolis.xtracted.repository.LazyRepository

class CountriesModuleController(
    selectedCountryId          : Int?,
    private val lazyRepository : LazyRepository<Country>,
    private val crudRepository : CrudRepository<Country>) {

    var state by mutableStateOf(
        CountriesModuleState(
            title = "Countries Module Demo",
            countryLazyTableController = countryLazyTableController(lazyRepository, onSelectionChange = { selectNewCountry(it) }),
            countryEditorController = countryEditorController(selectedCountryId!!, crudRepository),
        )
    )

    private fun selectNewCountry(id: Int){
        state = state.copy(countryEditorController = countryEditorController(id, crudRepository))
    }
}
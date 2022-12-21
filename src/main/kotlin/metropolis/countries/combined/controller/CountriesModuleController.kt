package metropolis.countries.combined.controller

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import metropolis.countries.combined.model.CountriesModuleState
import metropolis.countries.editor.controller.countryEditorController
import metropolis.countries.explorer.controller.countryLazyTableController
import metropolis.countries.shared.data.Country
import metropolis.xtracted.controller.ControllerBase
import metropolis.xtracted.controller.lazyloading.LazyTableAction
import metropolis.xtracted.repository.CrudRepository
import metropolis.xtracted.repository.LazyRepository

class CountriesModuleController (
    private var selectedCountryId : Int,
    private val lazyRepository    : LazyRepository<Country>,
    private val crudRepository    : CrudRepository<Country>)
{

    var state by mutableStateOf(
        CountriesModuleState(
            title = "Countries Module Demo",
            countryLazyTableController = countryLazyTableController(lazyRepository, onSelectionChange = { showCountryInEditor(it) }),
            countryEditorController = countryEditorController(selectedCountryId, crudRepository, onEditorAction = { reloadTable() }),
        )
    )

    private fun showCountryInEditor(id: Int){
        selectedCountryId = id
        state = state.copy(countryEditorController = countryEditorController(selectedCountryId, crudRepository, onEditorAction = { reloadTable() }))
    }

    private fun reloadTable(){
        println("reloadTable executed")
        state = state.copy(countryLazyTableController = countryLazyTableController(lazyRepository, onSelectionChange = { showCountryInEditor(it) }) )
        setSelectedCountryId()
    }

    private fun setSelectedCountryId(){
        state.countryLazyTableController.triggerAction(LazyTableAction.Select(selectedCountryId))
    }

}
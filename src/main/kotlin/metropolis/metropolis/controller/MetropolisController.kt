package metropolis.metropolis.controller

import metropolis.metropolis.data.MetropolisState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import metropolis.cities.combined.controller.CitiesModuleController
import metropolis.cities.shared.data.City
import metropolis.cities.shared.repository.CityColumn
import metropolis.countries.combined.controller.CountriesModuleController
import metropolis.countries.shared.data.Country
import metropolis.countries.shared.repository.CountryColumn
import metropolis.xtracted.controller.lazyloading.LazyTableAction
import metropolis.xtracted.controller.masterDetail.MasterDetailAction
import metropolis.xtracted.repository.CrudRepository
import metropolis.xtracted.repository.LazyRepository

class MetropolisController( // not generic
    private val countryLazyRepository: LazyRepository<Country>, private val countryCrudRepository: CrudRepository<Country>,
    private val cityLazyRepository : LazyRepository<City>, private val cityCrudRepository: CrudRepository<City>) {

    private val initiallySelectedCityId = 3038999
    private val initiallySelectedCountryId = 0
    private var selectionFromCityExplorer = false

    val countriesModule = CountriesModuleController(initiallySelectedCountryId, countryLazyRepository, countryCrudRepository, onCountrySelection = {
        filterAndSelectFittingCapital(it)
    })
    val citiesModule = CitiesModuleController(initiallySelectedCityId, cityLazyRepository, cityCrudRepository, onCitySelection = {
        selectFittingCountry(it)
    })

    var state by mutableStateOf(
        MetropolisState( // not generic
            title = "Metropolis",
            countriesModuleController = countriesModule,
            citiesModuleController = citiesModule)
    )

    private fun filterAndSelectFittingCapital(country: Country?) {
        val ccCol = citiesModule.controller.state.lazyTableController.state.columns[1] // Country Code
        val countryCode = country?.isoAlpha2
        val countryCapital = country?.capital
        val selectedId = citiesModule.controller.state.selectedId
        val selectedCountryIdCurrent = countriesModule.controller.state.selectedId

        println("onCountrySelection -> filterAndSelectFittingCapital(): ${country?.name} clicked")
        if(country != null){ //  && country.id != selectedCountryId Doesn't work
            //println("country: ${country.name}, cur: ${country.id} != $selectedCountryIdCurrent, cmod: ${country.id} != $selectedCountryId")
            println("onCountrySelection -> filterAndSelectFittingCapital(): selectionFromCityExplorer: $selectionFromCityExplorer") //, citySelected: $citySelected")
            if(!selectionFromCityExplorer){ //country.id != selectedCountryIdCurrent && country.id != selectedCountryId
                citiesModule.controller.state.lazyTableController.executeAction(LazyTableAction.SetFilter(column = ccCol, filter = countryCode!!))
                // select the city that is the country's capital
                //if(country.id != selectedCountryId){ // but only if this country isn't already selected
                if(countryCapital != null && countryCapital != "..."){
                    val controller = citiesModule.controller.state.lazyTableController
                    // Look for a city with this capital's name; this is the countries ASCII_NAME
                    val capitalCity = controller.searchFor(countryCapital, column = CityColumn.ASCII_NAME)
                    if(capitalCity != null) {
                        val capitalId = capitalCity.id
                        if(capitalId != selectedId){ // prevent endless sql statement loop
                            println("onCountrySelection -> filterAndSelectFittingCapital(): capitalCity: ${capitalCity.name} selected")
                            citiesModule.controller.executeAction(MasterDetailAction.SetSelected(capitalCity.id))
                        }
                    }
                }
            }
            selectionFromCityExplorer = false
        }
    }

    private fun selectFittingCountry(city: City?){
        // nimm von der City den CC...
        val countryCode = city?.countryCode // countryCode of selected City
        val selectedId = countriesModule.controller.state.selectedId
        println("onCitySelection -> selectFittingCountry(): ${city?.name} clicked")
        if(countryCode != null && countryCode != "..."){
            //und hole mir das Land dazu
            val controller = countriesModule.controller.state.lazyTableController
            // Look for a country with this countryCode; this is the countries ISO_ALPHA2
            val country = controller.searchFor(countryCode, column = CountryColumn.ISO_ALPHA2)
            if(country != null) {
                val countryId = country.id
                if(countryId != selectedId) { // prevent endless sql statement loop
                    println("onCitySelection -> selectFittingCountry(): country ${country.name} selected")
                    //println("city: country: ${country.id} != $selectedId")
                    selectionFromCityExplorer = true
                    countriesModule.controller.executeAction(MasterDetailAction.SetSelected(countryId))
                }
            }
        }
        println("onCitySelection -> selectFittingCountry(): selectionFromCityExplorer: $selectionFromCityExplorer")
    }

}
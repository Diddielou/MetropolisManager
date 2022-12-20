package metropolis.metropolis.controller

import metropolis.metropolis.data.MetropolisState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import metropolis.cities.combined.controller.CitiesModuleController
import metropolis.cities.shared.data.City
import metropolis.countries.combined.controller.CountriesModuleController
import metropolis.countries.shared.data.Country
import metropolis.xtracted.repository.CrudRepository
import metropolis.xtracted.repository.LazyRepository

class MetropolisController(
    countryLazyRepository: LazyRepository<Country>, countryCrudRepository: CrudRepository<Country>,
    cityLazyRepository : LazyRepository<City>, cityCrudRepository: CrudRepository<City>) {

    private val initiallySelectedCityId = 2661552
    private val initiallySelectedCountryId = 0

    val countriesController = CountriesModuleController(initiallySelectedCountryId, countryLazyRepository, countryCrudRepository)
    val citiesController = CitiesModuleController(initiallySelectedCityId, cityLazyRepository, cityCrudRepository)

    var state by mutableStateOf(
        MetropolisState(
            title = "Metropolis",
            countriesModuleController = countriesController,
            citiesModuleController = citiesController)
    )

}
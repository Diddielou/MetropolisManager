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

class MetropolisController( // not generic
    countryLazyRepository: LazyRepository<Country>, countryCrudRepository: CrudRepository<Country>,
    cityLazyRepository : LazyRepository<City>, cityCrudRepository: CrudRepository<City>) {

    private val initiallySelectedCityId = 3038999
    private val initiallySelectedCountryId = 0

    val countriesModule = CountriesModuleController(initiallySelectedCountryId, countryLazyRepository, countryCrudRepository)
    val citiesModule = CitiesModuleController(initiallySelectedCityId, cityLazyRepository, cityCrudRepository)

    var state by mutableStateOf(
        MetropolisState( // not generic
            title = "Metropolis",
            countriesModuleController = countriesModule,
            citiesModuleController = citiesModule)
    )

}
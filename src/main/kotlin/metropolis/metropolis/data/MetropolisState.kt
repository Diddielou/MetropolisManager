package metropolis.metropolis.data

import metropolis.cities.combined.controller.CitiesModuleController
import metropolis.countries.combined.controller.CountriesModuleController

data class MetropolisState(
    val title: String,
    val countriesModuleController: CountriesModuleController,
    val citiesModuleController: CitiesModuleController)

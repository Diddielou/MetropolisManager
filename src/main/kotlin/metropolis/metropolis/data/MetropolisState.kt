package metropolis.metropolis.data

import metropolis.cities.combined.controller.CitiesModuleController
import metropolis.cities.combined.model.CitiesModuleState
import metropolis.countries.combined.controller.CountriesModuleController
import metropolis.countries.combined.model.CountriesModuleState

data class MetropolisState(
    val title: String,
    val countriesModuleController: CountriesModuleController,
    val citiesModuleController: CitiesModuleController)

package metropolis.metropolis

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.application
import metropolis.cities.shared.repository.cityCrudRepository
import metropolis.cities.shared.repository.cityLazyTableRepository
import metropolis.countries.shared.repository.countryCrudRepository
import metropolis.countries.shared.repository.countryLazyTableRepository
import metropolis.metropolis.controller.MetropolisController
import metropolis.metropolis.view.MetropolisWindow
import metropolis.xtracted.repository.urlFromResources

fun main() {

    val url = "/data/metropolisDB".urlFromResources()
    val countryLazyRepository = countryLazyTableRepository(url)
    val countryCrudRepository = countryCrudRepository(url)
    val cityLazyRepository = cityLazyTableRepository(url)
    val cityCrudRepository = cityCrudRepository(url)

    val controller = MetropolisController(
        countryLazyRepository = countryLazyRepository,
        countryCrudRepository = countryCrudRepository,
        cityLazyRepository = cityLazyRepository,
        cityCrudRepository = cityCrudRepository)

    application {
        val countriesModuleState = controller.state.countriesModuleController.state
        //val citiesModuleState = controller.state.citiesModuleController.state // TODO state not accessible anymore

        // TODO access Action (initialize all Scopes)
        countriesModuleState.countryLazyTableController.initializeUiScope(rememberCoroutineScope())
        countriesModuleState.countryEditorController.initializeUiScope(rememberCoroutineScope())
        //citiesModuleState.cityLazyTableController.initializeUiScope(rememberCoroutineScope())
        //citiesModuleState.cityEditorController.initializeUiScope(rememberCoroutineScope())

        //MetropolisWindow(controller.state)
    }

}
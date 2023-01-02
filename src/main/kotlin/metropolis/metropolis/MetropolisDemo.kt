package metropolis.metropolis

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
        MetropolisWindow(controller.state)
    }

}
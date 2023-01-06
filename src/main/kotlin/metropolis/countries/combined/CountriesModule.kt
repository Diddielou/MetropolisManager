package metropolis.countries.combined

import androidx.compose.ui.window.application
import metropolis.countries.combined.controller.CountriesModuleController
import metropolis.countries.combined.view.CountriesModuleWindow
import metropolis.countries.shared.repository.countryCrudRepository
import metropolis.countries.shared.repository.countryLazyTableRepository
import metropolis.xtracted.repository.urlFromResources
import java.util.logging.Level
import java.util.logging.LogManager
import java.util.logging.Logger

fun main() {
    LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME).level = Level.INFO

    val initiallySelectedCountryId = 0

    val url = "/data/metropolisDB".urlFromResources()
    val countryLazyRepository = countryLazyTableRepository(url)
    val countryCrudRepository = countryCrudRepository(url)
    val module = CountriesModuleController(
        initiallySelectedCountryId,
        countryLazyRepository,
        countryCrudRepository,
        onCountrySelection = { println( "Country: $it selected!") }
    )

    application {
        module.controller.initializeUiScopes() // must be run here because it is a Composable
        CountriesModuleWindow(state = module.controller.state, trigger = { module.controller.executeAction(it) }) // TODO
    }

}

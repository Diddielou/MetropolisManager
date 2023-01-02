package metropolis.cities.combined

import androidx.compose.ui.window.application
import metropolis.cities.combined.controller.CitiesModuleController
import metropolis.cities.combined.view.CitiesModuleWindow
import metropolis.cities.shared.repository.cityCrudRepository
import metropolis.cities.shared.repository.cityLazyTableRepository
import metropolis.xtracted.controller.masterDetail.MasterDetailController
import metropolis.xtracted.repository.urlFromResources
import java.util.logging.Level
import java.util.logging.LogManager
import java.util.logging.Logger

fun main() {
    LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME).level = Level.WARNING

    val initiallySelectedCityId = 2661552

    val url = "/data/metropolisDB".urlFromResources()
    val cityLazyRepository = cityLazyTableRepository(url)
    val cityCrudRepository = cityCrudRepository(url)
    val module = CitiesModuleController(initiallySelectedCityId, cityLazyRepository, cityCrudRepository)

    application {
        // TODO: MasterDetailAction initializeUiScopes
        module.controller.initializeUiScopes()
        CitiesModuleWindow(state = module.controller.state, trigger = { module.controller.executeAction(it) })
    }
}
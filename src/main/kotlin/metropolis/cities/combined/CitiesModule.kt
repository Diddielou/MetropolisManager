package metropolis.cities.combined

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.application
import metropolis.cities.combined.controller.CitiesModuleController
import metropolis.cities.combined.view.CitiesModuleWindow
import metropolis.cities.shared.repository.cityCrudRepository
import metropolis.cities.shared.repository.cityLazyTableRepository
import metropolis.xtracted.repository.urlFromResources
import java.util.logging.Level
import java.util.logging.LogManager
import java.util.logging.Logger


fun main() {
    LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME).level = Level.WARNING

    val url = "/data/metropolisDB".urlFromResources()
    val cityLazyRepository = cityLazyTableRepository(url)
    val cityCrudRepository = cityCrudRepository(url)
    val controller = CitiesModuleController(cityLazyRepository, cityCrudRepository)

    application {
        controller.state.cityLazyTableController.initializeUiScope(rememberCoroutineScope())
        controller.state.cityEditor.initializeUiScope(rememberCoroutineScope())

        CitiesModuleWindow(state = controller.state)
    }
}
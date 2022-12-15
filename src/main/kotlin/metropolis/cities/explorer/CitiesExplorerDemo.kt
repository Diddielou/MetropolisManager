package metropolis.cities.explorer

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.application
import metropolis.cities.explorer.controller.cityController
import metropolis.cities.explorer.view.CityExplorerWindow
import metropolis.cities.shared.repository.cityRepository
import metropolis.xtracted.repository.urlFromResources
import java.util.logging.Level
import java.util.logging.LogManager
import java.util.logging.Logger

fun main() {
    LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME).level = Level.WARNING

    val url = "/data/metropolisDB".urlFromResources()
    val repository = cityRepository(url)
    val controller = cityController(repository)

    application {
        with(controller) {
            initializeUiScope(rememberCoroutineScope())
            CityExplorerWindow(state = state,
                dataProvider = { getData(it) },
                idProvider = { it.id },
                trigger = { triggerAction(it) }
            )
        }
    }
}
package metropolis.cities.explorer

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.application
import metropolis.cities.explorer.controller.cityLazyTableController
import metropolis.cities.explorer.view.CityExplorerWindow
import metropolis.cities.shared.repository.cityLazyTableRepository
import metropolis.xtracted.repository.urlFromResources
import java.util.logging.Level
import java.util.logging.LogManager
import java.util.logging.Logger

fun main() {
    LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME).level = Level.WARNING

    val url = "/data/metropolisDB".urlFromResources()
    val repository = cityLazyTableRepository(url)
    val controller = cityLazyTableController(repository)

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
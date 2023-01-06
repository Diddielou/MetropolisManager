package metropolis.countries.explorer

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.application
import metropolis.countries.explorer.controller.countryLazyTableController
import metropolis.countries.explorer.view.CountryExplorerWindow
import metropolis.countries.shared.repository.countryLazyTableRepository
import metropolis.xtracted.repository.urlFromResources
import java.util.logging.Level
import java.util.logging.LogManager
import java.util.logging.Logger

fun main() {
    LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME).level = Level.WARNING

    val url = "/data/metropolisDB".urlFromResources()
    val repository = countryLazyTableRepository(url)
    val controller = countryLazyTableController(repository)

    application {
        with(controller){
            initializeUiScope(rememberCoroutineScope())
            CountryExplorerWindow(state = state,
                dataProvider = { getData(it) },
                idProvider   = { it.id },
                trigger      = { triggerAction(it)}
            )
        }
    }
}
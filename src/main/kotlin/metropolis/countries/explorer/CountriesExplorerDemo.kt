package metropolis.countries.explorer

import androidx.compose.runtime.rememberCoroutineScope
import java.util.logging.Level
import java.util.logging.LogManager
import java.util.logging.Logger
import androidx.compose.ui.window.application
import metropolis.countries.explorer.controller.countryController
import metropolis.countries.explorer.view.CountryExplorerWindow
import metropolis.countries.shared.repository.countryLazyRepository
import metropolis.xtracted.repository.urlFromResources

fun main() {
    LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME).level = Level.WARNING

    val url = "/data/metropolisDB".urlFromResources()
    val repository = countryLazyRepository(url)
    val controller = countryController(repository)

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
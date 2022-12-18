package metropolis.countries.editor

import androidx.compose.runtime.rememberCoroutineScope
import java.util.logging.Level
import java.util.logging.LogManager
import java.util.logging.Logger
import androidx.compose.ui.window.application
import metropolis.countries.editor.controller.countryEditorController
import metropolis.countries.editor.view.CountryEditorWindow
import metropolis.countries.shared.repository.countryCrudRepository
import metropolis.xtracted.repository.urlFromResources

fun main() {
    LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME).level = Level.INFO

    val url = "/data/metropolisDB".urlFromResources()
    val repository = countryCrudRepository(url)
    val selectedCountryId = 0
    val controller = countryEditorController(selectedCountryId, repository, {})

    application {
        controller.initializeUiScope(rememberCoroutineScope())
        CountryEditorWindow(state   = controller.state,
            trigger = {controller.triggerAction(it)})
    }
}
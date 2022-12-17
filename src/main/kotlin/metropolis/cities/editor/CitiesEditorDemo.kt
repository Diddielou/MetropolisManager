package metropolis.cities.editor

import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.window.application
import metropolis.cities.editor.controller.cityEditor
import metropolis.cities.editor.view.CityEditorWindow
import metropolis.cities.shared.repository.cityCrudRepository
import metropolis.xtracted.repository.urlFromResources
import java.util.logging.Level
import java.util.logging.LogManager
import java.util.logging.Logger

fun main() {
    LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME).level = Level.INFO

    val url = "/data/metropolisDB".urlFromResources()
    val repository = cityCrudRepository(url)
    val selectedCityId = 2661552

    val controller = cityEditor(selectedCityId, repository)

    application {
        controller.initializeUiScope(rememberCoroutineScope())

        CityEditorWindow(state   = controller.state,
            trigger = {controller.triggerAction(it)})
    }
}
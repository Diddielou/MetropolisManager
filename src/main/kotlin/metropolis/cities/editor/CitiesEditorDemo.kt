package metropolis.cities.editor

import java.util.logging.Level
import java.util.logging.LogManager
import java.util.logging.Logger

fun main() {
    LogManager.getLogManager().getLogger(Logger.GLOBAL_LOGGER_NAME).level = Level.INFO

    //val url        = "/data/poichDB".urlFromResources()
    //val repository = poiChRepository(url)
    //val controller = poiChController(repository)

    //application {
    //    with(controller){
    //        initializeUiScope(rememberCoroutineScope())
    //        PoiChExplorerWindow(state        = state,
    //            dataProvider = { getData(it) },
    //            idProvider   = { it.id },
    //            trigger      = { triggerAction(it)}
    //        )
    //    }
    //}
}
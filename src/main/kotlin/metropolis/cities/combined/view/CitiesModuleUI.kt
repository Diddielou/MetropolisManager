package metropolis.cities.combined.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import metropolis.cities.editor.view.CityEditorUi
import metropolis.cities.explorer.view.CityExplorerUI
import metropolis.cities.shared.data.City
import metropolis.xtracted.controller.masterDetail.MasterDetailAction
import metropolis.xtracted.model.MasterDetailState
import metropolis.xtracted.view.MasterDetailUi

@Composable
fun ApplicationScope.CitiesModuleWindow(state: MasterDetailState<City>, trigger: (MasterDetailAction) -> Unit) {
    Window(title          = state.title,
        onCloseRequest = { exitApplication() },
        state          = rememberWindowState(width    = 1000.dp,
                                             height   = 900.dp,
                                             position = WindowPosition(Alignment.Center)
        )
    ) {
        CitiesMasterDetailUi(state, trigger)
    }
}

@Composable
fun CitiesMasterDetailUi(state: MasterDetailState<City>, trigger: (MasterDetailAction) -> Unit) {
    val editorController = state.editorController
    val explorerController = state.lazyTableController

     MasterDetailUi(
        state = state,
        explorer = {
            with(explorerController) {
                CityExplorerUI(
                    state = explorerController.state,
                    dataProvider = { getData(it) },
                    idProvider   = { it.id },
                    trigger      = { triggerAction(it) },
                    modifier     = Modifier
                )
            }
        },
        editor = {
            CityEditorUi(
                    state = editorController.state,
                    trigger = { editorController.triggerAction(it) }
            )
        },
        trigger = trigger
    )
}

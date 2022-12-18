package metropolis.cities.combined.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import metropolis.cities.combined.model.CitiesModuleState
import metropolis.cities.editor.view.CityEditorUi
import metropolis.cities.explorer.view.CityExplorerUI
import metropolis.xtracted.view.MasterDetail
import metropolis.xtracted.view.TopBar

@Composable
fun ApplicationScope.CitiesModuleWindow(state: CitiesModuleState) {
    Window(title          = state.title,
        onCloseRequest = { exitApplication() },
        state          = rememberWindowState(width    = 1000.dp,
                                             height   = 900.dp,
                                             position = WindowPosition(Alignment.Center)
        )
    ) {
        CitiesModuleUi(state)
    }
}

@Composable
fun CitiesModuleUi(state: CitiesModuleState) {
    val editorController = state.cityEditorController
    val explorerController = state.cityLazyTableController

    MasterDetail(
        toolbar = { TopBar(state = state) },
        explorer = {
            with(explorerController) {
                CityExplorerUI(
                    state = explorerController.state,
                    dataProvider = { getData(it) },
                    idProvider = { it.id },
                    trigger = { triggerAction(it) }
                )
            }
        },
        editor = {

            CityEditorUi(
                state = editorController.state,
                trigger = { editorController.triggerAction(it) }
            )
        }
    )
}

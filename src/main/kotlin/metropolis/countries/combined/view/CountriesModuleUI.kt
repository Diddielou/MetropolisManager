package metropolis.countries.combined.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import metropolis.countries.combined.model.CountriesModuleState
import metropolis.countries.editor.view.CountryEditorUi
import metropolis.countries.explorer.view.CountryExplorerUI
import metropolis.xtracted.view.MasterDetail
import metropolis.xtracted.view.TopBar

@Composable
fun ApplicationScope.CountriesModuleWindow(state: CountriesModuleState) {
    Window(title          = state.title,
        onCloseRequest = { exitApplication() },
        state          = rememberWindowState(width    = 1000.dp,
                                             height   = 900.dp,
                                             position = WindowPosition(Alignment.Center)
        )
    ) {
        CountriesModuleUi(state)
    }
}

@Composable
fun CountriesModuleUi(state: CountriesModuleState) {
    val editorController = state.countryEditorController
    val explorerController = state.countryLazyTableController

    MasterDetail(
        toolbar = { TopBar(title = state.title,
                    addOnClick = {},
                    deleteOnClick = {}) }, // TODO button onClicks
        explorer = {
            with(explorerController) {
                CountryExplorerUI(
                    state = explorerController.state,
                    dataProvider = { getData(it) },
                    idProvider = { it.id },
                    trigger = { triggerAction(it) },
                    modifier = Modifier
                )
            }
        },
        editor = {
            CountryEditorUi(
                state = editorController.state,
                trigger = { editorController.triggerAction(it) }
            )
        }
    )
}

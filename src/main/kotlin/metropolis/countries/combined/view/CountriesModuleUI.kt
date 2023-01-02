package metropolis.countries.combined.view

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import metropolis.countries.editor.view.CountryEditorUi
import metropolis.countries.explorer.view.CountryExplorerUI
import metropolis.countries.shared.data.Country
import metropolis.xtracted.controller.masterDetail.MasterDetailAction
import metropolis.xtracted.model.MasterDetailState
import metropolis.xtracted.view.*

@Composable
fun ApplicationScope.CountriesModuleWindow(state: MasterDetailState<Country>, trigger: (MasterDetailAction) -> Unit) {
    Window(title          = state.title,
        onCloseRequest = { exitApplication() },
        state          = rememberWindowState(width    = 1000.dp,
                                             height   = 900.dp,
                                             position = WindowPosition(Alignment.Center)
        )
    ) {
        CountriesModuleUi(state, trigger)
    }
}

@Composable
fun CountriesModuleUi(state: MasterDetailState<Country>, trigger: (MasterDetailAction) -> Unit) {
    val editorController = state.editorController
    val explorerController = state.lazyTableController

    MasterDetailUi(
        state = state,
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
        },
        trigger = trigger
    )
}

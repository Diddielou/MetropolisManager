package metropolis.metropolis.view

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import metropolis.cities.combined.view.CitiesModuleUi
import metropolis.cities.shared.data.City
import metropolis.countries.combined.view.CountriesModuleUi
import metropolis.countries.shared.data.Country
import metropolis.metropolis.data.MetropolisState
import metropolis.xtracted.controller.editor.EditorAction
import metropolis.xtracted.model.MasterDetailState
import metropolis.xtracted.view.AlignLeftRight
import metropolis.xtracted.view.MasterDetail
import metropolis.xtracted.view.Heading1

@Composable
fun ApplicationScope.MetropolisWindow(state: MetropolisState) {
    Window(title          = state.title,
           onCloseRequest = ::exitApplication,
           state          = rememberWindowState(width    = 1800.dp,
                                                height   = 900.dp,
                                                position = WindowPosition(Alignment.Center)
           )
    ) {
        MetropolisUi(state = state)
    }
}

@Composable
private fun MetropolisUi(state: MetropolisState) {
    val countryController = state.countriesModuleController.controller
    val cityController = state.citiesModuleController.controller

    MasterDetail(
        toolbar = { MetropolisTopBar(state.title) },
        explorer = { CountriesModuleUi(state = countryController.state, trigger = { countryController.executeAction(it) }) },
        editor = { CitiesModuleUi(state = cityController.state, trigger = { cityController.executeAction(it) }) },
        weight1 = 0.5f, weight2 = 0.5f
    )
}

@Composable
fun MetropolisTopBar(title: String){
    AlignLeftRight() {
        Heading1(text = title)
    }
}

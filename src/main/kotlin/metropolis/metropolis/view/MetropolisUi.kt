package metropolis.metropolis.view

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import metropolis.cities.combined.view.CitiesMasterDetailUi
import metropolis.countries.combined.view.CountriesMasterDetailUi
import metropolis.metropolis.data.MetropolisState
import metropolis.xtracted.view.AlignLeftRight
import metropolis.xtracted.view.ExpensesAppTheme
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
        ExpensesAppTheme(false) {
            MetropolisUi(state = state)
        }
    }
}

@Composable
private fun MetropolisUi(state: MetropolisState) {
    val countryController = state.countriesModuleController.controller
    val cityController = state.citiesModuleController.controller

    // ui scopes can only be initialized here
    countryController.initializeUiScopes()
    cityController.initializeUiScopes()

    MasterDetail(
        toolbar = { MetropolisTopBar(state.title) },
        explorer = { CountriesMasterDetailUi(state = countryController.state, trigger = { countryController.executeAction(it) }) },
        editor = { CitiesMasterDetailUi(state = cityController.state, trigger = { cityController.executeAction(it) }) },
        weight1 = 0.5f, weight2 = 0.5f
    )
}

@Composable
fun MetropolisTopBar(title: String){
    AlignLeftRight() {
        Heading1(text = title)
    }
}

package metropolis.metropolis.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import metropolis.cities.combined.view.CitiesModuleUi
import metropolis.countries.combined.view.CountriesModuleUi
import metropolis.metropolis.data.MetropolisState
import metropolis.xtracted.view.VulcanSalute

@Composable
fun ApplicationScope.MetropolisWindow(state: MetropolisState) {
    Window(onCloseRequest = ::exitApplication,
           title          = state.title,
           state          = rememberWindowState(width    = 1200.dp,
                                                height   = 900.dp,
                                                position = WindowPosition(Alignment.Center))) {
        MetropolisUi(state)
    }
}

@Composable
private fun MetropolisUi(state: MetropolisState) {
    Column(modifier            = Modifier.fillMaxSize(),
           horizontalAlignment = Alignment.Start,
           verticalArrangement = Arrangement.Top) {
          CountriesModuleUi(state = state.countriesModuleController.state) // MasterDetail -> Column
          CitiesModuleUi(state = state.citiesModuleController.state) // MasterDetail -> Column
    }
}
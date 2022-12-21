package metropolis.metropolis.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
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
import metropolis.xtracted.view.AlignLeftRight
import metropolis.xtracted.view.TopBar
import metropolis.xtracted.view.VulcanSalute
import metropolis.xtracted.view.Welcome

@Composable
fun ApplicationScope.MetropolisWindow(state: MetropolisState) {
    Window(onCloseRequest = ::exitApplication,
           title          = state.title,
           state          = rememberWindowState(width    = 1200.dp,
                                                height   = 900.dp,
                                                position = WindowPosition(Alignment.Center))) {


        var isPerformingTask by remember { mutableStateOf(true) }

        LaunchedEffect(Unit) {
            //delay(2000) // Do some heavy lifting
            isPerformingTask = false
        }

        if (isPerformingTask) {
            Window(onCloseRequest = ::exitApplication) {
                Text("Performing some tasks. Please wait!")
            }
        } else {
            Window(onCloseRequest = ::exitApplication) {
                Text("Hello, World!")
            }
        }
    }
}

@Composable
private fun MetropolisUi(state: MetropolisState) {
    MetropolisTopBar(title = state.title)

    Column(modifier            = Modifier.fillMaxSize(),
           horizontalAlignment = Alignment.Start,
           verticalArrangement = Arrangement.Top) {
          CountriesModuleUi(state = state.countriesModuleController.state) // MasterDetail -> Column
          //CitiesModuleUi(state = ) // MasterDetail -> Column // TODO state not accessible anymore
    }
}

@Composable
fun MetropolisTopBar(title: String){
    AlignLeftRight() {
        Welcome(text = title, modifier = Modifier)
    }
}
package metropolis.metropolis.view

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import metropolis.cities.shared.data.City
import metropolis.countries.shared.data.Country
import metropolis.metropolis.data.MetropolisState
import metropolis.xtracted.controller.editor.EditorAction
import metropolis.xtracted.model.MasterDetailState
import metropolis.xtracted.view.AlignLeftRight
import metropolis.xtracted.view.MasterDetail
import metropolis.xtracted.view.Heading1

@Composable
fun ApplicationScope.MetropolisWindow(state: MetropolisState) {
    Window(onCloseRequest = ::exitApplication,
           title          = state.title,
           state          = rememberWindowState(width    = 1200.dp,
                                                height   = 900.dp,
                                                position = WindowPosition(Alignment.Center))) {
    }
}

@Composable
private fun MetropolisUi(state: MetropolisState) {
    MasterDetail(
        toolbar = { MetropolisTopBar(state.title) },
        explorer = {  }, //showCountriesUI(state = state.countriesModuleController.state, trigger = {})
        editor = { }// { showCitiesUI(state = state.citiesModuleController.controller.state, trigger = {}) }
    )

}

@Composable
fun MetropolisTopBar(title: String){
    AlignLeftRight() {
        Heading1(text = title)
    }
}

@Composable
private fun showCountriesUI(state: MasterDetailState<Country>, trigger : (EditorAction) -> Unit){
    //CountriesModuleUi(state = state, trigger = trigger)
}

@Composable
private fun showCitiesUI(state: MasterDetailState<City>, trigger : (EditorAction) -> Unit){
    //CitiesModuleUi(state = state, trigger = trigger)
}
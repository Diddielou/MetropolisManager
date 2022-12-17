package metropolis.countries.explorer.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import metropolis.countries.shared.data.Country
import metropolis.xtracted.model.TableState
import metropolis.xtracted.controller.lazyloading.LazyTableAction
import metropolis.xtracted.view.explorer.Table

@Composable
fun ApplicationScope.CountryExplorerWindow(state       : TableState<Country>,
                                           dataProvider: (Int) -> Country,
                                           idProvider  : (Country) -> Int,
                                           trigger     : (LazyTableAction) -> Unit) {
    Window(title          = state.title,
           onCloseRequest = ::exitApplication,
           state          = rememberWindowState(width    = 850.dp,
                                                height   = 500.dp,
                                                position = WindowPosition(Alignment.Center)
        )
    ) {
        CountryExplorerUI(state, dataProvider, idProvider, trigger)
    }
}

@Composable
fun CountryExplorerUI(state       : TableState<Country>,
                      dataProvider: (Int) -> Country,
                      idProvider  : (Country) -> Int,
                      trigger     : (LazyTableAction) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()
        .background(Color(0xFFEEEEEE))
        .padding(10.dp)) {
        Table(tableState   = state,
            itemProvider = dataProvider,
            idProvider   = idProvider,
            trigger      = trigger,
            modifier     = Modifier.weight(1.0f))
    }
}
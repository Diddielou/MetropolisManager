package metropolis.cities.editor.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.ApplicationScope
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPosition
import androidx.compose.ui.window.rememberWindowState
import metropolis.cities.editor.controller.Id
import metropolis.cities.shared.data.City
import metropolis.xtracted.controller.editor.EditorAction
import metropolis.xtracted.model.Attribute
import metropolis.xtracted.model.EditorState
import metropolis.xtracted.model.get
import metropolis.xtracted.view.EditorHeadline
import metropolis.xtracted.view.VSpace
import metropolis.xtracted.view.editor.EditorBar
import metropolis.xtracted.view.editor.Form
import metropolis.xtracted.view.format

@Composable
fun ApplicationScope.CityEditorWindow(state: EditorState<City>, trigger : (EditorAction) -> Unit) {

    Window(title          = state.title.translate(state.locale),
           onCloseRequest = ::exitApplication,
           state          = rememberWindowState(width    = 500.dp,
                                                height   = 700.dp,
                                                position = WindowPosition(Alignment.Center)
        )) {
        CityEditorUi(state, trigger)
    }
}

@Composable
fun CityEditorUi(state: EditorState<City>, trigger : (EditorAction) -> Unit) {
    Column{
        EditorBar(state, trigger)

        Header(state)

        Card(modifier = Modifier.padding(10.dp)
            .weight(1.0f)) {
            Form(state   = state,
                trigger = trigger)
        }
    }
}

@Composable
private fun Header(state: EditorState<City>) {
    // im Editor-State werden die Attribute verwaltet. Diese können generisch als Formular angezeigt werden
    // der Header ist jedoch speziell, nicht generisch (oder noch nicht)
    val name            : Attribute<String> = state[Id.NAME]
    val country         : Attribute<String> = state[Id.COUNTRY_CODE]
    val population      : Attribute<String> = state[Id.POPULATION]

    val huge       = 42.sp
    val large      = 18.sp
    val small      = 12.sp

    Row(modifier = Modifier.height(IntrinsicSize.Max).padding(10.dp)){
        Column(modifier = Modifier.weight(1.0f)) {
            EditorHeadline(text = "${name.value.format("??")}, ${country.value}", fontSize = huge)
            VSpace(10.dp)
            EditorHeadline(text = population.valueAsText, fontSize = large)
            /*
            EditorHeadline(text = "${name.value.format("??")}, ${country.value}", fontSize = huge)
            VSpace(10.dp)
            EditorHeadline(text = masl.valueAsText, fontSize = large)
            EditorHeadline(text = timeZone.value ?: "", fontSize = large)
             */
        }
    }
}

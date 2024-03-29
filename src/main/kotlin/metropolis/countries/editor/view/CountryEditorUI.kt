package metropolis.countries.editor.view

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
import metropolis.countries.editor.controller.Id
import metropolis.countries.shared.data.Country
import metropolis.xtracted.controller.editor.EditorAction
import metropolis.xtracted.model.Attribute
import metropolis.xtracted.model.EditorState
import metropolis.xtracted.model.get
import metropolis.xtracted.view.EditorHeadline
import metropolis.xtracted.view.VSpace
import metropolis.xtracted.view.editor.EditorBar
import metropolis.xtracted.view.editor.Form
import metropolis.xtracted.view.format
import metropolis.xtracted.view.pp

@Composable
fun ApplicationScope.CountryEditorWindow(state: EditorState<Country>, trigger : (EditorAction) -> Unit) {

    Window(title          = state.title.translate(state.locale),
           onCloseRequest = ::exitApplication,
           state          = rememberWindowState(width    = 500.dp,
                                                height   = 700.dp,
                                                position = WindowPosition(Alignment.Center)
        )) {
        CountryEditorUi(state, trigger)
    }
}

@Composable
fun CountryEditorUi(state: EditorState<Country>, trigger : (EditorAction) -> Unit) {
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
private fun Header(state: EditorState<Country>) {
    // im Editor-State werden die Attribute verwaltet. Diese können generisch als Formular angezeigt werden
    // der Header ist jedoch speziell, nicht generisch (oder noch nicht)
    val name            : Attribute<String>   = state[Id.NAME]
    val areaSqm         : Attribute<Double>   = state[Id.AREA_SQM]

    val huge       = 42.sp
    val large      = 18.sp

    Row(modifier = Modifier.height(IntrinsicSize.Max).padding(10.dp)){
        Column(modifier = Modifier.weight(1.0f)) {
            EditorHeadline(text = if(name.value.isNullOrEmpty()) "[No name]" else name.value.format("??"), fontSize = huge)
            VSpace(10.dp)
            EditorHeadline(text = "${areaSqm.value.pp("%,.1f", "??")} ${areaSqm.unit}", fontSize = large)
        }
    }
}

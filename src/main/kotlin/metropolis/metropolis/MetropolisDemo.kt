package metropolis.metropolis

import androidx.compose.ui.window.application
import metropolis.metropolis.controller.MetropolisController
import metropolis.metropolis.view.HelloWindow

fun main() {
    val controller = MetropolisController()

    application {
        HelloWindow(controller.state)
    }

}
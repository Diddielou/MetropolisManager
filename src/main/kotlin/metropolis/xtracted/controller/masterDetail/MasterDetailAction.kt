package metropolis.xtracted.controller.masterDetail

import androidx.compose.ui.graphics.vector.ImageVector
import metropolis.xtracted.controller.Action

class MasterDetailAction(
    override val name    : String,
    override val icon    : ImageVector? = null,
    override val enabled : Boolean) : Action {

        class Open(val id: Int) // Open new Editor on Button Click
        class Add()
        class Delete(val id: Int)
}
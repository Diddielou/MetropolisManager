package metropolis.xtracted.controller.masterDetail

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material.icons.filled.Sync
import androidx.compose.ui.graphics.vector.ImageVector
import metropolis.xtracted.controller.Action
import metropolis.xtracted.controller.editor.EditorController
import metropolis.xtracted.controller.lazyloading.LazyTableController
import metropolis.xtracted.repository.Identifiable

sealed class MasterDetailAction(
    override val name    : String,
    override val icon    : ImageVector? = null,
    override val enabled : Boolean) : Action {

        class SetSelected(val id: Int?)       : MasterDetailAction("Select entry", null, true)
        // Nicht sicher, ob das der korrekte Weg ist, Klassen zu erstellen, die generische Argumente entgegennehmen für die EditorController LazyTableController.
        // Lösung momentan: manueller Cast nach EditorController<D> im MasterDetailController.
        class Open<D : Identifiable>(val id: Int, val editor: EditorController<D>)  : MasterDetailAction("Open", Icons.Filled.OpenInNew, true)
        class Reload<D : Identifiable>(val explorer: LazyTableController<D>)        : MasterDetailAction("Reload", Icons.Filled.Sync, true)
        class Add()                     : MasterDetailAction("Add", Icons.Filled.Add, true)
        class Delete(enabled: Boolean)  : MasterDetailAction("Delete", Icons.Filled.Delete, enabled)
        class DeselectEditor()           : MasterDetailAction("Deselect editor", null, true)
}
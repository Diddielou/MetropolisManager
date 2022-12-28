package metropolis.xtracted.controller.masterDetail

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.ui.graphics.vector.ImageVector
import metropolis.cities.shared.data.City
import metropolis.xtracted.controller.Action
import metropolis.xtracted.controller.editor.EditorController
import metropolis.xtracted.controller.lazyloading.LazyTableController
import metropolis.xtracted.repository.Identifiable

sealed class MasterDetailAction(
    override val name    : String,
    override val icon    : ImageVector? = null,
    override val enabled : Boolean) : Action {

        //class SetSelected()       : MasterDetailAction<>("SetSelected", null, true) // TODO
        //class Open(val id: Int, val editor: EditorController<City>)  : MasterDetailAction("Open", Icons.Filled.OpenInNew, true)
        //class Reload(val explorer: LazyTableController<City>)          : MasterDetailAction("Reload", null, true)

    // TODO: how to make generic?
    //class Open(val id: Int, val editor: EditorController<Country>)  : MasterDetailAction("Open", Icons.Filled.OpenInNew, true)
    //class Reload(val explorer: LazyTableController<Country>)          : MasterDetailAction("Reload", null, true)


        class Add()               : MasterDetailAction("Add", Icons.Filled.Add, true)
        class Delete(val id: Int) : MasterDetailAction("Delete", Icons.Filled.Delete, true)
}
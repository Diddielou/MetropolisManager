package metropolis.xtracted.model

import metropolis.xtracted.controller.editor.EditorController
import metropolis.xtracted.controller.lazyloading.LazyTableController
import metropolis.xtracted.repository.Identifiable

data class MasterDetailState<T : Identifiable>(
    val title: String,
    val lazyTableController: LazyTableController<T>,
    val editorController: EditorController<T>)
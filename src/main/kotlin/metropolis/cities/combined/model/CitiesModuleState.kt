package metropolis.cities.combined.model

import metropolis.cities.shared.data.City
import metropolis.xtracted.controller.editor.EditorController
import metropolis.xtracted.controller.lazyloading.LazyTableController

data class CitiesModuleState(
    val title: String,
    val cityLazyTableController: LazyTableController<City>,
    val cityEditorController: EditorController<City>)
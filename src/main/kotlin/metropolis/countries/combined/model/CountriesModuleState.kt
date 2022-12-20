package metropolis.countries.combined.model

import metropolis.countries.shared.data.Country
import metropolis.xtracted.controller.editor.EditorController
import metropolis.xtracted.controller.lazyloading.LazyTableController

data class CountriesModuleState(
    val title: String,
    val countryLazyTableController: LazyTableController<Country>,
    val countryEditorController: EditorController<Country>)
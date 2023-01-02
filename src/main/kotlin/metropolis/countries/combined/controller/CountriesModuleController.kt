package metropolis.countries.combined.controller

import metropolis.countries.editor.controller.countryEditorController
import metropolis.countries.explorer.controller.countryLazyTableController
import metropolis.countries.shared.data.Country
import metropolis.xtracted.controller.editor.EditorController
import metropolis.xtracted.controller.lazyloading.LazyTableController
import metropolis.xtracted.controller.masterDetail.MasterDetailAction
import metropolis.xtracted.controller.masterDetail.MasterDetailController
import metropolis.xtracted.repository.CrudRepository
import metropolis.xtracted.repository.LazyRepository

class CountriesModuleController (
    private var initiallySelectedCountryId : Int,
    private val lazyRepository    : LazyRepository<Country>,
    private val crudRepository    : CrudRepository<Country>)
{
    private val countryLazyTableController = countryLazyTableController(lazyRepository, onSelectionChange = { showCountryInEditor(it) })
    private val countryEditorController = countryEditorController(initiallySelectedCountryId, crudRepository, onEditorAction = { reloadCountryExplorer() })

    val controller = MasterDetailController(
        title                      = "Countries",
        selectedId                 = initiallySelectedCountryId,
        initialLazyTableController = countryLazyTableController,
        initialEditorController    = countryEditorController,
        onNewTableController = { createNewCountryExplorer() },
        onNewEditorController = { createNewCountryEditor(it) }
    )

    private fun createNewCountryExplorer(): LazyTableController<Country> {
        return countryLazyTableController(lazyRepository, onSelectionChange = { showCountryInEditor(it) })
    }
    private fun createNewCountryEditor(id: Int): EditorController<Country> {
        return countryEditorController(id, crudRepository, onEditorAction = { reloadCountryExplorer() })
    }

    private fun showCountryInEditor(id: Int){
        controller.executeAction(MasterDetailAction.Open(id = id, editor = createNewCountryEditor(id = id)))
    }
    private fun reloadCountryExplorer(){
        controller.executeAction(MasterDetailAction.Reload(explorer = createNewCountryExplorer()))
        controller.executeAction(MasterDetailAction.SetSelected())
    }

}
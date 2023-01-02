package metropolis.cities.explorer.controller

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import metropolis.cities.shared.data.City
import metropolis.cities.shared.repository.CityColumn
import metropolis.xtracted.controller.lazyloading.LazyTableController
import metropolis.xtracted.model.IntColumn
import metropolis.xtracted.model.StringColumn
import metropolis.xtracted.repository.LazyRepository
import metropolis.xtracted.view.format

private const val ELLIPSES = "..."

fun cityLazyTableController(repository: LazyRepository<City>, onSelectionChange: (Int) -> Unit = {}) =
    LazyTableController(title = "Cities of the world",
        repository = repository,
        defaultItem = City(-999, ELLIPSES, ELLIPSES, ELLIPSES, 0),
        columns = listOf(
            StringColumn(header = "Name",
            width = 150.dp,
            alignment = Alignment.CenterStart,
            fixed = true,
            dbColumn = CityColumn.NAME,
            valueProvider = { it.name }
            ),
            StringColumn(header = "Country code",
                width           = 50.dp,
                alignment       = Alignment.Center,
                fixed           = true,
                dbColumn        = CityColumn.COUNTRY_CODE,
                valueProvider   = { it.countryCode }
            ),
            IntColumn(header    = "Population",
                width           = 100.dp,
                alignment       = Alignment.CenterStart,
                fixed           = true,
                dbColumn        = CityColumn.POPULATION,
                valueProvider   = { it.population },
                formatter = { it.format("%,d") }
            ),
            StringColumn(header = "Alternate names",
                width           = 350.dp,
                alignment       = Alignment.CenterStart,
                fixed           = false,
                dbColumn        = CityColumn.ALTERNATE_NAMES,
                valueProvider   = { it.alternateNames }
            ),
            /*
            StringColumn(header = "Last modified",
                width           = 100.dp,
                alignment       = Alignment.CenterStart,
                fixed           = false,
                dbColumn        = CityColumn.MODIFICATION_DATE,
                valueProvider   = { it.modificationDate.toString() }
            ),
             */
            // TODO Modified date only view
        ),
        onSelectionChange = onSelectionChange
    )
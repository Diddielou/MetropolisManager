package metropolis.countries.explorer.controller

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import metropolis.countries.shared.data.Country
import metropolis.countries.shared.repository.CountryColumn
import metropolis.xtracted.controller.lazyloading.LazyTableController
import metropolis.xtracted.model.DoubleColumn
import metropolis.xtracted.model.IntColumn
import metropolis.xtracted.model.StringColumn
import metropolis.xtracted.repository.LazyRepository
import metropolis.xtracted.view.format

private const val ELLIPSES = "..."

fun countryLazyTableController(repository: LazyRepository<Country>, onSelectionChange: (Int) -> Unit = {}) =
    LazyTableController(title       = "Countries of the world",
        repository  = repository,
        defaultItem = Country(-999, ELLIPSES, ELLIPSES, 0.0, 0),
        columns     = listOf(
            StringColumn(header        = "Name",
                width         = 150.dp,
                alignment     = Alignment.CenterStart,
                fixed         = true,
                dbColumn      = CountryColumn.NAME,
                valueProvider = { it.name }
            ),
            StringColumn(header        = "Capital",
                width         = 100.dp,
                alignment     = Alignment.CenterStart,
                fixed         = true,
                dbColumn      = CountryColumn.CAPITAL,
                valueProvider = { it.capital }
            ),
            DoubleColumn(header        = "Area (km²)",
                width         = 100.dp,
                alignment     = Alignment.CenterStart,
                fixed         = false,
                dbColumn      = CountryColumn.AREA_IN_SQKM,
                valueProvider = { it.areaSqm },
                formatter     = { it.format("%,.1f") }
            ),
            IntColumn(header        = "Population",
                width         = 100.dp,
                alignment     = Alignment.CenterStart,
                fixed         = false,
                dbColumn      = CountryColumn.POPULATION,
                valueProvider = { it.population },
                formatter     = { it.format("%,d") }
            )
        ),
        onSelectionChange = onSelectionChange
    )
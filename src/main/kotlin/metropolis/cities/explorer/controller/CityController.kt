package metropolis.cities.explorer.controller

import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import metropolis.cities.shared.data.City
import metropolis.cities.shared.repository.CityColumn
import metropolis.xtracted.controller.lazyloading.LazyTableController
import metropolis.xtracted.model.DoubleColumn
import metropolis.xtracted.model.IntColumn
import metropolis.xtracted.model.StringColumn
import metropolis.xtracted.repository.LazyRepository
import metropolis.xtracted.view.format
import java.time.LocalDate

private const val ELLIPSES = "..."

fun cityController(repository: LazyRepository<City>) =
    LazyTableController(title = "Cities of the world",
        repository = repository,
        defaultItem = City(-999, ELLIPSES, ELLIPSES, ELLIPSES, 0),
        //defaultItem = City(-999, ELLIPSES, null, null, 0.0, 0.0, ELLIPSES, ELLIPSES, ELLIPSES, null, null, 0, null, 0, ELLIPSES, LocalDate.now()),
        columns = listOf(
            StringColumn(header = "Name",
            width = 150.dp,
            alignment = Alignment.CenterStart,
            fixed = true,
            dbColumn = CityColumn.NAME,
            valueProvider = { it.name }
            ),
            StringColumn(header = "Alternate names",
                width           = 350.dp,
                alignment       = Alignment.CenterStart,
                fixed           = false,
                dbColumn        = CityColumn.ALTERNATE_NAMES,
                valueProvider   = { it.alternateNames }
            ),
            StringColumn(header = "Country code",
                width           = 100.dp,
                alignment       = Alignment.CenterStart,
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
            /*
            DoubleColumn(header = "Latitude",
                width           = 75.dp,
                alignment       = Alignment.CenterEnd,
                fixed           = false,
                dbColumn        = CityColumn.LATITUDE,
                valueProvider   = { it.latitude },
                formatter       = { it.format("%,.1f") }
            ),
            DoubleColumn(header = "Longitude",
                width           = 75.dp,
                alignment       = Alignment.CenterEnd,
                fixed           = false,
                dbColumn        = CityColumn.LONGITUDE,
                valueProvider   = { it.longitude },
                formatter       = { it.format("%,.1f") }
            ),
            IntColumn(header    = "m.a.s.l",
                width           = 75.dp,
                alignment       = Alignment.Center,
                fixed           = false,
                dbColumn        = CityColumn.DEM,
                valueProvider   = { it.masl },
                formatter       = { it.format(pattern = "%s m", nullFormat = "...") }
            ),
            StringColumn(header = "Timezone",
                width           = 150.dp,
                alignment       = Alignment.CenterStart,
                fixed           = false,
                dbColumn        = CityColumn.TIMEZONE,
                valueProvider   = { it.timeZone }
            ),
            StringColumn(header = "Last modified",
                width           = 100.dp,
                alignment       = Alignment.CenterStart,
                fixed           = false,
                dbColumn        = CityColumn.MODIFICATION_DATE,
                valueProvider   = { it.modificationDate.toString() }
            ),
             */
            // TODO Modified date only view
        ))
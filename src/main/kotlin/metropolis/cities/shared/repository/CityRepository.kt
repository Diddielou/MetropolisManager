package metropolis.cities.shared.repository

import metropolis.cities.shared.data.City
import metropolis.xtracted.data.DbColumn
import metropolis.xtracted.repository.LazyRepository
import metropolis.xtracted.repository.CrudRepository
import metropolis.cities.shared.repository.CityColumn.*
import metropolis.xtracted.repository.asSql
import java.time.LocalDate

private const val TABLE = "CITY"
enum class CityColumn : DbColumn {
    ID,
    NAME,
    ASCII_NAME,
    ALTERNATE_NAMES,
    LATITUDE,
    LONGITUDE,
    FEATURE_CLASS,
    FEATURE_CODE,
    COUNTRY_CODE,
    CC2,
    ADMIN1_CODE,
    ADMIN2_CODE,
    ADMIN3_CODE,
    ADMIN4_CODE,
    POPULATION,
    ELEVATION,
    DEM,
    TIMEZONE,
    MODIFICATION_DATE
}

fun cityLazyTableRepository(url: String) =
    LazyRepository(url = url,
        table = TABLE,
        dataColumns = listOf(
            ID,
            NAME,
            ALTERNATE_NAMES,
            COUNTRY_CODE,
            POPULATION
        /*
            ASCII_NAME,
            LATITUDE,
            LONGITUDE,
            FEATURE_CLASS,
            FEATURE_CODE,
            CC2,
            ADMIN1_CODE,
            ADMIN2_CODE,
            ELEVATION,
            DEM,
            TIMEZONE,
            MODIFICATION_DATE
         */
        ),
        idColumn = ID,
        mapper = {
            City(
                id = getInt(ID.name),
                name = getString(NAME.name),
                alternateNames = getString(ALTERNATE_NAMES.name),
                countryCode = getString(COUNTRY_CODE.name),
                population = getInt(POPULATION.name)
                /*
                asciiName = getString(ASCII_NAME.name),
                latitude = getDouble(LATITUDE.name),
                longitude = getDouble(LONGITUDE.name),
                featureClass = getString(FEATURE_CLASS.name),
                featureCode = getString(FEATURE_CODE.name),
                admin1Code = getString(ADMIN1_CODE.name),
                admin2Code = getString(ADMIN2_CODE.name),
                elevation = getInt(ELEVATION.name),
                masl = getInt(DEM.name),
                timeZone = getString(TIMEZONE.name),
                modificationDate = LocalDate.parse(getString(MODIFICATION_DATE.name))
                 */
                )
        })

fun cityCrudRepository(url: String) =
    CrudRepository(url = url,
        table = TABLE,
        idColumn = ID,
        dataColumns = mapOf(
            NAME            to { it.name.asSql() },
            ALTERNATE_NAMES to { it.alternateNames?.asSql() },
            COUNTRY_CODE    to { it.countryCode.asSql() },
            POPULATION      to { it.population.toString().asSql() }
            /*
            ASCII_NAME      to { it.asciiName?.asSql() },
            LATITUDE        to { it.latitude.toString().asSql() },
            LONGITUDE       to { it.longitude.toString().asSql() },
            FEATURE_CODE    to { it.featureCode.asSql() },
            ADMIN1_CODE     to { it.admin1Code?.asSql() },
            ADMIN2_CODE     to { it.admin2Code?.asSql() },
            ELEVATION       to { it.elevation?.toString()?.asSql() },
            DEM             to { it.masl.toString().asSql() },
            TIMEZONE        to { it.timeZone.asSql() }
            */
        ),
        mapper = { City( // Alle Felder ausser featureClass
            id              = getInt(ID.name),
            name            = getString(NAME.name),
            alternateNames  = getString(ALTERNATE_NAMES.name),
            countryCode     = getString(COUNTRY_CODE.name),
            population      = getInt(POPULATION.name)
        )},
        addStmt = "(${NAME}, ${LATITUDE}, ${LONGITUDE}, ${FEATURE_CLASS}, ${FEATURE_CODE}, ${COUNTRY_CODE}, ${POPULATION}, ${DEM}, ${TIMEZONE}, ${MODIFICATION_DATE}) VALUES ('',0.0,0.0,'','','',0,0,''," + LocalDate.now() + ")"
            /*
            asciiName = getString(ASCII_NAME.name),
            latitude = getDouble(LATITUDE.name),
            longitude = getDouble(LONGITUDE.name),
            featureClass = "P", // TODO
            featureCode = getString(FEATURE_CODE.name),
            admin1Code = getString(ADMIN1_CODE.name),
            admin2Code = getString(ADMIN2_CODE.name),
            elevation = getInt(ELEVATION.name),
            masl = getInt(DEM.name),
            timeZone = getString(TIMEZONE.name),
            modificationDate = LocalDate.parse(getString(MODIFICATION_DATE.name)))

            addStmt = "| ($NAME, $LATITUDE, $LONGITUTDE, $FEATURE_CLASS, $FEATURE_CODE, $COUNTRY_CODE, $POPULATION, $DEM, $TIMEZONE, MODIFICATION_DATE"+
                   "| VALUES ('',0.0,0.0,'','','',0,0,''," + LocalDate.now() + ")"
             */
    )
/*
create table CITY
(
    ID                INTEGER          primary key  AUTOINCREMENT,
    NAME              VARCHAR(200)     NOT NULL,
    ASCII_NAME        VARCHAR(200),
    ALTERNATE_NAMES   VARCHAR(10000),
    LATITUDE          DOUBLE           NOT NULL,
    LONGITUDE         DOUBLE           NOT NULL,
    FEATURE_CLASS     CHAR             NOT NULL,
    FEATURE_CODE      VARCHAR(10)      NOT NULL,
    COUNTRY_CODE      VARCHAR(2)       NOT NULL,
    CC2               CHAR(200),
    ADMIN1_CODE       VARCHAR(20),
    ADMIN2_CODE       VARCHAR(80),
    ADMIN3_CODE       VARCHAR(20),
    ADMIN4_CODE       VARCHAR(20),
    POPULATION        INTEGER          NOT NULL,
    ELEVATION         INTEGER,
    DEM               INTEGER          NOT NULL,
    TIMEZONE          VARCHAR(40)      NOT NULL,
    MODIFICATION_DATE DATE             NOT NULL
);

create unique index CITY_ID
    on CITY (ID);

create index CITY_COUNTRY_CODE
    on CITY (COUNTRY_CODE);

create index CITY_NAME
    on CITY (NAME);

create index CITY_TIMEZONE
    on CITY (TIMEZONE);

 */
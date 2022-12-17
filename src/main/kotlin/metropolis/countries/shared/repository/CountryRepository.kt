package metropolis.countries.shared.repository

import metropolis.countries.shared.data.Country
import metropolis.xtracted.data.DbColumn
import metropolis.countries.shared.repository.CountryColumn.*
import metropolis.xtracted.repository.CrudRepository
import metropolis.xtracted.repository.LazyRepository
import metropolis.xtracted.repository.asSql

private const val TABLE = "COUNTRY"
enum class CountryColumn : DbColumn {
    ISO_ALPHA2,
    ISO_ALPHA3,
    ISO_NUMERIC,
    FIPS_CODE,
    NAME,
    CAPITAL,
    AREA_IN_SQKM,
    POPULATION,
    CONTINENT,
    TLD,
    CURRENCY_CODE,
    CURRENCY_NAME,
    PHONE,
    POSTAL_CODE_FORMAT,
    POSTAL_CODE_REGEX,
    LANGUAGES,
    GEONAME_ID,
    NEIGHBOURS,
    EQUIVALENT_FIPS_CODE
}

fun countryLazyRepository(url: String) =
    LazyRepository(url         = url,
        table       = TABLE,
        dataColumns = listOf(
            ISO_NUMERIC,
            NAME,
            CAPITAL,
            AREA_IN_SQKM,
            POPULATION
            /*
            CONTINENT,
            ISO_ALPHA2,
            CURRENCY_NAME,
            CURRENCY_CODE,
            LANGUAGES,
            NEIGHBOURS
             */
        ),
        idColumn    = ISO_NUMERIC,
        mapper      = {
            Country(
                id           = getInt(ISO_NUMERIC.name),
                name         = getString(NAME.name),
                capital      = getString(CAPITAL.name),
                areaSqm      = getDouble(AREA_IN_SQKM.name),
                population   = getInt(POPULATION.name)
                /*
                    continent    = getString(CONTINENT.name),
                    isoAlpha2    = getString(ISO_ALPHA2.name),
                    currencyName = getString(CURRENCY_NAME.name),
                    currencyCode = getString(CURRENCY_CODE.name),
                    languages    = getString(LANGUAGES.name),
                    neighbours   = getString(NEIGHBOURS.name)) }
                 */
            )
        })

fun countryCrudRepository(url: String) =
    CrudRepository(url = url,
        table = TABLE,
        idColumn = ISO_NUMERIC,
        dataColumns = mapOf(
            NAME            to { it.name.asSql() },
            CAPITAL         to { it.capital?.asSql() },
            AREA_IN_SQKM    to { it.areaSqm.toString().asSql() },
            POPULATION      to { it.population.toString().asSql() }
            /*
            CONTINENT       to { it.continent.asSql() },
            ISO_ALPHA2      to { it.isoAlpha2.asSql() },
            CURRENCY_NAME   to { it.currencyName?.asSql() },
            CURRENCY_CODE   to { it.currencyCode?.asSql() },
            LANGUAGES       to { it.languages?.asSql() },
            NEIGHBOURS      to { it.neighbours?.asSql() }
             */
        ),
        mapper = { Country(
            id           = getInt(ISO_NUMERIC.name),
            name         = getString(NAME.name),
            capital      = getString(CAPITAL.name),
            areaSqm      = getDouble(AREA_IN_SQKM.name),
            population   = getInt(POPULATION.name)
        )}
        /*
        continent    = getString(CONTINENT.name),
        isoAlpha2    = getString(ISO_ALPHA2.name),
        currencyName = getString(CURRENCY_NAME.name),
        currencyCode = getString(CURRENCY_CODE.name),
        languages    = getString(LANGUAGES.name),
        neighbours   = getString(NEIGHBOURS.name))
         */
    )


/*
CREATE TABLE COUNTRY (
                         ISO_ALPHA2           CHAR(2)          NOT NULL,
                         ISO_ALPHA3           CHAR(3)          NOT NULL,
                         ISO_NUMERIC          INTEGER          PRIMARY KEY AUTOINCREMENT,
                         FIPS_CODE            VARCHAR(3),
                         NAME                 VARCHAR(200)     NOT NULL,
                         CAPITAL              VARCHAR(200),
                         AREA_IN_SQKM         DOUBLE PRECISION NOT NULL,
                         POPULATION           INTEGER          NOT NULL,
                         CONTINENT            CHAR(2)          NOT NULL,
                         TLD                  CHAR(3),
                         CURRENCY_CODE        CHAR(3),
                         CURRENCY_NAME        VARCHAR(20),
                         PHONE                VARCHAR(10),
                         POSTAL_CODE_FORMAT   VARCHAR(10),
                         POSTAL_CODE_REGEX    VARCHAR(30),
                         LANGUAGES            VARCHAR(30),
                         GEONAME_ID           BIGINT           NOT NULL,
                         NEIGHBOURS           VARCHAR(30),
                         EQUIVALENT_FIPS_CODE VARCHAR(3)
);

 */
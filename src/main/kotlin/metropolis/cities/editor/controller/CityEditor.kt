package metropolis.cities.editor.controller

import metropolis.cities.shared.data.City
import metropolis.xtracted.controller.editor.EditorController
import metropolis.xtracted.controller.editor.ch
import metropolis.xtracted.controller.editor.get
import metropolis.xtracted.model.*
import metropolis.xtracted.repository.CrudRepository

fun cityEditorController(id: Int, repository: CrudRepository<City>, onEditorAction: () -> Unit) : EditorController<City> {
    return EditorController(
        id = id,
        title = Message.TITLE,
        locale = ch,
        repository = repository,
        asData = { City(id = id,
                    name = it[Id.NAME],
                    alternateNames = it[Id.ALTERNATE_NAMES],
                    countryCode = it[Id.COUNTRY_CODE],
                    population = it[Id.POPULATION]
            /*
                asciiName = it[Id.ASCII_NAME],
                latitude = it[Id.LATITUDE],
                longitude = it[Id.LONGITUDE],
                featureCode = it[Id.FEATURE_CODE],
                admin1Code = it[Id.ADMIN1_CODE],
                admin2Code = it[Id.ADMIN2_CODE],
                elevation = it[Id.ELEVATION],
                masl = it[Id.MASL],
                timeZone = it[Id.TIMEZONE]
             */
            )},
        asAttributeList = { city -> listOf(
            /*
            asciiname, country_ocde, alternateNames, Population
             */
            (stringAttribute(id = Id.NAME,
                value = city.name,
                required = true,
                syntaxValidator = { (it.length <= 200).asValidationResult(Message.TOO_LONG) })
            ),
            (stringAttribute(id = Id.ALTERNATE_NAMES,
                value = city.alternateNames,
                required = false,
                syntaxValidator = { (it.length <= 10000).asValidationResult(Message.TOO_LONG) })
            ),
            (stringAttribute(id = Id.COUNTRY_CODE,
                value = city.countryCode,
                required = true,
                semanticValidator = { when {
                    it == null      -> ValidationResult(false, null)
                    it.length > 2   -> ValidationResult(false, Message.TOO_LONG)
                    it.length < 2   -> ValidationResult(false, Message.TOO_SHORT)
                    else            -> ValidationResult(true, null)
                } })
            ),
            (intAttribute(id = Id.POPULATION,
                value = city.population,
                required = true,
                semanticValidator = { when {
                    it == null -> ValidationResult(false,  null)
                    it < 500   -> ValidationResult(false, Message.TOO_LOW)
                    else       -> ValidationResult(true,  null)
                } })
            ),
            /*
            (stringAttribute(id = Id.ASCII_NAME,
                value = city.asciiName,
                required = false)),
            (doubleAttribute(id = Id.LATITUDE,
                value = city.latitude,
                required = true)),
            (doubleAttribute(id = Id.LONGITUDE,
                value = city.longitude,
                required = true)),
            (stringAttribute(id = Id.ADMIN1_CODE,
                value = city.admin1Code,
                required = false)),
            (stringAttribute(id = Id.ADMIN2_CODE,
                value = city.admin2Code,
                required = false)),
            (intAttribute(id = Id.ELEVATION,
                value = city.elevation,
                required = false)),
            (intAttribute(id = Id.MASL,
                value = city.masl,
                required = true,
                unit = "km²")),
            (stringAttribute(id = Id.TIMEZONE,
                value = city.timeZone,
                required = true))*/
        ) },
        onEditorAction = onEditorAction
    )
}

enum class Id(override val german: String, override val english: String) : AttributeId {
    NAME          ("Name",          "Name"),
    ASCII_NAME   ("Ascii name",   "Ascii name"),
    ALTERNATE_NAMES       ("Andere Namen",    "Alternate names"),
    LATITUDE      ("Breitengrad",   "Latitude"),
    LONGITUDE    ("Längengrad",   "Longitude"),
    FEATURE_CODE("Feature code", "Feature code"),
    COUNTRY_CODE ("Ländercode",  "Country code"),
    ADMIN1_CODE     ("Fips code",      "Fips code"),
    ADMIN2_CODE    ("Zweite Verwaltungsabteilung", "Second administrative division"),
    POPULATION    ("Bevölkerung", "Population"),
    ELEVATION    ("Höhe", "Elevation"),
    MASL    ("M.ü.M.", "M.a.s.l"),
    TIMEZONE    ("Zeitzone", "Timezone")
}

private enum class Message(override val german: String, override val english: String) : Translatable {
    TITLE             ("Städte Editor"   , "City Editor"),
    TOO_HIGH          ("zu hoch"         , "too high"),
    TOO_LOW           ("zu niedrig"      , "too low"),
    TOO_LONG          ("zu lang"         , "too long"),
    TOO_SHORT         ("zu kurz"         , "too short")
}
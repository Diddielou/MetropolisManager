package metropolis.countries.editor.controller

import metropolis.countries.shared.data.Country
import metropolis.xtracted.controller.editor.EditorController
import metropolis.xtracted.controller.editor.ch
import metropolis.xtracted.controller.editor.get
import metropolis.xtracted.model.*
import metropolis.xtracted.repository.CrudRepository

fun countryEditorController(id: Int, repository: CrudRepository<Country>, onEditorAction: () -> Unit) : EditorController<Country> {
    return EditorController(
        id = id,
        title = Message.TITLE,
        locale = ch,
        repository = repository,
        asData = {
            Country(id      = id,
                isoAlpha2   = it[Id.ISO_ALPHA2],
                name        = it[Id.NAME],
                capital     = it[Id.CAPITAL],
                areaSqm     = it[Id.AREA_SQM],
                population  = it[Id.POPULATION]
                )},
        asAttributeList = { country -> listOf(
            (stringAttribute(
                id       = Id.ISO_ALPHA2,
                value    = country.isoAlpha2,
                required = true,
                syntaxValidator = { when {
                    it.length > 2   -> ValidationResult(false, Message.TOO_LONG)
                    it.length < 2   -> ValidationResult(false, Message.TOO_SHORT)
                    else            -> ValidationResult(true, null)
                } })
            ),
            (stringAttribute(
                id       = Id.NAME,
                value    = country.name,
                required = true,
                syntaxValidator = { (it.length <= 200).asValidationResult(Message.TOO_LONG) })
            ),
            (stringAttribute(
                id              = Id.CAPITAL,
                value           = country.capital,
                required        = true,
                syntaxValidator = { (it.length <= 200).asValidationResult(Message.TOO_LONG) })
            ),
            (doubleAttribute(
                id                = Id.AREA_SQM,
                value             = country.areaSqm,
                required          = true,
                unit              = "km²")
            ),
            (intAttribute(
                id       = Id.POPULATION,
                value    = country.population,
                required = true)
            ),

            /*
            (stringAttribute(
                id       = Id.CONTINENT,
                value    = country.continent,
                required = true)),
            (stringAttribute(id = Id.ISO_ALPHA2,
                value = country.isoAlpha2,
                required = true)),
            (stringAttribute(id = Id.CURRENCY_NAME,
                value = country.currencyName,
                required = true)),
            (stringAttribute(id = Id.CURRENCY_CODE,
                value = country.currencyCode,
                required = true)),
            (stringAttribute(id = Id.LANGUAGES,
                value = country.languages,
                required = true)),
            (stringAttribute(id = Id.NEIGHBOURS,
                value = country.neighbours,
                required = true))
             */
        ) },
        onEditorAction = onEditorAction
    )
}

enum class Id(override val german: String, override val english: String) : AttributeId {
    NAME          ("Name",          "Name"),
    ISO_ALPHA2   ("ISO Alpha 2",   "ISO Alpha 2"),
    CAPITAL       ("Hauptstadt",    "Capital"),
    AREA_SQM      ("Fläche",        "Area"),
    POPULATION    ("Bevölkerung",   "Population"),
    CONTINENT     ("Kontinent",     "Continent"),
    CURRENCY_NAME ("Währung",       "Currency"),
    CURRENCY_CODE ("Währung Code",  "Currency code"),
    LANGUAGES     ("Sprachen",      "Languages"),
    NEIGHBOURS    ("Nachbarländer", "Neighbour countries")
}

private enum class Message(override val german: String, override val english: String) : Translatable {
    TITLE             ("Länder Editor"   , "Countries Editor"),
    TOO_HIGH          ("zu hoch"         , "too high"),
    TOO_LOW           ("zu niedrig"      , "too low"),
    TOO_LONG          ("zu lang"         , "too long"),
    TOO_SHORT         ("zu kurz"         , "too short")
}

/*
enum class Id(override val german: String, override val english: String) : AttributeId {
    NAME            ("Name"                 , "Name"),
    HEIGHT          ("Höhe"                 , "Height"),
    TYPE            ("Typ"                  , "Type"),
    REGION          ("Region"               , "Region"),
    CANTONS         ("Kantone"              , "Cantons"),
    RANGE           ("Gebiet"               , "Range"),
    ISOLATION_POINT ("Dominanz Punkt"       , "Isolation Point"),
    ISOLATION       ("Abstand"              , "Isolation"),
    PROMINENCE_POINT("Schartenhöhe Referenz", "Prominence Point"),
    PROMINENCE      ("Höhendifferenz"       , "Prominence"),
    PICTURE_URL     ("Bild URL"             , "Picture URL"),
    CAPTION         ("Bildunterschrift"     , "Caption"),
}

private enum class Message(override val german: String, override val english: String) : Translatable {
    TITLE             ("Schweizer Berg Editor"   , "Swiss Mountain Editor"),
    TOO_HIGH          ("zu hoch"                 , "too high"),
    TOO_LOW           ("zu niedrig"              , "too low"),
    NAME_TOO_LONG     ("Name zu lang"            , "name too long"),
    NOT_A_CANTON_LIST ("keine Liste von Kantonen", "not a canton list")
}

private val cantonsAsString = Canton.values().joinToString("|")
private val swissCantonsRegex = Regex("""^$|^(${cantonsAsString}|${cantonsAsString.lowercase()})(, (${cantonsAsString}|${cantonsAsString.lowercase()}))*$""")
 */
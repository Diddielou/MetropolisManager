package metropolis.cities.shared.data

import metropolis.xtracted.repository.Identifiable

data class City(override val id     : Int,
                val name            : String,
                val alternateNames  : String?,
                val countryCode     : String,
                val population      : Int) : Identifiable

/*
val asciiName       : String?,
val latitude        : Double,
val longitude       : Double,
val featureClass    : String = "P",
val featureCode     : String,
val admin1Code      : String?,
val admin2Code      : String?,
val elevation       : Int?,
val masl            : Int, // dem
val timeZone        : String,
val modificationDate : LocalDate = LocalDate.now()) : Identifiable
 */
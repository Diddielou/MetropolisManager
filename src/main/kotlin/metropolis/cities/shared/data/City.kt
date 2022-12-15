package metropolis.cities.shared.data

import java.time.LocalDate

data class City(val id              : Int,
                val name            : String,
                val asciiName       : String?,
                val alternateNames  : String?,
                val latitude        : Double,
                val longitude       : Double,
                val featureClass    : String,
                val featureCode     : String,
                val countryCode     : String,
                val cc2             : String?,
                val admin1Code      : String?,
                val admin2Code      : String?,
                val population      : Int,
                val elevation       : Int?,
                val masl            : Int, // dem
                val timeZone        : String,
                val modificationDate : LocalDate)
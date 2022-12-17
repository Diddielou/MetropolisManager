package metropolis.countries.shared.data

import metropolis.xtracted.repository.Identifiable

data class Country(override val id  : Int, // isoNumeric
                   val name        : String,
                   val capital     : String? = null,
                   val areaSqm     : Double,
                   val population  : Int,
                  ) : Identifiable

/*
                   val continent   : String,
                   val isoAlpha2   : String,
                   val currencyName: String? = null,
                   val currencyCode: String? = null,
                   val languages   : String? = null,
                   val neighbours  : String? = null
 */

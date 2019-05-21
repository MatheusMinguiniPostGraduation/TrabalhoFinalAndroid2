package ifsp.edu.br.minguini.matheus.trabalhoFinal2.util

import java.lang.Double

object ConstantesUtil {
    const val URL_BASE: String = "http://www.omdbapi.com"
    val OMDB_API_KEY: String = "eb1fece4"
    val APP_KEY_FIELD = "apikey"

    const val API_ERROR_MESSAGE = "There was an error consulting the API: "
    const val NO_MOVIE_FOUND_MESSAGE = "There was no movie found with the provided filter"
    const val NO_FILTERS_PROVIDED_MESSAGE = "Please, to look the movie up, type the description or/and the movie identifier."
    const val ID_MUST_BE_A_NUMBER_MESSAGE = "Please, the ID must contain only numeric digits."

    fun validateIdField(id: String, title: String) : Boolean{
        try {
            Double.parseDouble(id)
            return true
        } catch (e: NumberFormatException) {
            return false
        }
    }
}

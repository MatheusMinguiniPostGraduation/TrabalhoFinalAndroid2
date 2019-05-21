package ifsp.edu.br.minguini.matheus.trabalhoFinal2.util

import java.lang.Double

object ConstantesUtil {
    const val URL_BASE: String = "http://www.omdbapi.com"
    val OMDB_API_KEY: String = "eb1fece4"
    val APP_KEY_FIELD = "apikey"

    fun validateIdField(id: String, title: String) : Boolean{
        try {
            Double.parseDouble(id)
            return true
        } catch (e: NumberFormatException) {
            return false
        }
    }
}

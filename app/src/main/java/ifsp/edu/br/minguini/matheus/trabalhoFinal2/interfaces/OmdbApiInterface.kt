package ifsp.edu.br.minguini.matheus.trabalhoFinal2.interfaces

import ifsp.edu.br.minguini.matheus.trabalhoFinal2.dto.MovieDTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface OmdbApi {

    @GET("/") //summary: Returns the most popular match for a given title
    fun getMovieByTitle(@Query("t") titulo: String): Call<MovieDTO>

    @GET("/") //summary: Returns the most popular match for a given title
    fun getMovieById(@Query("i") id: String): Call<MovieDTO>

}
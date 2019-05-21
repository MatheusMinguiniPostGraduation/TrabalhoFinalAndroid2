package ifsp.edu.br.minguini.matheus.trabalhoFinal2.model

import ifsp.edu.br.minguini.matheus.trabalhoFinal2.MainActivity
import ifsp.edu.br.minguini.matheus.trabalhoFinal2.dto.MovieDTO
import ifsp.edu.br.minguini.matheus.trabalhoFinal2.interfaces.MovieCallbackInterface
import ifsp.edu.br.minguini.matheus.trabalhoFinal2.interfaces.OmdbApi
import ifsp.edu.br.minguini.matheus.trabalhoFinal2.util.ConstantesUtil
import ifsp.edu.br.minguini.matheus.trabalhoFinal2.util.ConstantesUtil.API_ERROR_MESSAGE
import ifsp.edu.br.minguini.matheus.trabalhoFinal2.util.ConstantesUtil.NO_MOVIE_FOUND_MESSAGE
import kotlinx.android.synthetic.main.frame_main.*
import okhttp3.OkHttpClient
import org.jetbrains.anko.design.snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class OmdbImpl : Callback<MovieDTO>{

    var api: OmdbApi? = null

    var mainActivity: MainActivity? = null;

    val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()

    var callback: MovieCallbackInterface? = null

    constructor(retrofit : Retrofit, mainActivity: MainActivity){
        if(api == null){
            Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(ConstantesUtil.URL_BASE).client(okHttpClientBuilder.build()).build()
        }

        this.mainActivity = mainActivity
    }



    override fun onFailure(call: Call<MovieDTO>, t: Throwable) {
        mainActivity?.mainLl?.snackbar(API_ERROR_MESSAGE + t.message)
    }

    override fun onResponse(call: Call<MovieDTO>, response: Response<MovieDTO>) {
        val body = response.body()
        if (body != null) {
            callback?.onResponse(body)
        }else{
            mainActivity?.mainLl?.snackbar(NO_MOVIE_FOUND_MESSAGE)
        }
    }

}
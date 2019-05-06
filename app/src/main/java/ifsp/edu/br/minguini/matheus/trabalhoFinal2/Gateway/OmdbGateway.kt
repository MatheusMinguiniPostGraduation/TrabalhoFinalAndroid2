package ifsp.edu.br.minguini.matheus.trabalhoFinal2.Gateway

import android.widget.ImageView
import com.squareup.picasso.Picasso
import ifsp.edu.br.minguini.matheus.trabalhoFinal2.Dto.MovieDTO
import ifsp.edu.br.minguini.matheus.trabalhoFinal2.MainActivity
import ifsp.edu.br.minguini.matheus.trabalhoFinal2.interfaces.MovieCallbackInterface
import ifsp.edu.br.minguini.matheus.trabalhoFinal2.interfaces.OmdbApi
import ifsp.edu.br.minguini.matheus.trabalhoFinal2.util.ConstantesUtil.APP_KEY_FIELD
import ifsp.edu.br.minguini.matheus.trabalhoFinal2.util.ConstantesUtil.OMDB_API_KEY
import ifsp.edu.br.minguini.matheus.trabalhoFinal2.util.ConstantesUtil.URL_BASE
import kotlinx.android.synthetic.main.frame_main.*
import kotlinx.android.synthetic.main.main_fragment.view.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jetbrains.anko.design.snackbar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class OmdbGateway(val mainActivity: MainActivity) {

    val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()

    var callback: MovieCallbackInterface? = null

    // Instanciando o cliente HTTP
    init {
        // Adiciona um interceptador que é um objeto de uma classe anônima
        okHttpClientBuilder.addInterceptor(object : Interceptor {
            override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
                // Resgatando requisição interceptada
                val reqInterceptada: Request = chain.request()
                // Criando nova requisição a partir da interceptada e adicionando campos de cabeçalho
                val url = reqInterceptada.url().newBuilder().addQueryParameter(APP_KEY_FIELD, OMDB_API_KEY).build()
                val novaReq: Request = reqInterceptada.newBuilder()
                    .url(url)
                    .method(reqInterceptada.method(), reqInterceptada.body())
                    .build()
                // retornando a nova requisição preenchdia
                return chain.proceed(novaReq)
            }
        })
    }

    val retrofit: Retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(URL_BASE)
        .client(okHttpClientBuilder.build()).build()


    // Cria um objeto, a partir da Interface Retrofit, que contém as funções de requisição
    val api: OmdbApi = retrofit.create(OmdbApi::class.java)

    fun searchMovie(titulo: String,  id: String) {

        if(id.isEmpty() && titulo.isEmpty()){
            mainActivity.mainLl.snackbar("Please, to look the movie up, type the description or/and the movie identifier.");
        }else{
            var parameter = id

            if(id.isEmpty()){
                parameter = titulo
            }

            api.getMovie(parameter).enqueue(
                object : Callback<MovieDTO> {

                    override fun onFailure(call: Call<MovieDTO>, t: Throwable) {
                        mainActivity.mainLl.snackbar("Erro ao consultar API: " + t.message);
                    }

                    override fun onResponse(call: Call<MovieDTO>, response: Response<MovieDTO>) {
                        val body = response.body()
                        if (body != null) {
                            callback?.onResponse(body)
                        }
                    }

                }
            )
        }



    }
}


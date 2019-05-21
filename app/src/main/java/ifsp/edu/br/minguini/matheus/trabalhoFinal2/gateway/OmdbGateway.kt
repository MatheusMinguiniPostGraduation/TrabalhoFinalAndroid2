package ifsp.edu.br.minguini.matheus.trabalhoFinal2.Gateway

import ifsp.edu.br.minguini.matheus.trabalhoFinal2.MainActivity
import ifsp.edu.br.minguini.matheus.trabalhoFinal2.interfaces.OmdbApi
import ifsp.edu.br.minguini.matheus.trabalhoFinal2.model.OmdbImpl
import ifsp.edu.br.minguini.matheus.trabalhoFinal2.util.ConstantesUtil.APP_KEY_FIELD
import ifsp.edu.br.minguini.matheus.trabalhoFinal2.util.ConstantesUtil.ID_MUST_BE_A_NUMBER_MESSAGE
import ifsp.edu.br.minguini.matheus.trabalhoFinal2.util.ConstantesUtil.NO_FILTERS_PROVIDED_MESSAGE
import ifsp.edu.br.minguini.matheus.trabalhoFinal2.util.ConstantesUtil.OMDB_API_KEY
import ifsp.edu.br.minguini.matheus.trabalhoFinal2.util.ConstantesUtil.URL_BASE
import kotlinx.android.synthetic.main.frame_main.*
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import org.jetbrains.anko.design.snackbar
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Double.parseDouble


class OmdbGateway(val mainActivity: MainActivity) {

    val okHttpClientBuilder: OkHttpClient.Builder = OkHttpClient.Builder()

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

    fun searchMovie(title: String, id: String) {
        if(isParametersEmpty(id, title)){
            mainActivity.mainLl.snackbar(NO_FILTERS_PROVIDED_MESSAGE);
        }else{
            if(!id.isBlank()) {
                if(validateIdField(id, title)){
                    getMovieById(id)
                }else {
                    mainActivity.mainLl.snackbar(ID_MUST_BE_A_NUMBER_MESSAGE);
                }
            }else{
                getMovieByTitle(title)
            }
        }
    }

    val implementation : OmdbImpl = OmdbImpl(retrofit, mainActivity)

    private fun isParametersEmpty(id: String, titulo: String) = id.isEmpty() && titulo.isEmpty()

    fun getMovieByTitle(title: String){
        api.getMovieByTitle(title).enqueue(implementation)
    }

    fun getMovieById(id: String){
        api.getMovieById(id).enqueue(implementation)
    }

    private fun validateIdField(id: String, title: String) : Boolean{
        try {
            parseDouble(id)
            return true
        } catch (e: NumberFormatException) {
            return false
        }
    }
}


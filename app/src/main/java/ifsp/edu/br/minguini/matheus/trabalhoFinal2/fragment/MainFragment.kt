package ifsp.edu.br.minguini.matheus.trabalhoFinal2.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ifsp.edu.br.minguini.matheus.trabalhoFinal2.R

class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layoutView = inflater.inflate(R.layout.main_fragment, null, false)

        /*val omdbFilmes = OmdbFilmes(activity as MainActivity)

        /*layoutView.btnBuscar.setOnClickListener {
            omdbFilmes.pesquisarFilme(layoutView.txtBusca_textInputEditText.text.toString())
        }*/

        omdbFilmes.callback = object : OmdbFilmes.MovieCallback {
            override fun onResponse(obj: Teste) {
                val url = obj.poster
                if(url != null) {
                    layoutView.ImageMovie.loadPicasso(url)
                }
            }
        }*/

        return layoutView
    }

}
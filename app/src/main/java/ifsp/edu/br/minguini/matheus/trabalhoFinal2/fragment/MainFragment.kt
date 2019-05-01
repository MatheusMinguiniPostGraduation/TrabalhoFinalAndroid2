package ifsp.edu.br.minguini.matheus.trabalhoFinal2.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso
import ifsp.edu.br.minguini.matheus.trabalhoFinal2.Dto.MovieDTO
import ifsp.edu.br.minguini.matheus.trabalhoFinal2.Gateway.OmdbGateway
import ifsp.edu.br.minguini.matheus.trabalhoFinal2.MainActivity
import ifsp.edu.br.minguini.matheus.trabalhoFinal2.R
import ifsp.edu.br.minguini.matheus.trabalhoFinal2.interfaces.MovieCallbackInterface
import kotlinx.android.synthetic.main.main_fragment.view.*


class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val layoutView = inflater.inflate(R.layout.main_fragment, null, false)

        val moviesAPI = OmdbGateway(activity as MainActivity)

        layoutView.searchMovieButton.setOnClickListener {
            moviesAPI.searchMovie(layoutView.movieDescriptionEditText.text.toString())
        }

        moviesAPI.callback = object : MovieCallbackInterface {
            override fun onResponse(obj: MovieDTO) {
                val url = obj.poster
                if (url != null) {
                    layoutView.movieImageView.loadImageIntoView(url)
                }
            }
        }
        return layoutView
    }

    fun ImageView.loadImageIntoView(url: String) {
        Picasso.get().load(url).into(this)
    }

}
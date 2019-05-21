package ifsp.edu.br.minguini.matheus.trabalhoFinal2.interfaces

import ifsp.edu.br.minguini.matheus.trabalhoFinal2.dto.MovieDTO

interface MovieCallbackInterface {
    fun onResponse(obj: MovieDTO)
}
package br.com.ubook.desafioubook.mvp.model

import br.com.ubook.desafioubook.domain.ResultMoviesList
import br.com.ubook.desafioubook.mvp.interfaces.APIMovie
import br.com.ubook.desafioubook.mvp.presenter.PresenterListMovies
import br.com.ubook.desafioubook.network.APINetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModelListMovies : APIMovie.ModelListMovieImpl {

    var repository: APINetwork.ListMovieRepositoryImpl
    var presenterListMovies: PresenterListMovies

    constructor(repository: APINetwork.ListMovieRepositoryImpl,
                presenterListMovies: PresenterListMovies) {
        this.repository = repository
        this.presenterListMovies = presenterListMovies
    }

    override fun requestListMovies() {
        this.repository.request(object : Callback<ResultMoviesList> {

            override fun onFailure(call: Call<ResultMoviesList>, t: Throwable) {
                presenterListMovies.requestListMoviesError()
            }

            override fun onResponse(call: Call<ResultMoviesList>, response: Response<ResultMoviesList>) {
                if (response.isSuccessful) {
                    presenterListMovies.requestListMoviesSucess(response.body()!!)
                } else {
                    presenterListMovies.requestListMoviesError()
                }
            }
        })
    }
}
package br.com.ubook.desafioubook.mvp.presenter

import br.com.ubook.desafioubook.domain.ResultMoviesList
import br.com.ubook.desafioubook.mvp.interfaces.APIMovie
import br.com.ubook.desafioubook.mvp.model.ModelListMovies
import br.com.ubook.desafioubook.network.APINetwork

class PresenterListMovies: APIMovie.PresenterListMovieImpl {

    lateinit var view: APIMovie.ViewListMovieImpl
    lateinit var repository: APINetwork.ListMovieRepositoryImpl
    lateinit var model: APIMovie.ModelListMovieImpl

    override fun bind(view: APIMovie.ViewListMovieImpl, repository: APINetwork.ListMovieRepositoryImpl) {
        this.repository = repository
        this.view = view
        this.model = ModelListMovies(repository, this)
    }

    override fun requestListMovies() {
        model.requestListMovies()
    }

    override fun requestListMoviesStart() {
        requestListMovies()
    }

    override fun requestListMoviesSucess(listMovies: ResultMoviesList) {
        view.requestListMoviesSucess(listMovies)
    }

    override fun requestListMoviesError() {
        view.requestListMoviesError()
    }
}
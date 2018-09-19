package br.com.ubook.desafioubook.mvp.interfaces

import br.com.ubook.desafioubook.domain.Movie
import br.com.ubook.desafioubook.domain.ResultMoviesList
import br.com.ubook.desafioubook.network.APINetwork

interface APIMovie {

    interface PresenterListMovieImpl{

        fun bind(view: ViewListMovieImpl, repository: APINetwork.ListMovieRepositoryImpl)
        fun requestListMovies()

        fun requestListMoviesStart()
        fun requestListMoviesSucess(resultMoviesList: ResultMoviesList)
        fun requestListMoviesError()
    }

    interface ModelListMovieImpl{
        fun requestListMovies()
    }

    interface ViewListMovieImpl{
        fun requestListMoviesStart()

        fun requestListMoviesSucess(resultMoviesList: ResultMoviesList)

        fun requestListMoviesError()
    }

}
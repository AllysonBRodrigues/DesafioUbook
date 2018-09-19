package br.com.ubook.desafioubook.network

import br.com.ubook.desafioubook.BuildConfig
import br.com.ubook.desafioubook.domain.Movie
import br.com.ubook.desafioubook.domain.ResultMoviesList
import retrofit2.Callback

class ListMoviesManager(val function: String): APINetwork.ListMovieRepositoryImpl {

    override fun request(callback: Callback<ResultMoviesList>) {
        RetrofitService().getService(APINetwork.ListMoviesRequestFunctionsImpl::class.java, BuildConfig.BASE_URL).getTopRatedMovie(function,BuildConfig.API_KEY, BuildConfig.LANGUAGE).enqueue(callback)
    }
}
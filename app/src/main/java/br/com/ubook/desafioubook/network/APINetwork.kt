package br.com.ubook.desafioubook.network

import br.com.ubook.desafioubook.domain.Movie
import br.com.ubook.desafioubook.domain.ResultMoviesList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.Callback
import retrofit2.http.Query

interface APINetwork {

    interface ListMovieRepositoryImpl{
        fun request(callback: Callback<ResultMoviesList>)
    }

    interface ListMoviesRequestFunctionsImpl{
        @GET("{function}")
        fun getTopRatedMovie(@Path("function")function: String,@Query("api_key") api_key: String, @Query("language") language: String ): Call<ResultMoviesList>
    }


}
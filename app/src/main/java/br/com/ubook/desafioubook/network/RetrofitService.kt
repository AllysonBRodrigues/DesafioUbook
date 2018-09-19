package br.com.ubook.desafioubook.network

import com.google.gson.Gson
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitService {

    private fun retrofitBuilder(url: String): Retrofit {

        val pool = ConnectionPool(5, 10000, TimeUnit
                .MILLISECONDS)

        val client = OkHttpClient.Builder()
                .connectionPool(pool)
                .build()

        return Retrofit.Builder()
                .client(client)
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .build()
    }


    fun <T> getService(tClass: Class<T>, url: String): T {
        return retrofitBuilder(url)
                .create(tClass)
    }

}
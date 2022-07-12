package studio.sanguine.codingtest

import android.app.Application
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import studio.sanguine.codingtest.interfaces.RetrofitInterface

class App : Application() {
    companion object {
        fun createRetrofit() : RetrofitInterface {
            val BASE_URL = "https://mocki.io/v1/"
            val builder = Retrofit.Builder()
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return builder.create(RetrofitInterface::class.java)
        }

    }
}
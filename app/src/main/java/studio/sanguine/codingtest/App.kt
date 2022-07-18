package studio.sanguine.codingtest

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import studio.sanguine.codingtest.interfaces.RetrofitInterface

@HiltAndroidApp
class App : Application() {

}
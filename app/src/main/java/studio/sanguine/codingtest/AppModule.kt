package studio.sanguine.codingtest

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import studio.sanguine.codingtest.interfaces.RetrofitInterface
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class AppModule {

    @Singleton
    @Provides
    fun getRetroApi(): RetrofitInterface {
        val BASE_URL = "https://mocki.io/v1/"
        val builder = Retrofit.Builder()
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return builder.create(RetrofitInterface::class.java)
    }

}
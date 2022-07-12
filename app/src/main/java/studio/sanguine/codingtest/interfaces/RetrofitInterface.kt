package studio.sanguine.codingtest.interfaces

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url
import studio.sanguine.codingtest.models.Movie

interface RetrofitInterface {
    @GET("a53fc407-51f5-4e7f-be2d-9d593e2d08f1")
    suspend fun getMovies() : Response<List<Movie>>

    //call is async afaik
    @GET
    fun fetchImage(@Url url: String) : Call<ResponseBody>
}
package studio.sanguine.codingtest.viewmodels

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import studio.sanguine.codingtest.repositories.Repository

class MovieDetailsViewModel(val repository: Repository) : ViewModel() {
    lateinit var bitmap: Bitmap
    val callback = MyCallback()

    fun getImage(url: String){
        val call : Call<ResponseBody> = repository.inter.fetchImage(url)

        call.enqueue(callback)


    }
}

class MyCallback : Callback<ResponseBody> {

    lateinit var bitmap: Bitmap
    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {

    }

    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

    }

}
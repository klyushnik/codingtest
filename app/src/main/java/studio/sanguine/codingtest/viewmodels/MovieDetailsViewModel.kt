package studio.sanguine.codingtest.viewmodels

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import studio.sanguine.codingtest.repositories.Repository

class MovieDetailsViewModel(val repository: Repository) : ViewModel() {
    lateinit var bitmap: MutableLiveData<Bitmap>
    init {
        bitmap = MutableLiveData()
    }

    val getBitmap = fun(bmp: Bitmap){
        bitmap.postValue(bmp)
    }
    val callback = MyCallback(getBitmap)

    fun getImage(url: String){
        val call : Call<ResponseBody> = repository.inter.fetchImage(url)

        call.enqueue(callback)

    }

}

class MyCallback(val getBitmap : (Bitmap) -> Unit) : Callback<ResponseBody> {

    lateinit var bitmap: Bitmap

    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
        bitmap = BitmapFactory.decodeStream(response.body()?.byteStream())
        getBitmap(bitmap)
    }

    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {

    }

}
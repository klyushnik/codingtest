package studio.sanguine.codingtest.viewmodels

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import studio.sanguine.codingtest.R
import studio.sanguine.codingtest.repositories.Repository

class MovieDetailsViewModel(val repository: Repository) : ViewModel() {
    lateinit var bitmap: MutableLiveData<Bitmap>
    init {
        bitmap = MutableLiveData()
    }

    val getBitmap = fun(bmp: Bitmap?){
        bitmap.postValue(bmp)
    }
    val callback = MyCallback(getBitmap)

    fun getImage(url: String){
        val call : Call<ResponseBody> = repository.inter.fetchImage(url)

        call.enqueue(callback)

    }

}

class MyCallback(val getBitmap : (Bitmap?) -> Unit) : Callback<ResponseBody> {

    var bitmap: Bitmap? = null

    override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
        try {
            bitmap = BitmapFactory.decodeStream(response.body()?.byteStream())
            getBitmap(bitmap)
        }catch(e: Exception){
            bitmap = null
            Log.e("decoding error", "empty stream")
        }
    }

    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
        bitmap = BitmapFactory.decodeResource(Resources.getSystem(), R.drawable.ic_launcher_foreground)
    }

}
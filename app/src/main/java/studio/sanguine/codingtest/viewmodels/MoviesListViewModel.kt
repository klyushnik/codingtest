package studio.sanguine.codingtest.viewmodels

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import studio.sanguine.codingtest.models.Movie
import studio.sanguine.codingtest.repositories.Repository

class MoviesListViewModel(val repo: Repository) : ViewModel() {
    var moviesList : MutableLiveData<List<Movie>>
    init {
        moviesList = MutableLiveData()
    }

    fun fetchMovies(){
        CoroutineScope(Dispatchers.IO).launch {
            val response = repo.getMovies()
            if(response.isSuccessful){
                moviesList.postValue(response.body())
            }else{
                val emptyList = ArrayList<Movie>()
                moviesList.postValue(emptyList)
                Log.e("response", "unsuccessful")
            }
        }
    }
}
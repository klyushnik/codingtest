package studio.sanguine.codingtest.repositories

import studio.sanguine.codingtest.interfaces.RetrofitInterface
import javax.inject.Inject

class Repository @Inject constructor(val inter: RetrofitInterface) {

    suspend fun getMovies() = inter.getMovies()

}
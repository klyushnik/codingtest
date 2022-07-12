package studio.sanguine.codingtest.repositories

import studio.sanguine.codingtest.interfaces.RetrofitInterface

class Repository(val inter: RetrofitInterface) {
    suspend fun getMovies() = inter.getMovies()

}
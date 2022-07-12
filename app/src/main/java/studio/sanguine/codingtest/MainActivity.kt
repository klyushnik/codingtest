package studio.sanguine.codingtest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import studio.sanguine.codingtest.models.Movie
import studio.sanguine.codingtest.repositories.Repository
import studio.sanguine.codingtest.viewmodels.MoviesListViewModel

class MainActivity : AppCompatActivity() {
    lateinit var moviesList : ArrayList<Movie>

    lateinit var adapter: MovieListAdapter

    lateinit var viewModel: MoviesListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repo = Repository(App.createRetrofit())
        viewModel = MoviesListViewModel(repo)


        moviesList = ArrayList()
        adapter = MovieListAdapter(moviesList, openDetails)

        val recyclerView = findViewById<RecyclerView>(R.id.main_recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel.moviesList.observe(this){
            moviesList.clear()
            moviesList.addAll(it)
            adapter.notifyDataSetChanged()
        }

        viewModel.fetchMovies()

    }

    val openDetails = fun(movie: Movie) {
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra("movie", movie)
        startActivity(intent)
    }

}
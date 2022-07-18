package studio.sanguine.codingtest

import android.content.Intent
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import studio.sanguine.codingtest.interfaces.RetrofitInterface
import studio.sanguine.codingtest.models.Movie
import studio.sanguine.codingtest.repositories.Repository
import studio.sanguine.codingtest.viewmodels.MoviesListViewModel
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var moviesList : ArrayList<Movie>

    lateinit var adapter: MovieListAdapter

    @Inject
    lateinit var viewModel: MoviesListViewModel

    lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        preferences = getSharedPreferences("prefs", MODE_PRIVATE)
        supportActionBar?.title = getString(R.string.title)

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

        localize()

    }

    val openDetails = fun(movie: Movie) {
        val intent = Intent(this, MovieDetailsActivity::class.java)
        intent.putExtra("movie", movie)
        startActivity(intent)
    }

    //inflate options menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    //launch settings activity - since there is only one item, no need to check which menu item is selected
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val intent = Intent(this, SettingsActivity::class.java)
        startforResult.launch(intent)

        return super.onOptionsItemSelected(item)
    }

    //handle localization changes
    val startforResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result : ActivityResult ->

        if(result.resultCode == RESULT_OK){
            localize()
        }
    }

    fun localize(){
        val config = baseContext.resources.configuration
        val targetLocaleStr = preferences.getString("app_language", "en")

        config.setLocale(Locale(targetLocaleStr!!))
        baseContext.resources.updateConfiguration(config, baseContext.resources.displayMetrics)
        supportActionBar?.title = getString(R.string.title)
        adapter.notifyDataSetChanged()
    }
}


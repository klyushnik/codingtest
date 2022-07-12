package studio.sanguine.codingtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import org.w3c.dom.Text
import studio.sanguine.codingtest.R
import studio.sanguine.codingtest.models.Movie
import studio.sanguine.codingtest.repositories.Repository
import studio.sanguine.codingtest.viewmodels.MovieDetailsViewModel

class MovieDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val movie = intent.getSerializableExtra("movie")

        val vm = MovieDetailsViewModel(Repository(App.createRetrofit()))

        val quoteTextView = findViewById<TextView>(R.id.details_quoteTextView)
        val actorNameTextView = findViewById<TextView>(R.id.details_actorNameTextView)
        val imageView = findViewById<ImageView>(R.id.details_imageView)
        var url = ""

        movie?.let{
            quoteTextView.setText((it as Movie).content)
            actorNameTextView.setText((it as Movie).actorName)
            url = (it as Movie).imagethumburl ?: "https://encrypted-tbn2.gstatic.com/images?q=tbn:ANd9GcSqoqCXj79wy81IYW05vx6FPlsc9RIeBAeTKR37QKwRoFOaoxtBL0P8RR8"
        }

        vm.bitmap.observe(this){
            imageView.setImageBitmap(it)
        }

        vm.getImage(url)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()

    }
}
package studio.sanguine.codingtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.w3c.dom.Text
import studio.sanguine.codingtest.R
import studio.sanguine.codingtest.models.Movie

class MovieDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val movie = intent.getSerializableExtra("movie")

        val quoteTextView = findViewById<TextView>(R.id.details_quoteTextView)
        val actorNameTextView = findViewById<TextView>(R.id.details_actorNameTextView)

        movie?.let{
            quoteTextView.setText((it as Movie).content)
            actorNameTextView.setText((it as Movie).actorName)
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onBackPressed() {
        super.onBackPressed()

    }
}
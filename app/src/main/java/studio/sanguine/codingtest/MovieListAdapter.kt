package studio.sanguine.codingtest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import studio.sanguine.codingtest.models.Movie

class MovieListAdapter(val data: ArrayList<Movie>, val openDetails: (Movie) -> Unit) : RecyclerView.Adapter<ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.movie_item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data.get(position)
        var genresString = ""
        item.categories?.let{
            for(string in it){
                genresString += "$string "
            }
        }
        val yearText = item.year.toString()

        holder.titleTextView.setText(item.title ?: "")
        holder.genreTextView.setText(genresString)
        holder.releaseYearTextView.setText(yearText)

        holder.rootView.setOnClickListener {
            openDetails(item)
        }



    }

    override fun getItemCount(): Int {
        return data.size
    }
}

class ViewHolder(view: View): RecyclerView.ViewHolder(view){
    val rootView = view.findViewById<ConstraintLayout>(R.id.item_rootLayout)
    val titleTextView = view.findViewById<TextView>(R.id.item_movieTitleTextView)
    val genreTextView = view.findViewById<TextView>(R.id.item_genreTextView)
    val movieImageView = view.findViewById<ImageView>(R.id.item_movieImageView)
    val releaseYearTextView = view.findViewById<TextView>(R.id.item_releaseDateTextView)
}
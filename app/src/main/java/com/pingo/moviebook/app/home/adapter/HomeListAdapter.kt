package com.pingo.moviebook.app.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pingo.moviebook.R
import com.pingo.moviebook.shared.ext.showImage
import com.pingo.moviebook.shared.models.Result
import kotlinx.android.synthetic.main.item_movie_list.view.*


/**
 * [RecyclerView.Adapter] displaying movie list.
 */
class HomeListAdapter : RecyclerView.Adapter<HomeListAdapter.MovieViewHolder>() {

    private var results = ArrayList<Result?>()
    private var preExpPosition = -1

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(viewGroup.context).inflate(
                R.layout.item_movie_list, viewGroup, false
            )
        )
    }

    /**
     * Binds data with view holder
     * @param viewHolder MovieViewHolder
     * @param position Int
     */
    override fun onBindViewHolder(viewHolder: MovieViewHolder, position: Int) {

        val movie = results[viewHolder.adapterPosition]

        viewHolder.bind(movie)

        viewHolder.itemView.setOnClickListener {
        }
    }

    /**
     * Item Count
     * @return Int
     */
    override fun getItemCount() = results.size


    /**
     * Appends existing results with the new ones
     * @param results List<Result>
     */
    fun updateResults(results: List<Result?>) {
        preExpPosition = -1
        this.results.clear()
        this.results.addAll(results)
        notifyDataSetChanged()
    }


    /**
     * View holder
     * @property view View
     * @constructor
     */
    inner class MovieViewHolder(private var view: View) : RecyclerView.ViewHolder(view) {

        // bind data with views
        fun bind(movie: Result?) {

            view.tvMovieTitle.text = movie?.originalTitle
            view.tvMovieRating.text = movie?.voteAverage.toString()
            view.ivMoviePoster.showImage("http://image.tmdb.org/t/p/w185" + movie?.posterPath)
        }
    }

    fun addData(listItems: ArrayList<Result>) {
        var size = this.results.size
        this.results.addAll(listItems)
        var sizeNew = this.results.size
//        notifyItemRangeChanged(size, sizeNew)
        notifyDataSetChanged()
    }
}
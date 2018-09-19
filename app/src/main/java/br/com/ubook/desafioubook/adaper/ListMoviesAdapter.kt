package br.com.ubook.desafioubook.adaper

import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import br.com.ubook.desafioubook.BuildConfig
import br.com.ubook.desafioubook.DateUtil
import br.com.ubook.desafioubook.R

import br.com.ubook.desafioubook.domain.Movie
import br.com.ubook.desafioubook.viewholder.ListMoviesViewHolder
import com.squareup.picasso.Picasso

class ListMoviesAdapter(val context: Context, val list: List<Movie>) :  androidx.recyclerview.widget.RecyclerView.Adapter<androidx.recyclerview.widget.RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): androidx.recyclerview.widget.RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view: View = inflater.inflate(R.layout.row_movie, parent, false)
        return ListMoviesViewHolder(context, view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(viewHolder: androidx.recyclerview.widget.RecyclerView.ViewHolder, position: Int) {
        var view = (viewHolder as ListMoviesViewHolder)

        Picasso.get()
                .load("${BuildConfig.BASE_URL_IMG}${list[position].poster_path}")
                .fit()
                .into(view.image)

        view.date.text = DateUtil.convertStringToDateTime(list[position].release_date)
        view.title.text = list[position].title
        view.rating.text = "${list[position].vote_average}/10"
        view.itemView.setOnClickListener {
            val options = NavOptions.Builder()
                    .setEnterAnim(R.anim.abc_slide_in_left)
                    .setExitAnim(R.anim.abc_slide_out_left)
                    .setPopEnterAnim(R.anim.abc_slide_in_left)
                    .setPopExitAnim(R.anim.abc_slide_out_left)
                    .build()
            var bundle = Bundle()
            bundle.putParcelable("movie", list[position])

            Navigation.findNavController(it).navigate(R.id.action_mainFragment_to_dateilFragment, bundle, options)
        }

    }
}
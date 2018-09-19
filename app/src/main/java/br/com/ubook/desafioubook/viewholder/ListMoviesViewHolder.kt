package br.com.ubook.desafioubook.viewholder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import kotlinx.android.synthetic.main.row_movie.view.*

class ListMoviesViewHolder(val context: Context, view: View): androidx.recyclerview.widget.RecyclerView.ViewHolder(view) {

    var image: ImageView = view.poster
    var title: TextView = view.title
    var rating: TextView = view.rating
    val date: TextView = view.date

}
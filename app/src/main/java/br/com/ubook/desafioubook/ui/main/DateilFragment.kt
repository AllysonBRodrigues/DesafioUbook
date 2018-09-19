package br.com.ubook.desafioubook.ui.main


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.ubook.desafioubook.BuildConfig
import br.com.ubook.desafioubook.DateUtil
import br.com.ubook.desafioubook.MainActivity

import br.com.ubook.desafioubook.R
import br.com.ubook.desafioubook.domain.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_dateil.*

/**
 * A simple [Fragment] subclass.
 *
 */
class DateilFragment : androidx.fragment.app.Fragment() {

    private lateinit var movie: Movie

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_dateil, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).showProgressBar()
        var bundle = this.arguments
        if(bundle != null){
            movie = bundle.getParcelable("movie")!!
            title.text = movie.title!!
            (activity as MainActivity).detailToolbar()
            overview.text = movie.overview
            rating.text = "${movie.vote_average}/10"
            release_date.text = DateUtil.convertStringToDateTime(movie.release_date)
            original_language.text = movie.original_language
            Picasso.get()
                    .load("${BuildConfig.BASE_URL_IMG}${movie.poster_path}")
                    .into(poster)

        }
        (activity as MainActivity).hideProgressBar()
    }



}

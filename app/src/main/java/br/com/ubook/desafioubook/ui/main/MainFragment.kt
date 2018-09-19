package br.com.ubook.desafioubook.ui.main

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.ubook.desafioubook.MainActivity
import br.com.ubook.desafioubook.R
import br.com.ubook.desafioubook.adaper.ListMoviesAdapter
import br.com.ubook.desafioubook.domain.ResultMoviesList
import br.com.ubook.desafioubook.mvp.interfaces.APIMovie
import br.com.ubook.desafioubook.mvp.presenter.PresenterListMovies
import br.com.ubook.desafioubook.network.ListMoviesManager
import kotlinx.android.synthetic.main.error_screen.*
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : androidx.fragment.app.Fragment(), APIMovie.ViewListMovieImpl {
    private lateinit var adapter: ListMoviesAdapter
    private lateinit var presenterListMovies: PresenterListMovies
    private lateinit var resultMovies: ResultMoviesList
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        recycler_list_movies.visibility = View.INVISIBLE

        (activity as MainActivity).HomeToolbar()
        if (savedInstanceState != null) {
            this.resultMovies = savedInstanceState.getParcelable("result")
            requestListMoviesSucess(resultMovies)
        } else {
            requestListMoviesStart()
        }

        try_again.setOnClickListener {
            error_screen.visibility = View.INVISIBLE
            requestListMoviesStart()
        }
    }

    override fun requestListMoviesStart() {
        (activity as MainActivity).showProgressBar()
        presenterListMovies = PresenterListMovies()
        presenterListMovies.bind(this, ListMoviesManager("top_rated"))
        presenterListMovies.requestListMovies()
    }

    override fun requestListMoviesSucess(listMovies: ResultMoviesList) {
        this.resultMovies = listMovies
        adapter = ListMoviesAdapter(context!!, listMovies.results)
        recycler_list_movies.adapter = adapter
        val mLayoutManager = androidx.recyclerview.widget.LinearLayoutManager(context)
        recycler_list_movies.layoutManager = mLayoutManager
        adapter.notifyDataSetChanged()

        (activity as MainActivity).hideProgressBar()
        recycler_list_movies.visibility = View.VISIBLE
    }

    override fun requestListMoviesError() {
        error_screen.visibility = View.VISIBLE
        (activity as MainActivity).hideProgressBar()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if(resultMovies.results.isNotEmpty())
            outState.putParcelable("result", resultMovies)
    }
}

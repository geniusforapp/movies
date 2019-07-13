package com.geniusforapp.movies.ui.details.similar

import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import com.geniusforapp.movies.R
import com.geniusforapp.movies.shared.data.model.MoviesResponse
import com.geniusforapp.movies.ui.base.BaseFragment
import com.geniusforapp.movies.ui.details.movie.MovieActivity
import com.geniusforapp.movies.ui.details.similar.adapters.SimilarMoviesAdapter
import kotlinx.android.synthetic.main.fragment_similar_movies.*
import javax.inject.Inject

/**
 * @name movies
 * Copyrights (c) 2019-07-12 Created By Ahmad Najar
 **/
class SimilarMoviesFragment : BaseFragment() {

    @Inject
    lateinit var factory: ViewModelProvider.Factory

    @Inject
    lateinit var similarMoviesAdapter: SimilarMoviesAdapter

    private val viewModel: SimilarMoviesViewModel by lazy { ViewModelProviders.of(this, factory)[SimilarMoviesViewModel::class.java] }

    companion object {
        private const val MOVIE_ID = "MOVIE_ID"
        fun newInstance(movieId: Int): SimilarMoviesFragment = SimilarMoviesFragment().apply { arguments = bundleOf(Pair(MOVIE_ID, movieId)) }
    }

    override fun setContentView(): Int {
        return R.layout.fragment_similar_movies
    }

    override fun bindView(view: View) {
        initActions()
        initList()
        getData()
    }

    private fun initActions() {
        similarMoviesAdapter.onItemClick = { _, result -> MovieActivity.showMovieActivity(activityContext, result.id) }
    }

    private fun initList() {
        listRelatedMovies.adapter = similarMoviesAdapter
    }

    private fun getData() {
        arguments?.getInt(MOVIE_ID, 0)?.let { viewModel.getMovies(it).observe(this, handleMovies()) }
    }

    private fun handleMovies(): Observer<PagedList<MoviesResponse.Result>> {
        return Observer {
            similarMoviesAdapter.submitList(it)
        }
    }
}
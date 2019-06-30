package com.geniusforapp.movies.ui.movies

import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.geniusforapp.movies.R
import com.geniusforapp.movies.shared.data.model.MoviesResponse
import com.geniusforapp.movies.ui.base.BaseFragment
import com.geniusforapp.movies.ui.details.movie.MovieActivity
import com.geniusforapp.movies.ui.movies.adapters.MoviesAdapter
import com.geniusforapp.movies.ui.movies.vm.MoviesViewModel
import com.geniusforapp.movies.ui.movies.vm.MoviesViewModelFactory
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.android.synthetic.main.item_movie.view.*
import javax.inject.Inject

class MoviesFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: MoviesViewModelFactory

    @Inject
    lateinit var moviesAdapter: MoviesAdapter

    // get viewModel
    private lateinit var moviesViewModel: MoviesViewModel

    override fun setContentView(): Int = R.layout.fragment_movies

    override fun bindView(view: View) {

        moviesAdapter.onItemClick = { itemView, result ->
            activity?.let {
                MovieActivity.showMovieActivity(
                        activity = it,
                        movie = result,
                        imageBackdrop = itemView.imageBackdrop,
                        imagePoster = itemView.imagePoster)
            }
        }
        // init list
        with(listMovies) {
            adapter = moviesAdapter
            itemAnimator = DefaultItemAnimator()
            layoutManager = LinearLayoutManager(context)
        }

        moviesViewModel = ViewModelProviders.of(this, viewModelFactory)[MoviesViewModel::class.java]

        // get data form view model
        arguments?.apply { moviesViewModel.getMovies(getString(CATEGORY_TYPE, getString(R.string.key_popular))).observe(this@MoviesFragment, getObserver()) }
    }

    private fun getObserver(): Observer<PagedList<MoviesResponse.Result>> {
        return Observer {   moviesAdapter.submitList(it) }
    }


    companion object {
        private const val CATEGORY_TYPE = "CATEGORY_TYPE"
        fun newInstance(type: String): MoviesFragment {
            return MoviesFragment().apply { arguments = bundleOf(Pair(CATEGORY_TYPE, type)) }
        }
    }
}

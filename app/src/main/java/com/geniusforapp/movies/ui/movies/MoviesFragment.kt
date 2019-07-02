package com.geniusforapp.movies.ui.movies

import android.view.View
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.geniusforapp.movies.R
import com.geniusforapp.movies.shared.data.model.MoviesResponse
import com.geniusforapp.movies.ui.base.BaseFragment
import com.geniusforapp.movies.ui.details.movie.MovieActivity
import com.geniusforapp.movies.ui.movies.adapters.MoviesAdapter
import com.geniusforapp.movies.ui.movies.vm.MoviesViewModel
import com.geniusforapp.movies.ui.movies.vm.MoviesViewModelFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_movies.*
import javax.inject.Inject

class MoviesFragment : BaseFragment() {


    @Inject
    lateinit var viewModelFactory: MoviesViewModelFactory

    @Inject
    lateinit var moviesAdapter: MoviesAdapter

    // get viewModel
    private val moviesViewModel: MoviesViewModel by lazy { ViewModelProviders.of(this, viewModelFactory)[MoviesViewModel::class.java] }

    override fun setContentView(): Int = R.layout.fragment_movies

    override fun bindView(view: View) {
        initAdapterAction()
        // init list
        initList()
        // get data form view model
        initViewModel()
        initScrollPublisher()
    }

    private fun initScrollPublisher() {
        CompositeDisposable().add(publisher.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ listMovies.smoothScrollToPosition(0) }, {}))
    }

    private fun initViewModel() {
        arguments?.apply { moviesViewModel.getMovies(getString(CATEGORY_TYPE, getString(R.string.key_popular))).observe(this@MoviesFragment, getObserver()) }
        moviesViewModel.getLoaderObserver().observe(this, Observer { if (it) progressBar.show() else progressBar.hide() })
    }

    private fun initList() {
        with(listMovies) {
            adapter = moviesAdapter
            itemAnimator = DefaultItemAnimator()
            layoutManager = GridLayoutManager(context, resources.getInteger(R.integer.number_of_rows))
        }
    }

    private fun initAdapterAction() {
        moviesAdapter.onItemClick = { _, result -> context?.let { MovieActivity.showMovieActivity(it, result.id) } }
    }

    private fun getObserver(): Observer<PagedList<MoviesResponse.Result>> {
        return Observer { moviesAdapter.submitList(it) }
    }


    companion object {
        private const val CATEGORY_TYPE = "CATEGORY_TYPE"
        val publisher: PublishSubject<Boolean> = PublishSubject.create()
        fun newInstance(type: String): MoviesFragment {
            return MoviesFragment().apply { arguments = bundleOf(Pair(CATEGORY_TYPE, type)) }
        }
    }


}


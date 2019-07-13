package com.geniusforapp.movies.ui.details.movie.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geniusforapp.movies.shared.data.model.MovieDetails
import com.geniusforapp.movies.shared.domain.usecases.GetMovieDetailsUseCase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class MovieViewModel @Inject constructor(private val getMovieDetailsUseCase: GetMovieDetailsUseCase,
                                         private val compositeDisposable: CompositeDisposable) : ViewModel() {


    private var movieDetailsLiveData: LiveData<MovieDetails>? = null
    private var movieDetailsError: MutableLiveData<Throwable> = MutableLiveData()
    private var loadingDetailsLiveData: MutableLiveData<Boolean> = MutableLiveData()
/*    private var movieVideosLiveData: LiveData<MovieVideos>? = null*/


    fun getLoaderLiveData() = loadingDetailsLiveData
    fun getErrorLiveData() = movieDetailsError


    var movieId: Int = 0


/*    // similar live data
    private var movies: LiveData<PagedList<MoviesResponse.Result>>? = null*/


/*    private var loaderLiveData: MutableLiveData<Boolean> = MutableLiveData()
    private var errorLiveData: MutableLiveData<Throwable> = MutableLiveData()
    private var loadMoreLiveData: MutableLiveData<Boolean> = MutableLiveData()*/

    /* private var config: PagedList.Config = PagedList.Config.Builder()
             .setPageSize(10)
             .setInitialLoadSizeHint(10)
             .setPrefetchDistance(10)
             .setEnablePlaceholders(true)
             .build()
 */

    // movie details
    fun getMovieDetails(movieId: Int): LiveData<MovieDetails> {
        this.movieId = movieId
        if (movieDetailsLiveData != null) {
            return movieDetailsLiveData as LiveData<MovieDetails>
        }

        return getMovieDetailsById(movieId)
    }

    private fun getMovieDetailsById(movieId: Int): LiveData<MovieDetails> {
        val movieDetailsLiveData = MutableLiveData<MovieDetails>()
        loadingDetailsLiveData.postValue(true)
        compositeDisposable.add(getMovieDetailsUseCase
                .getMovieDetails(movieId)
                .subscribe({ handleResult(movieDetailsLiveData, it) }, { handleErrorResult(it) }))
        return movieDetailsLiveData
    }


/*    fun getSimilarMovies(): LiveData<PagedList<MoviesResponse.Result>> {
        if (movies != null) {
            return movies as LiveData<PagedList<MoviesResponse.Result>>
        }

        this.movies = LivePagedListBuilder(similarMoviesDataSourceFactory.apply {
            this.movieId = this@MovieViewModel.movieId
            this.loaderLiveData = this@MovieViewModel.loaderLiveData
            this.errorLiveData = this@MovieViewModel.errorLiveData
            this.loadMoreLiveData = this@MovieViewModel.loadMoreLiveData
        }, config).build()
        return movies as LiveData<PagedList<MoviesResponse.Result>>
    }*/


    // handle get movie videos by id
/*    fun getMovieVideos(): LiveData<MovieVideos> {
        if (movieVideosLiveData != null) {
            return movieVideosLiveData as LiveData<MovieVideos>
        }

        return getMovieVideosById()
    }*/

/*    private fun getMovieVideosById(): MutableLiveData<MovieVideos> {
        val movieVideos = MutableLiveData<MovieVideos>()
        compositeDisposable.add(getMovieVideosByIdUseCase.getMovieVideos(movieId)
                .subscribe({
                    movieVideos.postValue(it)
                    movieVideosLiveData = movieVideos
                }, { movieDetailsError.postValue(it) }))
        return movieVideos
    }*/

    private fun handleErrorResult(it: Throwable?) {
        loadingDetailsLiveData.postValue(false)
        movieDetailsError.postValue(it)
    }

    private fun handleResult(movieDetailsLiveData: MutableLiveData<MovieDetails>, it: MovieDetails?) {
        loadingDetailsLiveData.postValue(false)
        movieDetailsLiveData.postValue(it)
        this.movieDetailsLiveData = movieDetailsLiveData
    }
}
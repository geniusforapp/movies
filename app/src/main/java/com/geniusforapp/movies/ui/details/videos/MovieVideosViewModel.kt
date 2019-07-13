package com.geniusforapp.movies.ui.details.videos

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.geniusforapp.movies.shared.data.model.MovieVideos
import com.geniusforapp.movies.shared.domain.usecases.GetMovieVideosByIdUseCase
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * @name movies
 * Copyrights (c) 2019-07-12 Created By Ahmad Najar
 **/
class MovieVideosViewModel @Inject constructor(private val compositeDisposable: CompositeDisposable,
                                               private val getMovieVideosByIdUseCase: GetMovieVideosByIdUseCase) : ViewModel() {

    private var liveData: MutableLiveData<MovieVideos>? = null


    fun getMovieVideos(videoId: Int): MutableLiveData<MovieVideos> {
        if (liveData != null) return liveData as MutableLiveData<MovieVideos>

        return getMovieVideosFromApi(videoId).apply { liveData = this }
    }

    private fun getMovieVideosFromApi(videoId: Int): MutableLiveData<MovieVideos> {
        val mutableLiveData = MutableLiveData<MovieVideos>()
        compositeDisposable.add(getMovieVideosByIdUseCase.getMovieVideos(videoId).subscribe({ mutableLiveData.postValue(it) }, {}))
        return mutableLiveData
    }
}
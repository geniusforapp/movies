package com.geniusforapp.movies.ui.details.movie.adapters.callback

import androidx.recyclerview.widget.DiffUtil
import com.geniusforapp.movies.shared.data.model.MovieDetails
import javax.inject.Inject

/**
 * @name movies
 * Copyrights (c) 2019-07-05 Created By Ahmad Najar
 **/
class ProductionCompanyItemCallback @Inject constructor() : DiffUtil.ItemCallback<MovieDetails.ProductionCompany>() {
    override fun areItemsTheSame(oldItem: MovieDetails.ProductionCompany, newItem: MovieDetails.ProductionCompany): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: MovieDetails.ProductionCompany, newItem: MovieDetails.ProductionCompany): Boolean {
        return oldItem == newItem
    }
}
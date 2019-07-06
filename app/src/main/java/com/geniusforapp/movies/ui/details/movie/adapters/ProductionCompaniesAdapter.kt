package com.geniusforapp.movies.ui.details.movie.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.geniusforapp.movies.R
import com.geniusforapp.movies.shared.data.model.MovieDetails
import com.geniusforapp.movies.ui.details.movie.adapters.callback.ProductionCompanyItemCallback
import com.geniusforapp.movies.ui.details.movie.adapters.holders.ProductionCompanyViewHolder
import javax.inject.Inject

/**
 * @name movies
 * Copyrights (c) 2019-07-05 Created By Ahmad Najar
 **/
class ProductionCompaniesAdapter @Inject constructor(productionCompanyItemCallback: ProductionCompanyItemCallback) : ListAdapter<MovieDetails.ProductionCompany, ProductionCompanyViewHolder>(productionCompanyItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductionCompanyViewHolder {
        return ProductionCompanyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_production_companies, parent, false))
    }

    override fun onBindViewHolder(holder: ProductionCompanyViewHolder, position: Int) {
        holder.bind(getItem(holder.adapterPosition))
    }
}
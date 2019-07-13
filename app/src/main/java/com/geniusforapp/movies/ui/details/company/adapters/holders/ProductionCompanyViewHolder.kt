package com.geniusforapp.movies.ui.details.company.adapters.holders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.geniusforapp.movies.R
import com.geniusforapp.movies.shared.data.model.MovieDetails
import kotlinx.android.synthetic.main.item_production_companies.view.*


/**
 * @name movies
 * Copyrights (c) 2019-07-05 Created By Ahmad Najar
 **/
class ProductionCompanyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    fun bind(productionCompany: MovieDetails.ProductionCompany?) {
        productionCompany?.let {
            loadImage(it.logoPath)
            setData(it)
        }
    }

    private fun setData(productionCompany: MovieDetails.ProductionCompany) {
        itemView.textCompanies.text = productionCompany.name
    }

    private fun loadImage(logo: String?) {
        Glide.with(itemView.context)
                .load("${itemView.context.getString(R.string.image)}$logo")
                .apply(RequestOptions()
                        .error(R.drawable.bg_placeholder)).into(itemView.imageCompany)
    }
}
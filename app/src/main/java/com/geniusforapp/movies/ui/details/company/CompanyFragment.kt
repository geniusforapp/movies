package com.geniusforapp.movies.ui.details.company

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import com.geniusforapp.movies.R
import com.geniusforapp.movies.shared.data.model.MovieDetails.ProductionCompany
import com.geniusforapp.movies.ui.base.BaseFragment
import com.geniusforapp.movies.ui.details.company.adapters.ProductionCompaniesAdapter
import kotlinx.android.synthetic.main.fragment_comapny.*
import javax.inject.Inject

/**
 * @name movies
 * Copyrights (c) 2019-07-06 Created By Ahmad Najar
 **/
class CompanyFragment : BaseFragment() {


    @Inject
    lateinit var productionCompaniesAdapter: ProductionCompaniesAdapter

    override fun setContentView(): Int = R.layout.fragment_comapny

    override fun bindView(view: View) {
        with(listProductionCompanies) { adapter = productionCompaniesAdapter }
        arguments?.let { getData(it) }
    }

    private fun getData(bundle: Bundle) {
        bundle.getParcelableArrayList<ProductionCompany>(COMPANIES)?.let { productionCompaniesAdapter.submitList(it) }
    }


    companion object {
        private const val COMPANIES = "companies"
        fun newInstance(productionCompanies: List<ProductionCompany>): CompanyFragment {
            return CompanyFragment().apply { arguments = bundleOf(Pair(COMPANIES, productionCompanies)) }
        }
    }
}
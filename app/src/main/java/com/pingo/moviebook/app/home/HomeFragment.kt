package com.pingo.moviebook.app.home

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.pingo.moviebook.R
import com.pingo.moviebook.app.home.adapter.HomeListAdapter
import com.pingo.moviebook.app.home.adapter.PaginationScrollListener
import com.pingo.moviebook.databinding.FragmentHomeBinding
import com.pingo.moviebook.shared.ext.setup
import com.pingo.moviebook.shared.models.Result
import com.pingo.moviebook.shared.ui.BaseFragment
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.error_layout.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.loading_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Showing List of popular movies
 */
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {


    private var homeListAdapter: HomeListAdapter? = null
    private val viewModelObj by viewModel<HomeViewModel>()

    var isLastPage: Boolean = false
    var isLoading: Boolean = false
    var pageIndex = 1
    var genreId = -1

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeListAdapter = HomeListAdapter()
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // set data binding
        binding.apply {
            viewModel = viewModelObj
            lifecycleOwner = this@HomeFragment

        }

        // init all view
        initViews()

        // register observers
        initObservers()

        // fetch movies list  as we have started listening changes
        getMoviesList()

        viewModelObj.fetchGenresList()

        rvMovies?.addOnScrollListener(object : PaginationScrollListener(rvMovies.layoutManager as LinearLayoutManager) {
            override fun isLastPage(): Boolean {
                return isLastPage
            }

            override fun isLoading(): Boolean {
                return isLoading
            }

            override fun loadMoreItems() {
                isLoading = true
                //you have to call loadmore items to get more data
                getMoreItems()
            }
        })

    }

    fun getMoreItems() {
        //after fetching your data assuming you have fetched list in your
        // recyclerview adapter assuming your recyclerview adapter is
        //rvAdapter
        pageIndex++

        getMoviesList()

        isLoading = false
    }

    private fun getMoviesList() {
        if(genreId > -1) {
            viewModelObj.fetchMoviesByGenre(pageIndex, genreId)
        } else {
            viewModelObj.fetchPopularMovies(pageIndex)
        }
    }

    /**
     * Initializing All Views
     */
    private fun initViews() {

        // set recycler view
        with(rvMovies.setup()) {
            adapter = homeListAdapter
        }

        // set pull to refresh
        refreshLayout.setOnRefreshListener {
            pageIndex = 1
            getMoviesList()
        }

        btnShowAll.setOnClickListener {
            pageIndex = 1
            genreId = -1
            getMoviesList()
        }
    }


    /**
     * Start listening [androidx.lifecycle.Observer]
     */
    private fun initObservers() {

        // movie list
        viewModelObj.popMoviesList.observe(this, Observer {

            if (it == null) {
                errorLayout.visibility = View.VISIBLE
                homeListAdapter?.updateResults(emptyList())
            } else {
                if(pageIndex == 1) {
                    homeListAdapter?.updateResults(it.results)
                } else {
                    homeListAdapter?.addData(it.results as ArrayList<Result>)
                }
            }
        })

        viewModelObj.genresList.observe(this, Observer {

            if (it != null) {
                val genreAdapter = ArrayAdapter(activity, android.R.layout.simple_spinner_dropdown_item, it.genres)
                spnGenres.adapter = genreAdapter
                spnGenres.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }

                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        genreId = viewModelObj.genresList.value?.genres?.get(position)?.id!!
                        pageIndex = 1
                        getMoviesList()
                    }
                }
            }
        })

        // listen for connection error
        viewModelObj.connectionError.observe(this, Observer {
            errorLayout.visibility = View.VISIBLE
            tvErrorDetail.text = getString(R.string.connection_error_msg)
        })


        // listen for  error
        viewModelObj.errorMessage.observe(this, Observer {
            errorLayout.visibility = View.VISIBLE
            tvErrorDetail.text = it
        })


        viewModelObj.showProgress.observe(this, Observer {
            if (it) {
                errorLayout.visibility = View.GONE
                if (loader != null) {
                    loader.inflate()
                    shimmerLayout.startShimmer()
                } else {
//                    homeListAdapter?.updateResults(emptyList())
                    refreshLayout.isRefreshing = true
                }
            } else {
                refreshLayout.isRefreshing = false
                shimmerLayout?.stopShimmer()
                shimmerLayout?.visibility = View.GONE
            }
        })

    }

}

package com.srikanth.restaurant.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager

import com.srikanth.restaurant.R
import com.srikanth.restaurant.adapter.RestaurantAdapter
import com.srikanth.restaurant.util.Resource
import kotlinx.android.synthetic.main.fragment_restaurant_list.*

/**
 * A simple [Fragment] subclass.
 */
class RestaurantListFragment : Fragment(R.layout.fragment_restaurant_list) {

    lateinit var viewModel: RestaurantViewModel
    lateinit var restaurantAdapter: RestaurantAdapter
    private val TAG = "RestaurantListFragment1"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as HomeActivity).viewModel
        setupRecyclerView()

        restaurantAdapter.setOnItemClickListener {
            val bundle = Bundle().apply {
                putParcelable("restaurantDetails", it)
            }
            findNavController().navigate(
                R.id.action_restaurantListFragment_to_restaurantDetailsFragment,
                bundle
            )
        }

        viewModel.restaurantLiveData.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success->{
                    Log.d(TAG,"Success Response")
                    response.data?.let { apiResponse ->
                        restaurantAdapter.differ.submitList(apiResponse.toList())

                    }

                }
                is Resource.Error-> {
                    Log.d(TAG,"Error")
                }
                is Resource.Loading-> {
                    Log.d(TAG,"Loading")
                }
                else -> {
                    Log.d(TAG,"Default")
                }
            }
        })

        viewModel.errorLiveData.observe(
            viewLifecycleOwner, Observer { errorResponse ->
                when(errorResponse) {
                    is Resource.Error->{
                        Log.d(TAG,"Error1")
                    }
                    else -> {
                        Log.d(TAG,"Error1 Default")
                    }
                }
            }
        )
    }

    private fun loadRestaurantsList() {
        showProgressBar()
        viewModel.getRestaurantList()
    }

    private fun hideProgressBar() {
        rvProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        rvProgressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView() {
        restaurantAdapter = RestaurantAdapter()
        rvRestaurantList.apply {
            adapter = restaurantAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }

}

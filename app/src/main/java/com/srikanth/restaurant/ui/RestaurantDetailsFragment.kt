package com.srikanth.restaurant.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.srikanth.restaurant.R
import kotlinx.android.synthetic.main.fragment_restaurant_details.*

/**
 * A simple [Fragment] subclass.
 */
class RestaurantDetailsFragment : Fragment(R.layout.fragment_restaurant_details) {

    //lateinit var viewModel: RestaurantViewModel
    private val args: RestaurantDetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //viewModel = (activity as HomeActivity).viewModel
        val restaurant = args.restaurantDetails
        Glide.with(this).load(restaurant.imageUrl).into(ivRestaurantImage)
        tvRestaurantTitle.text = restaurant.name
        tvRestaurantCategory.text = restaurant.category
        tvRestaurantGoodFor.text = restaurant.goodFor
        tvRestaurantTimings.text = restaurant.closeTime
        tvRestaurantDescription.text = restaurant.description
        tvRestaurantAddress.text = restaurant.streetAddress
        tvRestaurantCityAndState.text = restaurant.city+" "+restaurant.state+" "+restaurant.postalCode
        tvRestaurantPhoneNo.text = restaurant.phoneNumber

    }

}

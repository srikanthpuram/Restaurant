package com.srikanth.restaurant.networking

import com.srikanth.restaurant.model.ApiResponseItem
import com.srikanth.restaurant.util.Resource
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface RestaurantAPI {

    @GET("/restaurants")
    fun getRestaurantList() : Single<Response<List<ApiResponseItem>>>
}
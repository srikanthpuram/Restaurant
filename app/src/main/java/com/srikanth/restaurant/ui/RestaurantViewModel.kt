package com.srikanth.restaurant.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.srikanth.restaurant.model.ApiResponseItem
import com.srikanth.restaurant.networking.RetrofitInstance
import com.srikanth.restaurant.util.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException


class RestaurantViewModel : ViewModel() {

    lateinit var disposable: Disposable
    val restaurantLiveData: MutableLiveData<Resource<List<ApiResponseItem>>> = MutableLiveData()
    val errorLiveData: MutableLiveData<Resource.Error<String>> = MutableLiveData()

    init {
        getRestaurantList()
    }

    fun getRestaurantList() {
        restaurantLiveData.postValue(Resource.Loading())
        disposable = RetrofitInstance.api.getRestaurantList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ response -> handleApiResponse(response) }, {handleError(it)});
    }

    /*
     * handle the api failure response
     */
    private fun handleError(throwable: Throwable) {
        var errorData: Resource.Error<String> = when (throwable) {
            is IOException -> Resource.Error("IOException")
            is HttpException -> Resource.Error<String>("Http Exception " +throwable.code() )
            else -> {
                 Resource.Error<String>("Error, unable to load data")
            }
        }
        errorLiveData.postValue(errorData)
    }

    /*
     * handle the api success response
     */
    private fun handleApiResponse(response: Response<List<ApiResponseItem>>)  {
        lateinit var returnedData: Resource<List<ApiResponseItem>>
        if(response.isSuccessful) {
            response.body()?.let{ resultResponse ->
                returnedData = Resource.Success(resultResponse)
            }
        }else {
            returnedData = Resource.Error(response.errorBody().toString())
        }
        restaurantLiveData.postValue(returnedData)

    }

    override fun onCleared() {
        super.onCleared()
        if(!disposable.isDisposed) {
            disposable.dispose()
        }
    }
}


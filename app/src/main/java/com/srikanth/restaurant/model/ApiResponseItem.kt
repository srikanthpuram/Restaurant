package com.srikanth.restaurant.model

import android.os.Parcel
import android.os.Parcelable

data class ApiResponseItem(

    val category: String?,  //Fine Dining, ListView, also in detail view below name
    val city: String?,
    val closeTime: String?,
    val description: String?,  //in details view
    val goodFor: String?,  //Detailed View, below category
    val id: String?,
    val imageUrl: String?,
    val latitude: Double,
    val longitude: Double,
    val name: String?,  //Restaurant name, list view and detail view
    val openTime: String?,  //listview and detailed view
    val phoneNumber: String?,  //listview and detailed view
    val postalCode: String?,   //listview and detailed view
    val state: String?,  //listview and detailed view
    val streetAddress: String?,
    val websiteUrl: String?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readDouble(),
        parcel.readDouble(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(category)
        parcel.writeString(city)
        parcel.writeString(closeTime)
        parcel.writeString(description)
        parcel.writeString(goodFor)
        parcel.writeString(id)
        parcel.writeString(imageUrl)
        parcel.writeDouble(latitude)
        parcel.writeDouble(longitude)
        parcel.writeString(name)
        parcel.writeString(openTime)
        parcel.writeString(phoneNumber)
        parcel.writeString(postalCode)
        parcel.writeString(state)
        parcel.writeString(streetAddress)
        parcel.writeString(websiteUrl)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ApiResponseItem> {
        override fun createFromParcel(parcel: Parcel): ApiResponseItem {
            return ApiResponseItem(parcel)
        }

        override fun newArray(size: Int): Array<ApiResponseItem?> {
            return arrayOfNulls(size)
        }
    }
}
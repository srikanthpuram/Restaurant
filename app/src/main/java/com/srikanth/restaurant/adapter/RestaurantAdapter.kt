package com.srikanth.restaurant.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.srikanth.restaurant.R
import com.srikanth.restaurant.model.ApiResponseItem
import kotlinx.android.synthetic.main.restaurant_row_item.view.*

class RestaurantAdapter : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    inner class RestaurantViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val diffCallback = object : DiffUtil.ItemCallback<ApiResponseItem>() {
        override fun areItemsTheSame(oldItem: ApiResponseItem, newItem: ApiResponseItem): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: ApiResponseItem,
            newItem: ApiResponseItem
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    private var onItemClickListener: ((ApiResponseItem) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        return RestaurantViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.restaurant_row_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {
        val restaurantItem = differ.currentList[position]
        holder.itemView.apply {
            Glide.with(this).load(restaurantItem.imageUrl).into(ivListItemThumbNailImage)
            tvListItemTitle.text = restaurantItem.name
            tvListItemCategory.text = restaurantItem.category
            tvListItemGoodFor.text = restaurantItem.goodFor
            tvListItemTimings.text = "Open till ${restaurantItem.closeTime}"

            setOnClickListener {
                onItemClickListener?.let {
                    it(restaurantItem)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun setOnItemClickListener(listener: (ApiResponseItem) -> Unit) {
        onItemClickListener = listener
    }
}
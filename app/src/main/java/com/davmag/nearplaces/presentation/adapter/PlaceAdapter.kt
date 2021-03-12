package com.davmag.nearplaces.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.davmag.nearplaces.R
import com.davmag.nearplaces.presentation.model.PlacePresentation

class PlaceAdapter() : PagingDataAdapter<PlacePresentation, PlaceViewHolder>(diffCallback) {
    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<PlacePresentation>() {
            override fun areItemsTheSame(
                oldItem: PlacePresentation,
                newItem: PlacePresentation
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: PlacePresentation,
                newItem: PlacePresentation
            ): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        getItem(position)?.let {
            holder.itemView.findViewById<AppCompatTextView>(R.id.place_name).text = it.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        return PlaceViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.place_item, parent, false)
        )
    }
}

class PlaceViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView)
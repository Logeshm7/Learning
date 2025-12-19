package com.kmm.android.ui.car.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kmm.android.data.Car
import com.kmm.android.databinding.CarImageRowBinding

class CarImageAdapter(
    private var carImage: List<String>,
    private val context: Context,
) : RecyclerView.Adapter<CarImageAdapter.CarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val binding = CarImageRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(carImage[position])
    }

    override fun getItemCount(): Int = carImage.size
    @SuppressLint("NotifyDataSetChanged")
    fun updateList(updatedList: List<String>?) {
        if (updatedList != null) {
            carImage = updatedList
        }
        notifyDataSetChanged()
    }

    inner class CarViewHolder(val binding: CarImageRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(carImage: String) {
            binding.apply {
                Glide.with(context).load(carImage).placeholder(android.R.drawable.ic_menu_gallery)
                    .error(android.R.drawable.ic_dialog_alert)
                    .centerCrop()
                    .into(ivCar)
            }
        }
    }
}
package com.kmm.android.ui.car.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kmm.android.data.Car
import com.kmm.android.databinding.CarRowBinding

class CarAdapter(
    private var cars: List<Car>,
    private val context: Context,
    private val listener: OnItemClickListener,
) : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(car: Car)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {

        val binding = CarRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(cars[position])
        holder.binding.root.setOnClickListener {
            listener.onItemClick(cars[position])
        }
    }

    override fun getItemCount(): Int = cars.size

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newCars: List<Car>) {
        cars = newCars
        Log.d("TAG", "updateList: $cars\n$newCars")
        notifyDataSetChanged()
    }

    inner class CarViewHolder(val binding: CarRowBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(car: Car) {
            binding.apply {
                tvBrandModel.text = "${car.brand} ${car.model}"
                tvPrice.text = "$${car.price}"
                Glide.with(context).load(car.image).placeholder(android.R.drawable.ic_menu_gallery)
                    .error(android.R.drawable.ic_dialog_alert)
                    .centerCrop()
                    .into(ivCar)
            }
        }
    }
}

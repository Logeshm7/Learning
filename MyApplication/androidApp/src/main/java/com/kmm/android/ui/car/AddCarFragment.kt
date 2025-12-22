package com.kmm.android.ui.car

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.kmm.android.R
import com.kmm.android.data.Car
import com.kmm.android.databinding.FragmentAddCarBinding
import com.kmm.android.presentation.AddCarViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddCarFragment : Fragment() {
    private lateinit var binding: FragmentAddCarBinding
    private val viewModel: AddCarViewModel by viewModel()
    private var carId: Long = 0
    private val args: AddCarFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        carId = args.carId
        binding = FragmentAddCarBinding.inflate(inflater)
        initObserver()
        initListener()
        if (carId > 0) {
            binding.btnAdd.text = "Update"
            binding.btnDeleteCar.visibility = View.VISIBLE
            viewModel.getCar(carId)
        }
        return binding.root
    }

    private fun initObserver() {
        viewModel.message.observe(viewLifecycleOwner) {
            CommonUtil.showCustomSuccessToast(it, requireContext())
        }
        viewModel.error.observe(viewLifecycleOwner) {
            CommonUtil.showCustomErrorToast(it, requireContext())
        }
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.exit.observe(viewLifecycleOwner) {
            findNavController().popBackStack()
        }
        viewModel.car.observe(viewLifecycleOwner) {
            binding.etBrand.setText(it.brand)
            binding.etModel.setText(it.model)
            binding.etPrice.setText(it.price.toString())
            binding.etImage.setText(it.image)
            Glide.with(requireContext()).load(it.image)
                .thumbnail(
                    Glide.with(requireContext())
                        .load(R.drawable.loading)
                )
                .error(android.R.drawable.ic_dialog_alert)
                .centerCrop()
                .into(binding.imageCar)
        }

    }

    private fun initListener() {
        binding.btnAdd.setOnClickListener {
            if (carId < 1) {
                viewModel.createCar(
                    Car(
                        brand = binding.etBrand.text.toString(),
                        model = binding.etModel.text.toString(),
                        price = binding.etPrice.text.toString().toDouble(),
                        image = binding.etImage.text.toString()
                    )
                )
            } else {
                viewModel.updateCar(
                    Car(
                        id = carId,
                        brand = binding.etBrand.text.toString(),
                        model = binding.etModel.text.toString(),
                        price = binding.etPrice.text.toString().toDouble(),
                        image = binding.etImage.text.toString()
                    )
                )
            }
        }
        binding.btnDeleteCar.setOnClickListener {
            viewModel.deleteCar(carId)
        }
    }
}
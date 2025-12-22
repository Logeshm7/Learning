package com.kmm.android.ui.car

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kmm.android.databinding.FragmentCarImageBinding
import com.kmm.android.presentation.CarImageViewModel
import com.kmm.android.ui.car.adapter.CarImageAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class CarImageFragment : Fragment() {

    private lateinit var binding: FragmentCarImageBinding
    private val viewModel: CarImageViewModel by viewModel()
    private lateinit var caradapter: CarImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCarImageBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setupRecyclerView()
        initObserver()
        viewModel.getCarImage()
    }

    private fun setupRecyclerView() {
        caradapter = CarImageAdapter(requireContext())
        binding.rvCars.apply {
            adapter = caradapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun initObserver() {
        viewModel.isLoading.observe(viewLifecycleOwner) {
            binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        }
        viewModel.car.observe(viewLifecycleOwner) {
            caradapter.updateList(it)
        }
        viewModel.message.observe(viewLifecycleOwner) {
            CommonUtil.showCustomSuccessToast(it, requireContext())
        }
        viewModel.error.observe(viewLifecycleOwner) {
            CommonUtil.showCustomErrorToast(it, requireContext())
        }
    }
}

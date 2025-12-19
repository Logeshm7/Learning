package com.kmm.android.ui.car

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kmm.android.data.network.KtorApiClient
import com.kmm.android.data.network.KtorCarRepository
import com.kmm.android.databinding.FragmentCarImageBinding
import com.kmm.android.domain.GetAllCarImageUseCase
import com.kmm.android.presentation.CarImageViewModel
import com.kmm.android.ui.car.adapter.CarImageAdapter

class CarImageFragment : Fragment() {

    private lateinit var binding: FragmentCarImageBinding
    private lateinit var ktorCarRepository: KtorCarRepository
    private lateinit var viewModel: CarImageViewModel
    private lateinit var caradapter: CarImageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCarImageBinding.inflate(inflater)
        ktorCarRepository = KtorCarRepository(ktorClient = KtorApiClient)
        viewModel = CarImageViewModel(
            getAllCarImageUseCase = GetAllCarImageUseCase(ktorCarRepository)
        )
        setupRecyclerView()
        viewModel.getCarImage()
        initObserver()
        return binding.root
    }

    private fun setupRecyclerView() {
        caradapter = CarImageAdapter(emptyList(), requireContext())
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
    }
}
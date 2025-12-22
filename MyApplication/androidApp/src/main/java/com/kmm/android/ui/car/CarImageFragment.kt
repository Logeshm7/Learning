package com.kmm.android.ui.car

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kmm.android.R
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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        ktorCarRepository = KtorCarRepository(ktorClient = KtorApiClient)
        viewModel = CarImageViewModel(
            getAllCarImageUseCase = GetAllCarImageUseCase(ktorCarRepository)
        )
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

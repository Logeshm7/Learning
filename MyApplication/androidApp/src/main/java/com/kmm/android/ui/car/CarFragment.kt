package com.kmm.android.ui.car

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kmm.android.data.Car
import com.kmm.android.data.network.KtorApiClient
import com.kmm.android.data.network.KtorCarRepository
import com.kmm.android.databinding.FragmentCarBinding
import com.kmm.android.domain.GetCarsUseCase
import com.kmm.android.presentation.CarViewModel
import com.kmm.android.ui.car.adapter.CarAdapter

class CarFragment : Fragment() {
    private lateinit var binding: FragmentCarBinding
    private lateinit var ktorCarRepository: KtorCarRepository
    private lateinit var viewModel: CarViewModel
    private lateinit var caradapter: CarAdapter
    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentCarBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        ktorCarRepository = KtorCarRepository(ktorClient = KtorApiClient)
        viewModel = CarViewModel(
            getCarsUseCase = GetCarsUseCase(ktorCarRepository)
        )
        setupRecyclerView()
        initObserver()
        viewModel.getCar()

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
        viewModel.car.observe(viewLifecycleOwner) {
            caradapter.updateList(it)
        }
    }

    private fun setupRecyclerView() {
        caradapter = CarAdapter(emptyList(), requireContext(),object : CarAdapter.OnItemClickListener{
            override fun onItemClick(car: Car) {
                navController.navigate(CarFragmentDirections.actionNavCarToNavAddCar(car.id ?: 0L))
            }

        })
        binding.rvCars.apply {
            adapter = caradapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }
}


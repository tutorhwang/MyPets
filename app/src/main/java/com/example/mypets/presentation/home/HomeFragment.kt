package com.example.mypets.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import coil.load
import com.example.mypets.data.repository.CatRepositoryImpl
import com.example.mypets.databinding.FragmentHomeBinding
import com.example.mypets.presentation.base.UiState

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!
    private val viewModel: HomeViewModel by viewModels {
        viewModelFactory { initializer { HomeViewModel(CatRepositoryImpl()) } }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    binding.progress.isVisible = true
                }

                is UiState.Success -> {
                    binding.progress.isVisible = false
                    binding.thumbnail.load(uiState.data) {
                        crossfade(true)
                    }
                }

                is UiState.Error -> {
                    Toast.makeText(requireContext(), uiState.message, Toast.LENGTH_LONG).show()
                }
            }
        }
        viewModel.fetchRandomCat()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
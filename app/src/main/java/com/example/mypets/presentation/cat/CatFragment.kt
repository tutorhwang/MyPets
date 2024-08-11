package com.example.mypets.presentation.cat

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
import androidx.recyclerview.widget.GridLayoutManager
import com.example.mypets.data.repository.CatRepositoryImpl
import com.example.mypets.databinding.FragmentCatBinding
import com.example.mypets.presentation.base.PetThumbnailAdapter
import com.example.mypets.presentation.base.UiState


class CatFragment : Fragment() {

    private var _binding: FragmentCatBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val viewModel: CatViewModel by viewModels {
        viewModelFactory { initializer { CatViewModel(CatRepositoryImpl()) } }
    }

    private val petAdapter by lazy { PetThumbnailAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
        viewModel.fetchCatList()
    }

    private fun initView() = with(binding) {
        list.adapter = petAdapter
        list.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    private fun initViewModel() {
        viewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is UiState.Loading -> {
                    binding.progress.isVisible = true
                }

                is UiState.Success -> {
                    petAdapter.submitList(uiState.data) {
                        binding.progress.isVisible = false
                    }
                }

                is UiState.Error -> {
                    Toast.makeText(requireContext(), uiState.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
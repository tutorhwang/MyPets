package com.example.mypets.presentation.dog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.mypets.databinding.FragmentDogBinding
import com.example.mypets.presentation.base.PetThumbnailAdapter

class DogFragment : Fragment() {

    private var _binding: FragmentDogBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private val petAdapter by lazy { PetThumbnailAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDogBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModel()
    }

    private fun initView() {
        binding.list.adapter = petAdapter
    }

    private fun initViewModel() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
package com.example.mypets.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypets.data.model.Pet
import com.example.mypets.data.repository.PetRepository
import com.example.mypets.presentation.base.UiState
import kotlinx.coroutines.launch

class HomeViewModel(private val catRepo: PetRepository) : ViewModel() {

    private val _uiState: MutableLiveData<UiState<Pet>> = MutableLiveData()
    val uiState: LiveData<UiState<Pet>> = _uiState

    fun fetchRandomCat() {
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            try {
                val pets = catRepo.getList(1)
                _uiState.value = UiState.Success(pets[0])
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "An unexpected error occurred")
            }
        }
    }
}
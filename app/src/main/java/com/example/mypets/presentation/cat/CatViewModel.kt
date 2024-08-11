package com.example.mypets.presentation.cat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypets.presentation.base.UiState
import com.example.mypets.data.model.Pet
import com.example.mypets.data.repository.PetRepository
import kotlinx.coroutines.launch

class CatViewModel(private val repository: PetRepository) : ViewModel() {

    private val _uiState: MutableLiveData<UiState<List<Pet>>> = MutableLiveData()
    val uiState: LiveData<UiState<List<Pet>>> = _uiState

    fun fetchCatList() {
        _uiState.value = UiState.Loading
        viewModelScope.launch {
            try {
                val pets = repository.getList()
                _uiState.value = UiState.Success(pets)
            } catch (e: Exception) {
                _uiState.value = UiState.Error(e.message ?: "An unexpected error occurred")
            }
        }
    }
}
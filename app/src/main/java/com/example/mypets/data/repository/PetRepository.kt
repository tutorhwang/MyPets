package com.example.mypets.data.repository

import com.example.mypets.data.model.Pet

interface PetRepository {
    suspend fun getList(num: Int = 20): List<Pet>
}

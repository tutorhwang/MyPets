package com.example.mypets.data.repository

import com.example.mypets.network.RetrofitClient
import com.example.mypets.data.model.Pet

class CatRepositoryImpl : PetRepository {
    override suspend fun getList(num: Int): List<Pet> {
        val catModels = RetrofitClient.api.getImages(num)
        return catModels.map {
            Pet(thumbnail = it.url.toString())
        }
    }
}

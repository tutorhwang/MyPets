package com.example.mypets.data.model


import com.google.gson.annotations.SerializedName

class CatModel : ArrayList<CatModelItem>()

data class CatModelItem(
    @SerializedName("height")
    val height: Int?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("width")
    val width: Int?
)
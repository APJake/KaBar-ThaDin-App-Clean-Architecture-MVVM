package com.apjake.kabarthadin.data.network.dto


import com.google.gson.annotations.SerializedName

data class NewsResponseDto(
    @SerializedName("articles")
    val articles: List<ArticleDto>?,

    @SerializedName("status")
    val status: String?,

    @SerializedName("totalResults")
    val totalResults: Int?
)
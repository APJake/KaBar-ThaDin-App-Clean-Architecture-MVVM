package com.apjake.kabarthadin.data.network.dto


import com.apjake.kabarthadin.data.local.entity.ArticleEntity
import com.apjake.kabarthadin.domain.model.Article
import com.google.gson.annotations.SerializedName

data class ArticleDto(
    @SerializedName("author")
    val author: String?,

    @SerializedName("content")
    val content: String?,

    @SerializedName("description")
    val description: String?,

    @SerializedName("publishedAt")
    val publishedAt: String?,

    @SerializedName("source")
    val source: SourceDto?,

    @SerializedName("title")
    val title: String?,

    @SerializedName("url")
    val url: String?,

    @SerializedName("urlToImage")
    val urlToImage: String?,
){
    fun toArticle() = Article(
        author = author.orEmpty(),
        content = content.orEmpty(),
        description = description.orEmpty(),
        publishedAt = publishedAt.orEmpty(),
        sourceName = source?.name?:source?.id.orEmpty(),
        title = title.orEmpty(),
        url = url.orEmpty(),
        urlToImage = urlToImage.orEmpty()
    )
}
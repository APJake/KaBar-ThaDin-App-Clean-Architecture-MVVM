package com.apjake.kabarthadin.domain.model

import android.os.Parcelable
import com.apjake.kabarthadin.data.local.entity.ArticleEntity
import com.apjake.kabarthadin.data.network.dto.SourceDto
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val sourceName: String,
    val title: String,
    val url: String,
    val urlToImage: String,
): Parcelable {
    fun toArticleEntity() = ArticleEntity(
        author = author,
        content = content,
        description = description,
        publishedAt = publishedAt,
        sourceName = sourceName,
        title = title,
        url = url,
        urlToImage = urlToImage,
    )
}
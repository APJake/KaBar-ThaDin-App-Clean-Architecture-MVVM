package com.apjake.kabarthadin.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.apjake.kabarthadin.domain.model.Article

@Entity
data class ArticleEntity(
    @PrimaryKey val id: Int? = null,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val sourceName: String,
    val title: String,
    val url: String,
    val urlToImage: String,
){
    fun toArticle() = Article(
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
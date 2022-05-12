package com.apjake.kabarthadin.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.apjake.kabarthadin.data.local.entity.ArticleEntity

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticleList(articles: List<ArticleEntity>)

    @Query("DELETE FROM ArticleEntity")
    suspend fun clearArticles()

    @Query("SELECT * FROM ArticleEntity")
    suspend fun getArticles(): List<ArticleEntity>

}
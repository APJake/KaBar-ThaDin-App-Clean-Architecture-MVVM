package com.apjake.kabarthadin.domain.repository

import com.apjake.kabarthadin.common.util.AppConstants
import com.apjake.kabarthadin.common.util.Resource
import com.apjake.kabarthadin.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun searchArticles(query: String, page: Int): Flow<Resource<Boolean>>
    fun loadMoreArticles(query: String, page: Int): Flow<Resource<Boolean>>
    fun getArticles(): Flow<List<Article>>

}
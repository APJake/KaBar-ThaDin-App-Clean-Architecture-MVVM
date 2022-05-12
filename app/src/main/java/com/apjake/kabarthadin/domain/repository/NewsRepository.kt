package com.apjake.kabarthadin.domain.repository

import com.apjake.kabarthadin.common.util.AppConstants
import com.apjake.kabarthadin.common.util.Resource
import com.apjake.kabarthadin.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun searchArticles(query: String, page: Int): Flow<Resource<List<Article>>>
    fun loadMoreArticles(query: String, page: Int): Flow<Resource<List<Article>>>

}
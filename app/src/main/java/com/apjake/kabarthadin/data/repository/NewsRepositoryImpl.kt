package com.apjake.kabarthadin.data.repository

import com.apjake.kabarthadin.common.util.Resource
import com.apjake.kabarthadin.data.datasource.NewsLocalDataSource
import com.apjake.kabarthadin.data.datasource.NewsNetworkDataSource
import com.apjake.kabarthadin.domain.model.Article
import com.apjake.kabarthadin.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class NewsRepositoryImpl(
    private val localDataSource: NewsLocalDataSource,
    private val networkDataSource: NewsNetworkDataSource,
): NewsRepository {
    override fun searchArticles(query: String, page: Int): Flow<Resource<List<Article>>> = flow {
        emit(Resource.Loading())
        var oldArticles = emptyList<Article>()
        try {
            oldArticles = localDataSource.getAllArticles()
            val articles = networkDataSource.getNewsArticles(query, page)
            localDataSource.clearArticles()
            localDataSource.insertArticles(articles)
            val localArticles = localDataSource.getAllArticles()
            emit(Resource.Success(localArticles))
        }catch (e: HttpException){
            emit(Resource.Error("Something went wrong", oldArticles))
        }catch (e: IOException){
            emit(Resource.Error("No internet connections", oldArticles))
        }
    }

    override fun loadMoreArticles(query: String, page: Int): Flow<Resource<List<Article>>> = flow{
        emit(Resource.Loading())
        var oldArticles = emptyList<Article>()
        try {
            oldArticles = localDataSource.getAllArticles()
            emit(Resource.Loading(oldArticles))
            val articles = networkDataSource.getNewsArticles(query, page)
            localDataSource.insertArticles(articles)
            val newArticles = localDataSource.getAllArticles()
            emit(Resource.Success(newArticles))
        }catch (e: HttpException){
            emit(Resource.Success(oldArticles))
        }catch (e: IOException){
            emit(Resource.Error("No internet connections"))
        }
    }
}
package com.apjake.kabarthadin.data.repository

import com.apjake.kabarthadin.common.util.AppConstants
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
    override fun searchArticles(query: String, page: Int): Flow<Resource<Boolean>> = flow {
        emit(Resource.Loading())
        try {
            val articles = networkDataSource.getNewsArticles(query, page, AppConstants.DEFAULT_ARTICLE_PAGE_SIZE)
            localDataSource.clearArticles()
            localDataSource.insertArticles(articles)
            emit(Resource.Success(true))
        }catch (e: HttpException){
            emit(Resource.Error("Something went wrong"))
        }catch (e: IOException){
            emit(Resource.Error("No internet connections"))
        }
    }

    override fun loadMoreArticles(query: String, page: Int): Flow<Resource<Boolean>> = flow{
        emit(Resource.Loading())
        try {
            emit(Resource.Loading())
            val articles = networkDataSource.getNewsArticles(query, page, AppConstants.DEFAULT_ARTICLE_PAGE_SIZE)
            localDataSource.insertArticles(articles)
            emit(Resource.Success(true))
        }catch (e: HttpException){
            emit(Resource.Success(false))
        }catch (e: IOException){
            emit(Resource.Error("No internet connections"))
        }
    }

    override fun getArticles(): Flow<List<Article>> = localDataSource.getAllArticles()
}
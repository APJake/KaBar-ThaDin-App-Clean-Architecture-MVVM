package com.apjake.kabarthadin.di

import android.app.Application
import androidx.room.Room
import com.apjake.kabarthadin.data.datasource.NewsLocalDataSource
import com.apjake.kabarthadin.data.datasource.NewsNetworkDataSource
import com.apjake.kabarthadin.data.local.database.NewsDatabase
import com.apjake.kabarthadin.data.local.datasource.NewsLocalDataSourceImpl
import com.apjake.kabarthadin.data.network.datasource.NewsNetworkDataSourceImpl
import com.apjake.kabarthadin.data.network.service.NewsApi
import com.apjake.kabarthadin.data.repository.NewsRepositoryImpl
import com.apjake.kabarthadin.domain.repository.NewsRepository
import com.apjake.kabarthadin.domain.usecase.LoadMoreArticlesUseCase
import com.apjake.kabarthadin.domain.usecase.SearchArticlesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideLoadMoreArticlesUseCase(
        newsRepository: NewsRepository
    ) = LoadMoreArticlesUseCase(newsRepository)

    @Provides
    @Singleton
    fun provideSearchArticlesUseCase(
        newsRepository: NewsRepository
    ) = SearchArticlesUseCase(newsRepository)

    @Provides
    @Singleton
    fun provideNewsRepository(
        networkDataSource: NewsNetworkDataSource,
        localDataSource: NewsLocalDataSource
    ):NewsRepository = NewsRepositoryImpl(
        networkDataSource = networkDataSource,
        localDataSource = localDataSource
    )

    @Provides
    @Singleton
    fun provideNewsNetworkDataSource(
        api: NewsApi
    ): NewsNetworkDataSource = NewsNetworkDataSourceImpl(api)

    @Provides
    @Singleton
    fun provideNewsLocalDataSource(
        database: NewsDatabase
    ): NewsLocalDataSource = NewsLocalDataSourceImpl(database.dao)

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi{
        return Retrofit.Builder()
            .baseUrl(NewsApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(app: Application): NewsDatabase {
        return Room.databaseBuilder(
            app,
            NewsDatabase::class.java,
            "news_db"
        ).build()
    }

}
package com.example.Laboratorio4.di

import android.app.Application
import androidx.room.Room
import com.example.Laboratorio4.data.local.NewsDao
import com.example.Laboratorio4.data.local.NewsDatabase
import com.example.Laboratorio4.data.local.NewsTypeConvertor
import com.example.Laboratorio4.data.manager.LocalUserManagerImpl
import com.example.Laboratorio4.data.remote.NewsApi
import com.example.Laboratorio4.data.repository.NewsRepositoryImpl
import com.example.Laboratorio4.domain.manager.LocalUserManager
import com.example.Laboratorio4.domain.repository.NewsRepository
import com.example.Laboratorio4.domain.usescases.app_entry.AppEntryUseCases
import com.example.Laboratorio4.domain.usescases.app_entry.ReadAppEntry
import com.example.Laboratorio4.domain.usescases.app_entry.SaveAppEntry
import com.example.Laboratorio4.domain.usescases.news.DeleteArticle
import com.example.Laboratorio4.domain.usescases.news.GetNewById
import com.example.Laboratorio4.domain.usescases.news.GetNews
import com.example.Laboratorio4.domain.usescases.news.NewsUseCases
import com.example.Laboratorio4.domain.usescases.news.SearchNews
import com.example.Laboratorio4.domain.usescases.news.SelectArticle
import com.example.Laboratorio4.domain.usescases.news.SelectArticles
import com.example.Laboratorio4.domain.usescases.news.UpsertArticle
import com.example.Laboratorio4.services.BeaconScanner
import com.example.Laboratorio4.util.Constants.BASE_URL
import com.example.Laboratorio4.util.Constants.NEWS_DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManger(
        application: Application
    ): LocalUserManager = LocalUserManagerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManager: LocalUserManager
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManager),
        saveAppEntry = SaveAppEntry(localUserManager)
    )

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi,
        newsDao: NewsDao
    ) : NewsRepository = NewsRepositoryImpl(newsApi, newsDao)
    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ) : NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsRepository),
            deleteArticle = DeleteArticle(newsRepository),
            selectArticles = SelectArticles(newsRepository),
            selectArticle = SelectArticle(newsRepository),
            searchNewById = GetNewById(newsRepository),
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ) : NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ) : NewsDao = newsDatabase.newsDao

    @Provides
    @Singleton
    fun provideBeaconScanner(
        application: Application
    ) : BeaconScanner = BeaconScanner(application)
}
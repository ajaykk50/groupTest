package com.example.groupsapp.di

import android.content.Context
import com.example.groupsapp.netWorking.GroupDataSource
import com.example.groupsapp.netWorking.GroupDataSourceImpl
import com.example.groupsapp.netWorking.GroupRepository
import com.example.groupsapp.apis.GroupService
import com.example.groupsapp.room.dao.GroupInfoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    @Singleton
    @Provides
    fun providesCrunchRetrofit(@ApplicationContext context: Context): Retrofit {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient().newBuilder()
        httpClient.addInterceptor(logging)

        return Retrofit.Builder().baseUrl("http://huddle-dev.messej.com/huddles/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
    }

    @Singleton
    @Provides
    fun provideGroupService(retrofit: Retrofit): GroupService {
        return retrofit.create(GroupService::class.java)
    }

    @Singleton
    @Provides
    fun providesGroupDataSource(groupService: GroupService): GroupDataSource =
        GroupDataSourceImpl(groupService)

    @Singleton
    @Provides
    fun provideGroupRepository(groupDatasource: GroupDataSource, groupInfoDao: GroupInfoDao): GroupRepository =
        GroupRepository(groupDatasource,groupInfoDao)

}
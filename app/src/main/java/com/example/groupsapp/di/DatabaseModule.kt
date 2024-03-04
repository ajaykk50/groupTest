package com.example.groupsapp.di

import android.content.Context
import androidx.room.Room
import com.example.groupsapp.room.AppDatabase
import com.example.groupsapp.room.dao.GroupInfoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java, "app_database"
        ).build()
    }

    @Provides
    fun provideGroupInfoDao(database: AppDatabase): GroupInfoDao {
        return database.groupInfoDao()
    }
}
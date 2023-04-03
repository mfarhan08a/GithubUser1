package com.mfarhan08a.githubuser.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.mfarhan08a.githubuser.data.UserRepository
import com.mfarhan08a.githubuser.data.local.room.FavoriteDatabase
import com.mfarhan08a.githubuser.data.remote.retrofit.ApiConfig

object Injection {
    fun provideRepository(context: Context): UserRepository {
        val apiService = ApiConfig.getApiService()
        val database = FavoriteDatabase.getInstance(context)
        val dao = database.favoriteDao()
        return UserRepository.getInstance(apiService, dao)
    }
}
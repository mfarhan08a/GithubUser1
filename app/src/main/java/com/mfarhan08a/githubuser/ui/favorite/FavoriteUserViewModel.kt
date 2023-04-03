package com.mfarhan08a.githubuser.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mfarhan08a.githubuser.data.UserRepository
import com.mfarhan08a.githubuser.data.local.entity.FavoriteEntity

class FavoriteUserViewModel(private val repository: UserRepository) : ViewModel() {
    fun getFavoritedUserList(): LiveData<List<FavoriteEntity>> = repository.getFavoritedUser()
}
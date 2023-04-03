package com.mfarhan08a.githubuser.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mfarhan08a.githubuser.data.UserRepository
import com.mfarhan08a.githubuser.data.remote.response.DetailUserResponse
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: UserRepository) : ViewModel() {

    fun getDetail(username: String) = repository.findUserDetailsByUsername(username)

    fun addToFavorite(user: DetailUserResponse) = viewModelScope.launch {
        repository.addToFavorite(user)
    }

    fun deleteFavorited(username: String) = viewModelScope.launch {
        repository.deleteFavorite(username)
    }

    fun isFavorited(username: String) : Boolean = repository.isFavorited(username)
}
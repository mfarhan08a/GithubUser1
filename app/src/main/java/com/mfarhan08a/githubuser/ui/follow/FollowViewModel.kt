package com.mfarhan08a.githubuser.ui.follow

import androidx.lifecycle.ViewModel
import com.mfarhan08a.githubuser.data.UserRepository

class FollowViewModel(private val repository: UserRepository): ViewModel() {
    fun getFollowers(username: String) = repository.getFollowers(username)
    fun getFollowing(username: String) = repository.getFollowing(username)
}
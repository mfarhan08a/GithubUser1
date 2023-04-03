package com.mfarhan08a.githubuser.ui.main

import androidx.lifecycle.ViewModel
import com.mfarhan08a.githubuser.data.UserRepository

class HomeViewModel(private val repository: UserRepository): ViewModel() {
    fun findUserByUserName(username: String) = repository.findUserListByUsername(username)
}
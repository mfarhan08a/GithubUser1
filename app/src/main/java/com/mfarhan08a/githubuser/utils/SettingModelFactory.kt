package com.mfarhan08a.githubuser.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.mfarhan08a.githubuser.data.local.preference.SettingPreferences
import com.mfarhan08a.githubuser.ui.settings.SettingsViewModel

class SettingModelFactory(private val pref: SettingPreferences) : NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SettingsViewModel::class.java)) {
            return SettingsViewModel(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
    }
}
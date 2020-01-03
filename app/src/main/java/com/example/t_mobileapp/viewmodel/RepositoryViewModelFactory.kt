package com.example.t_mobileapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.t_mobileapp.model.UserBioInfo


@Suppress("UNCHECKED_CAST")
class RepositoryViewModelFactory(val application: Application, val userBioInfo: UserBioInfo):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RepositoryViewModel(application, userBioInfo) as T
    }
}


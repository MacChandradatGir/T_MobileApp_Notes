package com.example.t_mobileapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.t_mobileapp.model.UserBio


@Suppress("UNCHECKED_CAST")
class RepositoryViewModelFactory(val application: Application, val userBio:
    UserBio):ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return RepositoryViewModel(application, userBio) as T
    }
}


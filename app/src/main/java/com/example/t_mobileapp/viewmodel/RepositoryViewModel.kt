package com.example.t_mobileapp.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.t_mobileapp.model.Repository
import com.example.t_mobileapp.model.UserBio
import com.example.t_mobileapp.network.GitService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RepositoryViewModel(val application: Application, val userBio: UserBio)
    : ViewModel(){

    private val compositeDisposable = CompositeDisposable()
    private val cacheFile = application.cacheDir
    val userName = MutableLiveData<String>()
    val useEmail = MutableLiveData<String>()
    val jointDate = MutableLiveData<String>()
    val userLocation = MutableLiveData<String>()
    val followersCount = MutableLiveData<String>()
    val followingCount = MutableLiveData<String>()
    val userAvatar = MutableLiveData<String>()
    val userBiog = MutableLiveData<String>()
    val allRepository = MutableLiveData<List<Repository>>()
    val filteredRepositoryList = MutableLiveData<List<Repository>>()
    init {
        userName.postValue(userBio.login)
        useEmail.postValue(userBio.email.toString()?: "No Email Available")
        jointDate.postValue(userBio.createdAt)
        userLocation.postValue(userBio.location)
        followersCount.postValue("${userBio.followers} Followers")
        followingCount.postValue("Following ${userBio.following}")
        userBiog.postValue(userBio.bio.toString()?: "No Bio Provided")
        userAvatar.postValue(userBio.avatarUrl)
        getUserRepositories()
    }

    fun onRepositiryFilteringTextChanged(currentInput: CharSequence, start:Int, before:Int, count: Int){
        val filterString =currentInput.toString()
        val filteredList =
            allRepository.value?.filter {it.name.startsWith(filterString)}
        filteredRepositoryList.postValue(filteredList)
    }

    private fun getUserRepositories() {
        compositeDisposable.add(GitService
            .gitService(cacheFile)
            .getUserRepo(userBio.login)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                userRepositoryResults -> allRepository.postValue(userRepositoryResults)
            }
        )
    }


}
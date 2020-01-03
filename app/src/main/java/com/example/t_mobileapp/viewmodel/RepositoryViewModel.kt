package com.example.t_mobileapp.viewmodel

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.t_mobileapp.model.Repository
import com.example.t_mobileapp.model.UserBioInfo
import com.example.t_mobileapp.network.GitService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class RepositoryViewModel(val application: Application, val userBioInfo: UserBioInfo)
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
        userName.postValue(userBioInfo.login)
        useEmail.postValue(userBioInfo.email.toString()?: "No Email Available")
        jointDate.postValue(userBioInfo.created_at)
        userLocation.postValue(userBioInfo.location)
        followersCount.postValue("${userBioInfo.followers} Followers")
        followingCount.postValue("Following ${userBioInfo.following}")
        userBiog.postValue(userBioInfo.bio.toString()?: "No Bio Provided")
        userAvatar.postValue(userBioInfo.avatar_url)
        getUserRepositories()
    }

    fun onRepositoryFilteringTextChanged(currentInput: CharSequence, start:Int, before:Int, count: Int){
        val filterString =currentInput.toString()
        val filteredList =
            allRepository.value?.filter {it.name.startsWith(filterString)}
        filteredRepositoryList.postValue(filteredList)
    }

    private fun getUserRepositories() {
        compositeDisposable.add(GitService
            .gitService(cacheFile)
            .getUserRepo(userBioInfo.login)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{
                repository -> allRepository.postValue(repository)
            }
        )
    }


}
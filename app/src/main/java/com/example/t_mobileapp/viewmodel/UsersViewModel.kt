package com.example.t_mobileapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.t_mobileapp.model.Item

import com.example.t_mobileapp.model.UserBio
import com.example.t_mobileapp.network.GitService
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers



class UsersViewModel(application: Application) : AndroidViewModel(application){

    private val compositeDisposable = CompositeDisposable()
    val userToAdd = MutableLiveData<UserBio>()
    val clearList = MutableLiveData<Boolean>()
    private val cacheFile = application.cacheDir


    fun onUserNameTextChanged(currentInput: CharSequence,start: Int,before : Int,
                              count :Int){
        val currentUserNameEntered = currentInput.toString()
        clearList.postValue(true)
         getUsers(currentUserNameEntered)
    }



    fun getUsers(userName: String) {
        compositeDisposable.add(
            getUserSearchObservable(userName)
                .subscribeOn(Schedulers.io())
                .flatMap { item -> getUserInfo(item) }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe() )

    }

    private fun getUserSearchObservable(userName: String) =
        GitService
            .gitService(cacheFile)
            .getUser(userName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { users -> Observable.fromIterable(users.items).take(10) }

    private fun getUserInfo(item: Item)=
        GitService
            .gitService(cacheFile)
            .getUserBio(item.login)
            .map { user -> userToAdd.postValue(user) }
            .subscribeOn(Schedulers.io())


}
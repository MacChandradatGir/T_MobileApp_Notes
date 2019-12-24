package com.example.t_mobileapp.viewmodel

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.t_mobileapp.model.User
import com.example.t_mobileapp.network.GitFactory
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

class GitViewModel : ViewModel(){

    private val gitFactory = GitFactory()

    fun getUsers(userName : String): Observable<User> {
        return gitFactory.getUsers(userName)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }



}
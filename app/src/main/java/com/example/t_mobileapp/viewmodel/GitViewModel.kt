package com.example.t_mobileapp.viewmodel

import android.app.Activity
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.t_mobileapp.adapter.UserAdapter
import com.example.t_mobileapp.model.Item
import com.example.t_mobileapp.model.User
import com.example.t_mobileapp.network.GitFactory
import com.example.t_mobileapp.view.MainActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers



class GitViewModel : ViewModel(){

    private val gitFactory = GitFactory()

     var mainActivity: MainActivity = MainActivity()

    private val compositeDisposable = CompositeDisposable()
    private lateinit var viewModel: GitViewModel

    fun getUsers(userName : String): Observable<User> {
        return gitFactory.getUsers(userName)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }


    fun makeApiCall(searchEditText: String) {
        // var searchEditText = search_edittext.text.toString().trim()

        compositeDisposable.add(
            viewModel.getUsers("${searchEditText}")
                .subscribe({user ->
                    mainActivity.displayUsers(user.items)
                }, {throwable ->
                    Log.e("TAG_ERROR", throwable.toString())
                })

        )
    }



}
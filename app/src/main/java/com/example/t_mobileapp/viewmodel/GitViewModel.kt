package com.example.t_mobileapp.viewmodel

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.t_mobileapp.adapter.UserAdapter
import com.example.t_mobileapp.model.Repository

import com.example.t_mobileapp.model.User
import com.example.t_mobileapp.model.UserBio
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
    //We will use LiveData to hold the results from the API Call
    val userInfo : MutableLiveData<User> = MutableLiveData()
    val bioInfo : MutableLiveData<UserBio> = MutableLiveData()
    val repoInfo : MutableLiveData<Repository> = MutableLiveData()


    //NOTE:  Create a function that uses the textwatcher method "onTextChanged"
    //         to handle the action of the user entering input
    fun onUserNameTextChanged(currentInput: CharSequence,start: Int,before : Int,
                              count :Int){
        val currentUserNameEntered = currentInput.toString()
        makeApiCall(currentUserNameEntered)
    }

//    fun onSelectedUserBio(selectedUser: String){
//       // Toast.makeText(this.mainActivity, "I am clicked - viewmodel", Toast.LENGTH_SHORT).show()
//    }

    fun getUsers(userName : String): Observable<User> {
        return gitFactory.getUsers(userName)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    fun getUserBio(userName: String): Observable<UserBio>{
        return gitFactory.getUserBio(userName)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    fun getUserRepos(userName: String) : Observable<Repository>{
        return gitFactory.getUserRepos(userName)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }


    fun makeApiCall(searchEditText: String) {
        // var searchEditText = search_edittext.text.toString().trim()

        compositeDisposable.add(
            getUsers("${searchEditText}")
                .subscribe({user ->
                    //Post results to the LiveData Object
                   userInfo.postValue(user)
                }, {throwable ->
                    Log.e("TAG_ERROR", throwable.toString())
                })

        )
    }

    fun makeBioApiCall(selectedUser: String){
        getUserBio("${selectedUser}")
            .subscribe({userBio ->
                bioInfo.postValue(userBio)
            }, {throwable ->
                Log.e("TAG_ERROR", throwable.toString())
            })
    }

    fun makeReposApiCall(userName: String){
        getUserRepos("${userName}")
            .subscribe({userRepo ->
                repoInfo.postValue(userRepo)
            }, {
                throwable ->
                Log.e("TAG_ERROR", throwable.toString())
            })

    }



//    fun getUserInfo(userSelected: String){
//       compositeDisposable.add(
//           getUserBio("${userSelected}")
//               .subscribe({user->
//                   bioInfo.postValue(user)
//               }, {throwable ->
//                   Log.e("TAG_ERROR", throwable.toString())
//               })
//       )
//    }


}
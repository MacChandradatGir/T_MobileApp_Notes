package com.example.t_mobileapp.network

import com.example.t_mobileapp.model.Repository
import com.example.t_mobileapp.model.User
import com.example.t_mobileapp.model.UserBio
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class GitFactory{

//    private val BASE_URL = "https://api.github.com"
//    private var gitService: GitService
//
//
//    init {
//        gitService = createService(retrofitInstance())
//    }
//
//    private fun retrofitInstance(): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//            .build()
//    }
//
//    private fun createService(retrofit: Retrofit): GitService {
//        return retrofit.create(GitService::class.java)
//    }
//
//    fun getUsers(userName: String): io.reactivex.Observable<User> {
//        return gitService.getUser(userName)
//    }
//
//    fun getUserBio(userName: String): io.reactivex.Observable<UserBio>{
//        return gitService.getUserBio(userName)
//    }
//
//    fun getUserRepos(userName: String): io.reactivex.Observable<Repository>{
//        return gitService.getUserRepo(userName)
//    }
//

}
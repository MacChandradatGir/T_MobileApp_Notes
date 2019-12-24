package com.example.t_mobileapp.network

import android.database.Observable
import com.example.t_mobileapp.model.User
import retrofit2.http.GET
import retrofit2.http.Query

interface GitService {
    @GET("/search/users")
    fun getUser(@Query("q") userName: String): io.reactivex.Observable<User>


}
package com.example.t_mobileapp.network


import com.example.t_mobileapp.model.Repository
import com.example.t_mobileapp.model.User
import com.example.t_mobileapp.model.UserBio
import com.example.t_mobileapp.model.UserBioInfo
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.io.File

interface GitService {
    companion object{
        fun gitService(cacheFile : File) =
            RetrofitHelper.getRetrofitInstance(BASE_URL, cacheFile).create(GitService::class.java)
    }

    @GET("/search/users")
    fun getUser(@Query("q") userName: String): io.reactivex.Observable<User>

    @GET("users/{user_name}")
    fun getUserBio(@Path("user_name") userName: String): io.reactivex.Observable<UserBioInfo>


    @GET("users/{user_name}/repos")
    fun getUserRepo(@Path("user_name") userName: String): io.reactivex.Observable<List<Repository>>

}
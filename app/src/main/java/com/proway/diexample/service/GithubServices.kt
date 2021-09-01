package com.proway.diexample.service

import com.proway.diexample.model.User
import retrofit2.Call
import retrofit2.http.GET

interface GithubServices {

    @GET("/users")
    fun fetchUsers(): Call<List<User>>
}
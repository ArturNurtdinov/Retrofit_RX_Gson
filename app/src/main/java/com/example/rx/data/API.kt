package com.example.rx.data

import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface API {
    @GET("users")
    fun getUserDetails(@Query("q") filter: String): Single<Response<UsersList>>
}
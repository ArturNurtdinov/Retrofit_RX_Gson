package com.example.rx.data

import com.google.gson.annotations.SerializedName

class Users{
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("login")
    var login: String = ""
    @SerializedName("score")
    var score: Float = 0.0f
}
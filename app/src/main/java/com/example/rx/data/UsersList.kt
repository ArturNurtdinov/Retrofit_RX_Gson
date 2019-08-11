package com.example.rx.data

import com.google.gson.annotations.SerializedName

class UsersList {
    @SerializedName("items")
    var users : List<Users>? = null
    @SerializedName("total_count")
    var totalCount: Int = 0
    @SerializedName("incomplete_results")
    var incompleteResults: Boolean = false
}
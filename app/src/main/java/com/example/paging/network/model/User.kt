package com.example.paging.network.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


@Parcelize
@JsonClass(generateAdapter = true)
data class UserListResponse(
    @Json(name = "page") val totalUsers: Int,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "data") val userList: List<User>
) : Parcelable

@Parcelize
@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "id") val id: Int,
    @Json(name = "email") val email: String,
    @Json(name = "first_name") val firstName: String,
    @Json(name = "last_name") val lastName: String,
    @Json(name = "avatar") val avatar: String
) : Parcelable

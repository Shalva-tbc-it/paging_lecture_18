package com.example.paging.network.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize


@Parcelize
@JsonClass(generateAdapter = true)
data class UserListResponse(
    @Json(name = "totalUsers") val totalUsers: Int,
    @Json(name ="totalPages") val totalPages: Int,
    @Json(name = "data") val userList: List<User>
) : Parcelable
@Parcelize
@JsonClass(generateAdapter = true)
data class User(
    @field:Json(name = "id") val id: Int,
    @field:Json(name = "email") val email: String,
    @field:Json(name = "first_name") val firstName: String,
    @field:Json(name = "last_name") val lastName: String,
    @field:Json(name = "avatar") val avatar: String
) : Parcelable

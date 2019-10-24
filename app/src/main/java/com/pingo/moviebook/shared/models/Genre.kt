package com.pingo.moviebook.shared.models


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Genre(
    @field:Json(name = "id")
    var id: Int?,
    @field:Json(name = "name")
    var name: String
): Parcelable

{
    override fun toString(): String = name
}
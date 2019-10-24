package com.pingo.moviebook.shared.models


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GenreList(
    @field:Json(name = "genres")
    var genres: List<Genre?>?
): Parcelable
package com.pingo.moviebook.shared.models


import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieObj(
    @field:Json(name = "page")
    val page: Int?,
    @field:Json(name = "results")
    val results: List<Result?>,
    @field:Json(name = "total_pages")
    val totalPages: Int?,
    @field:Json(name = "total_results")
    val totalResults: Int?
):Parcelable
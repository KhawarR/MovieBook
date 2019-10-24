package com.pingo.moviebook.shared.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BuiltBy(
    var avatar: String = "",

    var href: String = "",

    var username: String = ""
):Parcelable
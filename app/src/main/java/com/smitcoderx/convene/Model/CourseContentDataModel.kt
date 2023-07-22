package com.smitcoderx.convene.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CourseContentDataModel(
    var id: String,
    var courseContentTitle: String,
    var courseContentDescription: String,
    var duration: Float,
    var isWatching: Boolean,
    var isFinished: Boolean,
    var isNext: Boolean,
    var watchTime: Float

) : Parcelable
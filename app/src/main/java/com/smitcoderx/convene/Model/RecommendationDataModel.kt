package com.smitcoderx.convene.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecommendationDataModel(
    var id: String,
    var user: String,
    var userImg: String,
    var positionAtTime: String,
    var relationship: String,
    var addRecommendation: String,
    var isVisibleToAll: Boolean,
) : Parcelable

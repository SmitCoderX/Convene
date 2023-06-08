package com.smitcoderx.convene.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class EducationDataModel(
    var id: String,
    var schoolName: String,
    var degree: String,
    var fieldOfStudy: String,
    var schoolImg: String,
    var startData: String,
    var endDate: String,
    var grade: String,
    var activities: String,
    var description: String,
    var skills: ArrayList<String>,
): Parcelable
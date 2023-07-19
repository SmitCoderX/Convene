package com.smitcoderx.convene.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ExperienceDataModel(
    var id: String? = "",
    var jobPost: String? = "",
    var companyName: String? = "",
    var startTime: String? = "",
    var companyImg: String? = "",
    var endTime: String? = "",
    var achievements: String? = "",
    var skills: ArrayList<String>? = arrayListOf(),
    var description: String? = "",
    var employmentType: String? = "",
    var location: String? = "",
    var isCurrentlyWorking: Boolean? = false,
    var locationType: String? = "",
    var industryName: String? = ""
) : Parcelable
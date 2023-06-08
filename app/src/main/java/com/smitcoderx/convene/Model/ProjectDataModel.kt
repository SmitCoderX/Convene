package com.smitcoderx.convene.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProjectDataModel(
    var id: String,
    var projectName: String,
    var projectUrl: String,
    var description: String,
    var skills: ArrayList<String>,
    var isCurrentlyWorking: Boolean,
    var startDate: String,
    var endDate: String,
    var contributors: ArrayList<String> = arrayListOf(),
    var associatedWith: ExperienceDataModel
): Parcelable

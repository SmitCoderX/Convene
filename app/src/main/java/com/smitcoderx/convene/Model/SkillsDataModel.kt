package com.smitcoderx.convene.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SkillsDataModel(
    var id: String,
    var skillName: String,
    var endorsement: Int,
    var skillUsedIn: String,
    var tag: ArrayList<String>,

): Parcelable
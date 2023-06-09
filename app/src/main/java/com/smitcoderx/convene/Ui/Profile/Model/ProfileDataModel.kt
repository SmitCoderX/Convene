package com.smitcoderx.convene.Ui.Profile.Model

import android.os.Parcelable
import com.smitcoderx.convene.Model.CoursesDataModel
import com.smitcoderx.convene.Model.EducationDataModel
import com.smitcoderx.convene.Model.ExperienceDataModel
import com.smitcoderx.convene.Model.LicenseDataModel
import com.smitcoderx.convene.Model.ProjectDataModel
import com.smitcoderx.convene.Model.RecommendationDataModel
import com.smitcoderx.convene.Model.SkillsDataModel
import com.smitcoderx.convene.Ui.Login.Models.LoginData
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProfileDataModel(
    var id: String = "",
    var description: String = "",
    var experience: MutableList<ExperienceDataModel>? = arrayListOf(),
    var dob: String = "",
    var followersCount: Int? = null,
    var followingList: ArrayList<LoginData>? = arrayListOf(),
    var connectionList: ArrayList<LoginData>? = arrayListOf(),
    var connections: Int? = null,
    var education: ArrayList<EducationDataModel>? = arrayListOf(),
    var licenseAndCertificate: ArrayList<LicenseDataModel>? = arrayListOf(),
    var projects: ArrayList<ProjectDataModel>? = arrayListOf(),
    var skills: ArrayList<SkillsDataModel>? = arrayListOf(),
    var receivedRecommendation: ArrayList<RecommendationDataModel>? = arrayListOf(),
    var givenRecommendation: ArrayList<RecommendationDataModel>? = arrayListOf(),
    var courseEnrolled: ArrayList<CoursesDataModel>? = arrayListOf(),
    var suggestedCourses: ArrayList<CoursesDataModel>? = arrayListOf(),
    var tags: ArrayList<String>? = arrayListOf()
): Parcelable


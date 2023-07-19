package com.smitcoderx.convene.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CoursesDataModel(
    var id: String,
    var courseName: String,
    var courseImg: String,
    var courseDescription: String,
    var courseInstructor: ArrayList<String>,
    var courseContent: ArrayList<CourseContentDataModel>,
    var isEnrolled: Boolean,
    var isFinished: Boolean,
    var isPassed: Boolean,
    var quiz: HashMap<String, String>,
    var totalQuestions: Int,
    var rightAnswers: Int,
    var percentage: Float,
    var courseSection: Int,
    var sectionNames: ArrayList<String>,
    var courseTracking: Float,
    var tags: ArrayList<String>
) : Parcelable

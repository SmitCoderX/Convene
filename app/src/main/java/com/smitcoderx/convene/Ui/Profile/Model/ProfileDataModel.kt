package com.smitcoderx.convene.Ui.Profile.Model

import java.security.cert.Certificate

data class ProfileDataModel(
    var description: String,
    var experience: ArrayList<String>,        // Will Change to custom data class
    var dob: String,
    var education: ArrayList<String>,    // Will Change to custom data class
    var licenseAndCertificate: ArrayList<String>,       // Will Change to custom data class
    var projects: ArrayList<String>,     // Will Change to custom data class
    var skills: ArrayList<String>,
    var recommedations: ArrayList<String>,     // Will Change to custom data class
    var courseEnrolled: ArrayList<String>,     // Will Change to custom data class
    var suggestedCourses: ArrayList<String>     // Will Change to custom data class
)


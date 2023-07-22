package com.smitcoderx.convene.Model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LicenseDataModel(
    var id: String = "",
    var name: String = "",
    var issuingOrganization: String = "",
    var licenseImg: String = "",
    var issueDate: String = "",
    var expirationDate: String = "",
    var credentialID: String = "",
    var credentialUrl: String = "",
    var skills: ArrayList<String> = arrayListOf()
) : Parcelable

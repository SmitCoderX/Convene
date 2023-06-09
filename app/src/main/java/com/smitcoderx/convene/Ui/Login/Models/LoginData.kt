package com.smitcoderx.convene.Ui.Login.Models

import android.os.Parcelable
import com.smitcoderx.convene.Ui.Profile.Model.ProfileDataModel
import kotlinx.parcelize.Parcelize


@Parcelize
data class LoginData(
    var displayName: String = "",
    var email: String = "",
    var uid: String = "",
    var phoneNumber: String = "",
    var photoUrl: String = "",
    var profileData: ProfileDataModel? = null
) : Parcelable
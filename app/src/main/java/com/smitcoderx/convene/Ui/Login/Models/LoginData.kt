package com.smitcoderx.convene.Ui.Login.Models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class LoginData(
    var displayName: String,
    var email: String,
    var uid: String,
    var phoneNumber: String,
    var photoUrl: String
) : Parcelable

package com.smitcoderx.convene.Ui.Profile

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.smitcoderx.convene.Ui.Login.Models.LoginData
import com.smitcoderx.convene.Ui.Profile.Model.ProfileDataModel
import com.smitcoderx.convene.Utils.Constants
import com.smitcoderx.convene.Utils.Constants.TAG
import com.smitcoderx.convene.Utils.Resource
import com.smitcoderx.convene.Utils.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProfileRepository @Inject constructor(
    private val user: FirebaseAuth
) {
    private val userCollectionRef = Firebase.firestore.collection("users")
    suspend fun updateUserData(id: String, profileDataModel: LoginData): Resource<String?> {
        return try {
            userCollectionRef.document(id).set(profileDataModel).await()
            Log.i(TAG, "Add Profile UserData Done -->")
            Resource.Success("Data Updated in Profile")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(TAG, "Add Profile UserData: ${e.message}", e.cause)
            Resource.Error(e.message.toString())
        }
    }

}
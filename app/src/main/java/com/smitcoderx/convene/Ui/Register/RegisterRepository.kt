package com.smitcoderx.convene.Ui.Register

import android.net.Uri
import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.smitcoderx.convene.Ui.Login.Models.LoginData
import com.smitcoderx.convene.Utils.Constants
import com.smitcoderx.convene.Utils.Resource
import com.smitcoderx.convene.Utils.await
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RegisterRepository @Inject constructor(private val auth: FirebaseAuth) {

    private val userCollectionRef = Firebase.firestore.collection("users")

    private suspend fun saveUserDate(id: String, loginData: LoginData) {
        try {
            userCollectionRef.document(id).set(loginData).await()
            Log.i(Constants.TAG, "Register User Data Saved -->")
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e(Constants.TAG, "saveUserDate: ${e.message}", e.cause)
        }
    }


    suspend fun createAccount(
        username: String,
        email: String,
        password: String,
        mobileNo: String
    ): Resource<FirebaseUser?> {
        val uri = Uri.parse("${Constants.IMAGE_URL}${username}.png?apikey=${Constants.MULTI_VAR}")
        return try {
            val result = auth.createUserWithEmailAndPassword(email, password).await()
            result?.user?.updateProfile(
                UserProfileChangeRequest.Builder().setDisplayName(username).setPhotoUri(uri).build()
            )?.await()
            saveUserDate(
                result.user?.uid.toString(),
                LoginData(username, email, result?.user?.uid.toString(), "", uri.toString(), null)
            )
            Resource.Success(result.user)
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message.toString())
        }
    }

    fun signOut() {
        auth.signOut()
    }
}
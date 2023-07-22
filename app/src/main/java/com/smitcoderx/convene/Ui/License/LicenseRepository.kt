package com.smitcoderx.convene.Ui.License

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.smitcoderx.convene.Model.LicenseDataModel
import com.smitcoderx.convene.Utils.Constants.LIC
import com.smitcoderx.convene.Utils.Constants.TAG
import com.smitcoderx.convene.Utils.Resource
import com.smitcoderx.convene.Utils.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LicenseRepository @Inject constructor(
    private val auth: FirebaseAuth
){

    private val userCollectionRef = Firebase.firestore.collection("users")

    suspend fun fetchLicenseAndCertification(id: String): Resource<ArrayList<LicenseDataModel?>> {
        val fetchLicenseArrayList = ArrayList<LicenseDataModel?>()
        return try {
            val documentSnapshot = userCollectionRef.document(id).collection(LIC).get().await()
            documentSnapshot.documents.forEach {
                fetchLicenseArrayList.add(it.toObject(LicenseDataModel::class.java))
            }
            Log.i(TAG, "fetchLicenseAndCertificationInfo: ${documentSnapshot.documents}")
            Resource.Success(fetchLicenseArrayList)
        } catch (e: Exception) {
            Log.e(TAG, "fetchLicenseAndCertificationError: ${e.message}")
            Resource.Error(e.message.toString())
        }
    }

    suspend fun addLicenseAndCertification(id: String, licenseDataModel: LicenseDataModel): Resource<String> {
        return try {
            userCollectionRef.document(id).collection(LIC).document()
                .set(licenseDataModel, SetOptions.merge()).await()
            Resource.Success("Data Saved Successfully")
        } catch (e: Exception) {
            Resource.Error(e.message.toString())
        }
    }
}
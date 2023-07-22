package com.smitcoderx.convene.Ui.Education

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.smitcoderx.convene.Model.EducationDataModel
import com.smitcoderx.convene.Utils.Constants.EDU
import com.smitcoderx.convene.Utils.Constants.TAG
import com.smitcoderx.convene.Utils.Resource
import com.smitcoderx.convene.Utils.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EducationRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) {

    private val userCollectionRef = Firebase.firestore.collection("users")
    suspend fun fetchEducation(id: String): Resource<ArrayList<EducationDataModel?>> {
        val fetchEducation = ArrayList<EducationDataModel?>()
        return try {
            val documentSnapshot = userCollectionRef.document(id).collection(EDU).get().await()
            documentSnapshot.documents.forEach {
                fetchEducation.add(it.toObject(EducationDataModel::class.java))
            }
            Log.d(TAG, "fetchEducationSuccess: ${documentSnapshot.documents}")
            Resource.Success(fetchEducation)
        } catch (e: Exception) {
            Log.e(TAG, "fetchEducationError: ${e.message.toString()}", e.cause)
            Resource.Error(e.message.toString())
        }

    }

    suspend fun addEducation(id: String, educationDataModel: EducationDataModel): Resource<String> {
        return try {
            userCollectionRef.document(id).collection(EDU).document()
                .set(educationDataModel, SetOptions.merge()).await()
            Resource.Success("Data Saved Successfully")
        } catch (e: Exception) {
            Log.e(TAG, "addEducation: ${e.message.toString()}", e.cause)
            Resource.Error(e.message.toString())
        }
    }

}
package com.smitcoderx.convene.Ui.Login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.ktx.Firebase
import com.smitcoderx.convene.Utils.Constants
import com.smitcoderx.convene.Utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository
): ViewModel() {

    val isNetworkConnectedLiveData = MutableLiveData<Boolean>()
    private val _signInMutableLiveData = MutableLiveData<Resource<FirebaseUser>>()
    private var _signIn = MutableLiveData<Resource<FirebaseUser>>()
    val signInLiveData: LiveData<Resource<FirebaseUser>>
        get() = _signInMutableLiveData

    fun signInEmail(email: String, password: String): LiveData<Resource<FirebaseUser>> {
        viewModelScope.launch {
            _signIn = safeHandleSignIn(email, password)
        }
        return _signIn
    }

    private suspend fun safeHandleSignIn(email: String, password: String): MutableLiveData<Resource<FirebaseUser>> {
        _signInMutableLiveData.value = Resource.Loading()
        try {
            if(isNetworkConnectedLiveData.value == true) {
                val response = repository.signInWithEmail(email, password)
                _signInMutableLiveData.value = Resource.Success(response)
            } else {
                _signInMutableLiveData.value = Resource.Error("No Internet Connection")
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> _signInMutableLiveData.value = Resource.Error("Network Failure")
                else -> {
                    Log.d(Constants.TAG, "safeHandleLogin: ${t.message.toString()}")
                    _signInMutableLiveData.value = Resource.Error(t.message.toString())
                }
            }
        }
        return _signInMutableLiveData
    }

}
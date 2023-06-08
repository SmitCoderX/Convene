package com.smitcoderx.convene.Ui.Login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.smitcoderx.convene.Ui.Login.Models.LoginData
import com.smitcoderx.convene.Utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: LoginRepository
) : ViewModel() {

    val isNetworkConnectedLiveData = MutableLiveData<Boolean>()
    private val _signInMutableLiveData = MutableLiveData<Resource<FirebaseUser?>>()
    val signInMutableLiveData: LiveData<Resource<FirebaseUser?>>
        get() = _signInMutableLiveData

    fun signInAccount(email: String, password: String) = viewModelScope.launch {
        _signInMutableLiveData.value = Resource.Loading()
        if (isNetworkConnectedLiveData.value == true) {
            _signInMutableLiveData.value = Resource.Loading()
            val result = repository.loginAccount(email, password)
            _signInMutableLiveData.value = result
        } else {
            _signInMutableLiveData.value = Resource.Error("No Internet Connection")
        }
    }

    fun loginSignOut() {
        repository.signOut()
    }

}
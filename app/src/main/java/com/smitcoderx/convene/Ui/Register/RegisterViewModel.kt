package com.smitcoderx.convene.Ui.Register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.smitcoderx.convene.Utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val repository: RegisterRepository
) : ViewModel() {

    val isNetworkConnectedLiveData = MutableLiveData<Boolean>()
    private val _createAccountLiveData = MutableLiveData<Resource<FirebaseUser?>>()
    val createAccountLiveData: LiveData<Resource<FirebaseUser?>>
        get() = _createAccountLiveData

    fun createAccountEmail(username: String, email: String, password: String, mobileNo: String) =
        viewModelScope.launch {
            _createAccountLiveData.value = Resource.Loading()
            if (isNetworkConnectedLiveData.value == true) {
                _createAccountLiveData.value = Resource.Loading()
                val result = repository.createAccount(username, email, password, mobileNo)
                _createAccountLiveData.value = result
            } else {
                _createAccountLiveData.value = Resource.Error("No Internet Connection")
            }
        }

    fun registerSignOut() {
        repository.signOut()
    }
}
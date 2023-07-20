package com.smitcoderx.convene.Ui.Education

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.smitcoderx.convene.Model.EducationDataModel
import com.smitcoderx.convene.Utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EducationViewModel @Inject constructor(
    private val repository: EducationRepository
) : ViewModel() {

    val isNetworkConnectedLiveData = MutableLiveData<Boolean>()
    private val _addEducation = MutableLiveData<Resource<String>>()
    val addEducation: LiveData<Resource<String>>
        get() = _addEducation

    private val _fetchEducation = MutableLiveData<Resource<ArrayList<EducationDataModel?>>>()
    val fetchEducation: LiveData<Resource<ArrayList<EducationDataModel?>>>
        get() = _fetchEducation

    fun fetchEducationDetails(id: String) = viewModelScope.launch {
        viewModelScope.launch {
            _fetchEducation.value = Resource.Loading()
            if (isNetworkConnectedLiveData.value == true) {
                _fetchEducation.value = Resource.Loading()
                val result = repository.fetchEducation(id)
                _fetchEducation.value = result
            } else {
                _fetchEducation.value = Resource.Error("No Internet Connection")
            }
        }
    }


    fun addEducationData(id: String, educationDataModel: EducationDataModel) =
        viewModelScope.launch {
            _addEducation.value = Resource.Loading()
            if (isNetworkConnectedLiveData.value == true) {
                _addEducation.value = Resource.Loading()
                val result = repository.addEducation(id, educationDataModel)
                _addEducation.value = result
            } else {
                _addEducation.value = Resource.Error("No Internet Connection")

            }
        }

}
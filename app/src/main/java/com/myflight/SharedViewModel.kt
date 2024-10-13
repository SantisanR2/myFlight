package com.myflight

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    private val _outputData = MutableLiveData<OutputData>()
    val outputData: LiveData<OutputData> get() = _outputData

    fun setOutputData(data: OutputData) {
        _outputData.value = data
    }
}

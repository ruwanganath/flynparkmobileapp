package com.example.flynparkmelb.ui.searchparking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchParkingViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is searchparking Fragment"
    }
    val text: LiveData<String> = _text
}
package com.example.flynparkmelb.ui.dash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "0Hrs : 0Mins"
    }
    val text: LiveData<String> = _text
}
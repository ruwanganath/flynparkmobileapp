package com.example.flynparkmelb.ui.signoff

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SignoffViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is sign off Fragment"
    }
    val text: LiveData<String> = _text
}
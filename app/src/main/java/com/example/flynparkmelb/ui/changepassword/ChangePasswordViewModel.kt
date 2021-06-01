package com.example.flynparkmelb.ui.changepassword

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ChangePasswordViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is change password Fragment"
    }
    val text: LiveData<String> = _text
}
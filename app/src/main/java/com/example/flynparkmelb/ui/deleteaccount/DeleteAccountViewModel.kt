package com.example.flynparkmelb.ui.deleteaccount

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DeleteAccountViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is delete account Fragment"
    }
    val text: LiveData<String> = _text
}
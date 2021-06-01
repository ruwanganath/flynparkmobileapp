package com.example.flynparkmelb.ui.about

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
// Fly&Park Melbourne About view model
class AboutViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is about Fragment"
    }
    val text: LiveData<String> = _text
}
package com.example.flynparkmelb

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.ActionBar

// Forgot password activity class
class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        changeTitle(getString(R.string.text_forgot_password), ColorDrawable(Color.parseColor("#4F1177")) )
    }

    // function to change the support bar of the activity
    private fun changeTitle(titletext: String?, colourBar: ColorDrawable? ) {
        val title = TextView(this)
        title.text = titletext
        title.textSize = 24f
        title.setTypeface(title.typeface, Typeface.BOLD)
        title.setTextColor(Color.parseColor("#FFFFFF"))
        supportActionBar!!.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        supportActionBar!!.customView = title
        supportActionBar?.setBackgroundDrawable(colourBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
}
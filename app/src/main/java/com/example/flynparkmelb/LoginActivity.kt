package com.example.flynparkmelb

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.NestedScrollView
import com.example.flynparkmelb.helpers.InputValidation
import com.example.flynparkmelb.sql.DatabaseHelper
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

// Login activity class
class LoginActivity : AppCompatActivity(), View.OnClickListener {
    private val activity = this@LoginActivity
    private lateinit var nestedScrollView: NestedScrollView
    private lateinit var textInputLayoutName: TextInputLayout
    private lateinit var textInputLayoutPassword: TextInputLayout
    private lateinit var textInputEditTextName: TextInputEditText
    private lateinit var textInputEditTextPassword: TextInputEditText
    private lateinit var appCompatButtonLogin: AppCompatButton
    private lateinit var appCompatButtonRegister: AppCompatButton
    private lateinit var textViewLinkForgotPassword: AppCompatTextView
    private lateinit var inputValidation: InputValidation
    private lateinit var databaseHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // hiding the action bar
        supportActionBar!!.hide()
        // initializing the views
        initViews()
        // initializing the listeners
        initListeners()
        // initializing the objects
        initObjects()
    }
    /**
     * This method is to initialize views
     */
    private fun initViews() {
        nestedScrollView = findViewById(R.id.nestedScrollView)
        textInputLayoutName = findViewById(R.id.textInputLayoutName)
        textInputLayoutPassword = findViewById(R.id.textInputLayoutPassword)
        textInputEditTextName = findViewById(R.id.textInputEditTextName)
        textInputEditTextPassword = findViewById(R.id.textInputEditTextPassword)
        appCompatButtonLogin = findViewById(R.id.appCompatButtonLogin)
        appCompatButtonRegister= findViewById(R.id.appCompatButtonRegister)
        textViewLinkForgotPassword= findViewById(R.id.textViewLinkForgotPassword)
    }
    /**
     * This method is to initialize listeners
     */
    private fun initListeners() {
        appCompatButtonLogin.setOnClickListener(this)
        appCompatButtonRegister.setOnClickListener(this)
        textViewLinkForgotPassword.setOnClickListener(this)
    }
    /**
     * This method is to initialize objects to be used
     */
    private fun initObjects() {
        databaseHelper = DatabaseHelper(activity)
        inputValidation = InputValidation(activity)
    }
    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    override fun onClick(v: View) {
        when (v.id) {
            R.id.appCompatButtonLogin -> verifyFromSQLite()
            R.id.appCompatButtonRegister -> {
                // Navigate to RegisterActivity
                val intentRegister = Intent(applicationContext, RegisterActivity::class.java)
                startActivity(intentRegister)
            }
            R.id.textViewLinkForgotPassword -> {
                // Navigate to RegisterActivity
                val intentForgotPassword = Intent(applicationContext, ForgotPasswordActivity::class.java)
                startActivity(intentForgotPassword)
            }
        }
    }
    /**
     * This method is to validate the input text fields and verify login credentials from SQLite
     */
    private fun verifyFromSQLite() {
        if (!inputValidation.isInputEditTextFilled(
                textInputEditTextName,
                textInputLayoutName, getString(R.string.error_message_credentials))) {
            return
        }
        if (!inputValidation.isInputEditTextFilled(
                textInputEditTextPassword,
                textInputLayoutPassword, getString(R.string.error_message_credentials))) {
            return
        }
        if (databaseHelper.checkUser(textInputEditTextName.text.toString().trim { it <= ' ' }, textInputEditTextPassword.text.toString().trim { it <= ' ' })) {
            val dashboardIntent = Intent(activity, MainDashboardActivity::class.java)
            dashboardIntent.putExtra("userName", textInputEditTextName.text.toString().trim { it <= ' ' })
            emptyInputEditText()
            startActivity(dashboardIntent)
        } else {
            // Snack Bar to show success message that record is wrong
            Snackbar.make(nestedScrollView, getString(R.string.error_message_credentials), Snackbar.LENGTH_LONG).show()
        }
    }
    /**
     * This method is to empty all input edit text
     */
    private fun emptyInputEditText() {
        textInputEditTextName.text = null
        textInputEditTextPassword.text = null
    }
}
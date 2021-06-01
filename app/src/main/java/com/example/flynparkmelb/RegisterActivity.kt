package com.example.flynparkmelb

import android.graphics.Color
import android.graphics.Color.parseColor
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.widget.NestedScrollView
import com.example.flynparkmelb.helpers.InputValidation
import com.example.flynparkmelb.model.User
import com.example.flynparkmelb.sql.DatabaseHelper
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

// Register activity class
class RegisterActivity : AppCompatActivity(), View.OnClickListener {
    private val activity = this@RegisterActivity
    private lateinit var nestedScrollView: NestedScrollView
    private lateinit var textInputLayoutName: TextInputLayout
    private lateinit var textInputLayoutFirstName: TextInputLayout
    private lateinit var textInputLayoutLastName: TextInputLayout
    private lateinit var textInputLayoutEmail: TextInputLayout
    private lateinit var textInputLayoutPassword: TextInputLayout
    private lateinit var textInputLayoutConfirmPassword: TextInputLayout
    private lateinit var textInputEditTextName: TextInputEditText
    private lateinit var textInputEditTextFirstName: TextInputEditText
    private lateinit var textInputEditTextLastName: TextInputEditText
    private lateinit var textInputEditTextEmail: TextInputEditText
    private lateinit var textInputEditTextPassword: TextInputEditText
    private lateinit var textInputEditTextConfirmPassword: TextInputEditText
    private lateinit var appCompatButtonRegister: AppCompatButton
    private lateinit var inputValidation: InputValidation
    private lateinit var databaseHelper: DatabaseHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        // setting up the action bar

        changeTitle(getString(R.string.text_registration),ColorDrawable(Color.parseColor("#4F1177")) )
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
        nestedScrollView = findViewById<NestedScrollView>(R.id.nestedScrollView)
        textInputLayoutName = findViewById<TextInputLayout>(R.id.textInputLayoutName)
        textInputLayoutFirstName = findViewById<TextInputLayout>(R.id.textInputLayoutFirstName)
        textInputLayoutLastName = findViewById<TextInputLayout>(R.id.textInputLayoutLastName)
        textInputLayoutEmail = findViewById<TextInputLayout>(R.id.textInputLayoutEmail)
        textInputLayoutPassword = findViewById<TextInputLayout>(R.id.textInputLayoutPassword)
        textInputLayoutConfirmPassword = findViewById<TextInputLayout>(R.id.textInputLayoutConfirmPassword)
        textInputEditTextName = findViewById<TextInputEditText>(R.id.textInputEditTextName)
        textInputEditTextFirstName = findViewById<TextInputEditText>(R.id.textInputEditTextFirstName)
        textInputEditTextLastName = findViewById<TextInputEditText>(R.id.textInputEditTextLastName)
        textInputEditTextEmail = findViewById<TextInputEditText>(R.id.textInputEditTextEmail)
        textInputEditTextPassword = findViewById<TextInputEditText>(R.id.textInputEditTextPassword)
        textInputEditTextConfirmPassword = findViewById<TextInputEditText>(R.id.textInputEditTextConfirmPassword)
        appCompatButtonRegister = findViewById<AppCompatButton>(R.id.appCompatButtonRegister)
    }
    /**
     * This method is to initialize listeners
     */
    private fun initListeners() {
        appCompatButtonRegister.setOnClickListener(this)
    }
    /**
     * This method is to initialize objects to be used
     */
    private fun initObjects() {
        inputValidation = InputValidation(activity)
        databaseHelper = DatabaseHelper(activity)
    }
    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    override fun onClick(v: View) {
        when (v.id) {
            R.id.appCompatButtonRegister -> postDataToSQLite()
        }
    }
    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private fun postDataToSQLite() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.error_message_name))) {
            return
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextFirstName, textInputLayoutFirstName, getString(R.string.error_message_firstname))) {
            return
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextLastName, textInputLayoutLastName, getString(R.string.error_message_lastname))) {
            return
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return
        }
        if (!inputValidation.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                textInputLayoutConfirmPassword, getString(R.string.error_password_match))) {
            return
        }
        if (!databaseHelper.checkUser(textInputEditTextEmail.text.toString().trim())) {
            val user = User(name = textInputEditTextName.text.toString().trim(),
                firstname = textInputEditTextFirstName.text.toString().trim(),
                lastname = textInputEditTextLastName.text.toString().trim(),
                email = textInputEditTextEmail.text.toString().trim(),
                password = textInputEditTextPassword.text.toString().trim())
            databaseHelper.addUser(user)
            // Snack Bar to show success message that record saved successfully
            Snackbar.make(nestedScrollView, getString(R.string.success_message), Snackbar.LENGTH_LONG).show()
            emptyInputEditText()
        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(nestedScrollView, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show()
        }
    }
    /**
     * This method is to empty all input edit text
     */
    private fun emptyInputEditText() {
        textInputEditTextName.text = null
        textInputEditTextFirstName.text = null
        textInputEditTextLastName.text = null
        textInputEditTextEmail.text = null
        textInputEditTextPassword.text = null
        textInputEditTextConfirmPassword.text = null
    }

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
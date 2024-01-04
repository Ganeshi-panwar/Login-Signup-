package com.example.loginsignupfrd.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.view.KeyEvent
import android.view.View
import android.widget.Toast
import com.example.loginsignupfrd.R
import com.example.loginsignupfrd.databinding.ActivityRegisterBinding


class RegisterActivity : AppCompatActivity(), View.OnClickListener, View.OnFocusChangeListener, View.OnKeyListener {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.edUserName.onFocusChangeListener = this
        binding.etUserEmail.onFocusChangeListener = this
        binding.etUserPassword.onFocusChangeListener = this
        binding.etUserConfimpassword.onFocusChangeListener = this

    }
    private fun validateFullName(): Boolean{
        val value = binding.edUserName.text.toString()
        val errorMessage = if (value.isEmpty()) "Full Name is required" else null
        if (errorMessage != null){
            binding.edUserName.error = errorMessage
        }else{
            binding.edUserName.error = null
        }
        return errorMessage == null
    }
    private  fun validateEmail(): Boolean{
        val value = binding.etUserEmail.text.toString()
        val errorMessage = when {
            value.isEmpty() -> "Email is required"
            !Patterns.EMAIL_ADDRESS.matcher(value).matches() -> "Email address is invalide"
            else -> null
        }
        if (errorMessage != null){
            binding.etUserEmail.error = errorMessage
        }else{
            binding.etUserEmail.error = null
        }
        return errorMessage == null
    }
    private  fun validatePassword(): Boolean{
        val value = binding.etUserPassword.text.toString()
        val errorMessage = when{
             value.isEmpty() -> "Password is required"
            value.length<6 -> "Password must be at least 6 characters logn"
            else -> null
        }
        if (errorMessage != null){
            binding.etUserPassword.error = errorMessage
        }else{
            binding.etUserPassword.error = null
        }
        return errorMessage == null
    }
    private  fun validateConfirmPassword(): Boolean{
        val value = binding.etUserConfimpassword.text.toString()
        val errorMessage = when{
            value.isEmpty() -> "Confirm password is required"
            value.length<6 -> "Confirm password must be at least 6 character long"
            else -> null
        }
        if (errorMessage != null){
            binding.etUserConfimpassword.error = errorMessage
        }else{
            binding.etUserConfimpassword.error =  null
        }
        return  errorMessage == null
    }
    private  fun validatePasswordAndConfirmPassword(): Boolean{
        val password = binding.etUserPassword.text.toString()
        val confirmPassword = binding.etUserConfimpassword.text.toString()
        val errorMessage = if(password != confirmPassword) "Confirm password doesn't match with password" else null
        if (errorMessage != null){
            binding.etUserConfimpassword.error = errorMessage
        }else{
            binding.etUserConfimpassword.error = null
        }
        return errorMessage == null
    }



    private fun validateAllFields() {
        val isValidFullName = validateFullName()
        val isValidEmail = validateEmail()
        val isValidPassword = validatePassword()
        val isValidConfirmPassword = validateConfirmPassword()
        val isPasswordMatch = validatePasswordAndConfirmPassword()

        if (isValidFullName && isValidEmail && isValidPassword && isValidConfirmPassword && isPasswordMatch) {
            // All fields are valid, proceed with registration or other actions
            Toast.makeText(this, "Registration Successful!", Toast.LENGTH_SHORT).show()
        } else {
            // Handle the case where some fields are not valid
            Toast.makeText(this, "Please fix the errors in the form", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onClick(view: View?) {
        TODO("Not yet implemented")
    }

    override fun onFocusChange(view: View?, hasFocus: Boolean) {



        if (view != null) {
            when (view.id) {
                R.id.ed_user_name -> if (!hasFocus) validateFullName()
                R.id.et_user_email -> if (!hasFocus) validateEmail()
                R.id.et_user_password -> if (!hasFocus) validatePassword()
                R.id.et_user_confimpassword -> if (!hasFocus) validateConfirmPassword()
            }
            if (view.id == R.id.et_user_password || view.id== R.id.et_user_confimpassword){
                validatePasswordAndConfirmPassword()
            }
        }
    }

    override fun onKey(view: View?, keyCode: Int, event: KeyEvent?): Boolean {
        return  false
    }


}
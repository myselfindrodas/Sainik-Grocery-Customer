package com.grocery.sainik_grocery.ui

import android.app.AlertDialog
import android.content.ContentValues
import android.content.Intent
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.databinding.ViewDataBinding
import com.grocery.sainik_grocery.R
import com.grocery.sainik_grocery.base.BaseActivity
import com.grocery.sainik_grocery.data.ApiClient
import com.grocery.sainik_grocery.data.ApiHelper
import com.grocery.sainik_grocery.data.model.login.LoginRequestModel
import com.grocery.sainik_grocery.data.modelfactory.CommonModelFactory
import com.grocery.sainik_grocery.databinding.ActivityLoginBinding
import com.grocery.sainik_grocery.utils.Shared_Preferences
import com.grocery.sainik_grocery.utils.Status
import com.grocery.sainik_grocery.utils.Utilities
import com.grocery.sainik_grocery.viewmodel.CommonViewModel


class LoginActivity : BaseActivity() {

    lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: CommonViewModel
    var isPasswordVisible = false


    override fun resourceLayout(): Int {
        return R.layout.activity_login
    }

    override fun initializeBinding(binding: ViewDataBinding) {
        this.binding = binding as ActivityLoginBinding

    }

    override fun setFunction() {

        with(binding){

            Shared_Preferences.setUserToken("")
            val vm: CommonViewModel by viewModels {
                CommonModelFactory(ApiHelper(ApiClient.apiService))
            }

            viewModel = vm


            btnLogin.setOnClickListener {

                if (binding.etUserName.text.toString().isEmpty()){
                    Toast.makeText(applicationContext, "Enter User Name", Toast.LENGTH_LONG).show()
                }else if (binding.etPassword.text.toString().isEmpty()){
                    Toast.makeText(applicationContext, "Enter Password", Toast.LENGTH_LONG).show()
                }else{

                    loginapi(binding.etUserName.text.toString(),
                        binding.etPassword.text.toString())
                }

//                startActivity(Intent(this@LoginActivity,LocationActivity::class.java))
            }
            tvForgetPass.setOnClickListener {
                startActivity(Intent(this@LoginActivity,ForgetPasswordActivity::class.java))
            }

            pwdHideBtn.setOnClickListener {
                if (!isPasswordVisible) {
                    etPassword.transformationMethod = null
                    pwdHideBtn.setImageResource(R.drawable.ic_visibilityon)
                    isPasswordVisible = true

                } else {
                    etPassword.transformationMethod =
                        PasswordTransformationMethod()
                    pwdHideBtn.setImageResource(R.drawable.ic_visibilityoff)
                    isPasswordVisible = false
                }
                etPassword.setSelection(etPassword.length())
            }
        }
    }


    private fun loginapi(username:String, password:String){

        if (Utilities.isNetworkAvailable(this)) {

            viewModel.login(LoginRequestModel(email = username, password = password)).observe(this) {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            hideProgressDialog()
                            if (resource.data?.status==true){
                                val builder = AlertDialog.Builder(this@LoginActivity)
                                builder.setMessage(resource.data.msg)
                                builder.setPositiveButton(
                                    "Ok"
                                ) { dialog, which ->

                                    Shared_Preferences.setUserToken(resource.data.data.token)
                                    Shared_Preferences.setLoginStatus(true)
                                    startActivity(Intent(this@LoginActivity,LocationActivity::class.java))
                                }
                                val alert = builder.create()
                                alert.setOnShowListener { arg0 ->
                                    alert.getButton(AlertDialog.BUTTON_POSITIVE)
                                        .setTextColor(resources.getColor(R.color.orange))
                                }
                                alert.show()
                            }else{

                                val builder = AlertDialog.Builder(this@LoginActivity)
                                builder.setMessage(it.data?.msg)
                                builder.setPositiveButton(
                                    "Ok"
                                ) { dialog, which ->

                                    dialog.cancel()

                                }
                                val alert = builder.create()
                                alert.setOnShowListener { arg0 ->
                                    alert.getButton(AlertDialog.BUTTON_POSITIVE)
                                        .setTextColor(resources.getColor(R.color.orange))
                                }
                                alert.show()

                            }


                        }
                        Status.ERROR -> {
                            hideProgressDialog()
                            Log.d(ContentValues.TAG, "print-->"+ resource.data?.status)
                            if (it.message!!.contains("401",true)) {
                                val builder = AlertDialog.Builder(this@LoginActivity)
                                builder.setMessage("Invalid Employee Id / Password")
                                builder.setPositiveButton(
                                    "Ok"
                                ) { dialog, which ->

                                    dialog.cancel()

                                }
                                val alert = builder.create()
                                alert.show()
                            }else
                                Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()

                        }

                        Status.LOADING -> {
                            showProgressDialog()
                        }

                    }

                }
            }



        }else{

            Toast.makeText(this, "Ooops! Internet Connection Error", Toast.LENGTH_SHORT).show()

        }

    }



    fun showProgressDialog() {
        binding.rlLoading.visibility = View.VISIBLE
    }

    fun hideProgressDialog() {
        binding.rlLoading.visibility = View.GONE
    }

}
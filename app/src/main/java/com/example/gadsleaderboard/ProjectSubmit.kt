package com.example.gadsleaderboard

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isNotEmpty
import com.example.gadsleaderboard.api.ServerApi
import com.google.gson.Gson
import kotlinx.android.synthetic.main.project_submit_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProjectSubmit : AppCompatActivity(), View.OnClickListener {

    private lateinit var imageView : ImageView
    private lateinit var submitProjectBtn : Button

    private val submitBaseUrl = "https://docs.google.com/forms/d/e/"

    private var emailTxt: String = ""
    private var firstName: String = ""
    private var lastName: String = ""
    private var projectLink: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.project_submit_layout)

        imageView = findViewById(R.id.back_arrow)
        imageView.setOnClickListener(this)

        submitProjectBtn = findViewById(R.id.submit_project_btn)
        submitProjectBtn.setOnClickListener(this)

//        showSuccessDialog()
//        showFailureDialog()
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.back_arrow -> {
                finish()
            }
            R.id.submit_project_btn -> {

                emailTxt = fName?.text.toString()
                firstName = lName?.text.toString()
                lastName = eMail?.text.toString()
                projectLink = pLink?.text.toString()

                if (!emailTxt.isBlank() && !firstName.isBlank() && !lastName.isBlank() && !projectLink.isBlank()) {
                    showConfirmDialog()
                }
            }
        }
    }

     fun showConfirmDialog() {
        val dialog = Dialog(this, R.style.CustomDialogTheme)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.confirm_pop_up)
//         dialog.window?.setDimAmount(0.65f)
        val cancelBtn = dialog.findViewById(R.id.cancel_btn) as ImageView
        val yesBtn = dialog.findViewById(R.id.yes_btn) as Button
        yesBtn.setOnClickListener {
            Log.i("EditText ", "Values: $emailTxt $firstName $lastName $projectLink")
            dialog.dismiss()
        }
        cancelBtn.setOnClickListener { dialog.dismiss() }
        dialog.show()
         Log.i("ProjectSumit", "showDialog")
    }

    fun showSuccessDialog() {
        val dialog = Dialog(this, R.style.CustomDialogTheme)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.success_pop_up)
//         dialog.window?.setDimAmount(0.65f)

        dialog.show()
        Log.i("ProjectSumit", "showDialog")
    }

    fun showFailureDialog() {
        val dialog = Dialog(this, R.style.CustomDialogTheme)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.fail_pop_up)
//         dialog.window?.setDimAmount(0.65f)

        dialog.show()
        Log.i("ProjectSumit", "showDialog")
    }

    private fun update(mail:String, firstName:String, lastName:String, projectLink:String){

        val retrofit = Retrofit.Builder().baseUrl(submitBaseUrl).addConverterFactory(
            GsonConverterFactory.create()).build()

        val api = retrofit.create(ServerApi::class.java)

        val call = api.submitProject(mail, firstName, lastName, projectLink)

        val gson = Gson()

        call.enqueue(object : Callback<Void> {

            override fun onResponse(
                call: Call<Void>,
                response: Response<Void>
            ) {
                Log.i("ResponseString",gson.toJson(response.body()))

                if(response.isSuccessful){

//                    response.body()?.matunda
                }else{

                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.i("ResponseFailure1",t.message!!)
            }

        })

    }
}
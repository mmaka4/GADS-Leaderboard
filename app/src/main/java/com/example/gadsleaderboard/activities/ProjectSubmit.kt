package com.example.gadsleaderboard.activities

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.gadsleaderboard.R
import com.example.gadsleaderboard.api.ServerApi
import kotlinx.android.synthetic.main.project_submit_layout.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProjectSubmit : AppCompatActivity(), View.OnClickListener {

    private lateinit var imageView: ImageView
    private lateinit var submitProjectBtn: Button

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

    }

    override fun onClick(view: View?) {
        when (view?.id) {
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

    private fun showConfirmDialog() {
        val dialog = Dialog(
            this,
            R.style.CustomDialogTheme
        )
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.confirm_pop_up)
//         dialog.window?.setDimAmount(0.65f)
        val cancelBtn = dialog.findViewById(R.id.cancel_btn) as ImageView
        val yesBtn = dialog.findViewById(R.id.yes_btn) as Button
        yesBtn.setOnClickListener {
            Log.i("EditText ", "Values: $emailTxt $firstName $lastName $projectLink")
            update(emailTxt, firstName, lastName, projectLink)
            dialog.dismiss()
        }
        cancelBtn.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }

    private fun showSuccessDialog() {
        val dialog = Dialog(
            this,
            R.style.CustomDialogTheme
        )
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.success_pop_up)
//         dialog.window?.setDimAmount(0.65f)

        val cancelLayout = dialog.findViewById(R.id.successLayout) as ConstraintLayout
        cancelLayout.setOnClickListener { dialog.dismiss() }

        dialog.show()
    }

    private fun showFailureDialog() {
        val dialog = Dialog(
            this,
            R.style.CustomDialogTheme
        )
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setCanceledOnTouchOutside(true)
        dialog.setContentView(R.layout.fail_pop_up)
        //dialog.window?.setDimAmount(0.65f)

        val cancelLayout = dialog.findViewById(R.id.failLayout) as ConstraintLayout
        cancelLayout.setOnClickListener { dialog.dismiss() }
        dialog.show()
    }

    private fun update(mail: String, firstName: String, lastName: String, projectLink: String) {

        val retrofit = Retrofit.Builder().baseUrl(submitBaseUrl).addConverterFactory(
            GsonConverterFactory.create()
        ).build()

        val api = retrofit.create(ServerApi::class.java)

        val call = api.submitProject(mail, firstName, lastName, projectLink)

        call.enqueue(object : Callback<Void> {

            override fun onResponse(
                call: Call<Void>,
                response: Response<Void>
            ) {
                Log.i("ResponseString: ", "Success")
                //showSuccessDialog()

                if (response.code() == 200) {
                    showSuccessDialog()

                    fName?.setText("")
                    lName?.setText("")
                    eMail?.setText("")
                    pLink?.setText("")

                } else {
                    Log.i("ResponseCode: ", "!= 200")
                }
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.i("ResponseFailure: Submit", t.message!!)
                showFailureDialog()
            }
        })

    }
}
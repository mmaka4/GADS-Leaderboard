package com.example.gadsleaderboard

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class ProjectSubmit : AppCompatActivity(), View.OnClickListener {

    private lateinit var imageView : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.project_submit_layout)

        imageView = findViewById(R.id.back_arrow)
        imageView.setOnClickListener(this)

        //dialog()
        showDialog()
    }

    override fun onClick(view: View?) {
        when(view?.id) {
            R.id.back_arrow -> {
                finish()
            }
        }
    }

     fun showDialog() {
        val dialog = Dialog(this, R.style.CustomDialogTheme)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.confirm_pop_up)
//         dialog.window?.setDimAmount(0.65f)
        val cancelBtn = dialog.findViewById(R.id.cancel_btn) as ImageView
        val yesBtn = dialog.findViewById(R.id.yes_btn) as Button
        yesBtn.setOnClickListener {
            dialog.dismiss()
        }
        cancelBtn.setOnClickListener { dialog.dismiss() }
        dialog.show()
         Log.i("ProjectSumit", "showDialog")
    }

    @SuppressLint("InflateParams")
    fun dialog() {
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.confirm_pop_up, null)
        //AlertDialogBuilder
        val mBuilder = AlertDialog.Builder(this)
            .setView(mDialogView)
        mBuilder.show()
        //show dialog
//        val  mAlertDialog = mBuilder.show()
        //login button click of custom layout
//        mDialogView.dialogLoginBtn.setOnClickListener {
//            //dismiss dialog
//            mAlertDialog.dismiss()
//            //get text from EditTexts of custom layout
//            val name = mDialogView.dialogNameEt.text.toString()
//            val email = mDialogView.dialogEmailEt.text.toString()
//            val password = mDialogView.dialogPasswEt.text.toString()
//            //set the input text in TextView
//            mainInfoTv.setText("Name:"+ name +"\nEmail: "+ email +"\nPassword: "+ password)
//        }
//        //cancel button click of custom layout
//        mDialogView.dialogCancelBtn.setOnClickListener {
//            //dismiss dialog
//            mAlertDialog.dismiss()
//        }
    }
}
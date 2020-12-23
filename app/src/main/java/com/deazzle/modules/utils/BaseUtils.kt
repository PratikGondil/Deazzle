package com.deazzle.modules.utils

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import com.deazzle.roomdatabase.database.AppDatabase

object BaseUtils {

    lateinit var progressDialog: ProgressDialog
    fun showProgress(activity :Activity) {
        progressDialog = ProgressDialog(activity)
        progressDialog.setMessage("Application is loading, please wait")
        progressDialog.show()
    }

    fun stopProgress() {
     progressDialog.hide()
    }

    fun getDatabase(context: Context?): AppDatabase? {
        return AppDatabase.getAppDatabase(context!!)
    }
}
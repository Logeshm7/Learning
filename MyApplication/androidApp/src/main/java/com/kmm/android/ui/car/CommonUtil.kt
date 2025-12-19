package com.kmm.android.ui.car

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import com.kmm.android.R

object CommonUtil {
    fun showCustomSuccessToast(message: String, context: Context) {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.toast_success, null)
        view.findViewById<TextView>(R.id.tvSuccess).text = message
        Toast(context).apply {
            duration = Toast.LENGTH_SHORT
            this.view = view
        }.show()
    }

    fun showCustomErrorToast(message: String, context: Context) {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.toast_error, null)
        view.findViewById<TextView>(R.id.tvError).text = message

        Toast(context).apply {
            duration = Toast.LENGTH_SHORT
            this.view = view
        }.show()
    }

}
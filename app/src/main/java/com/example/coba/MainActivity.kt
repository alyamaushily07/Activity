package com.example.coba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.AbsSavedState
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var edtWidth: EditText
    private lateinit var edtHeight: EditText
    private lateinit var edtLength: EditText
    private lateinit var btnCalsulate: Button
    private lateinit var tvResult: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtWidth = findViewById(R.id.edt_width)
        edtHeight = findViewById(R.id.edt_height)
        edtLength = findViewById(R.id.edt_leght)
        btnCalsulate = findViewById(R.id.btn_calculate)
        tvResult = findViewById(R.id.tv_result)

        btnCalsulate.setOnClickListener(this)

        if(savedInstanceState!=null){
            val result = savedInstanceState.getString(STATE_RESULT) as String
            tvResult.text = result
        }
    }

    override fun onClick(v: View?) {
        val inputLength = edtLength.text.toString()
        val inputWidth = edtWidth.text.toString()
        val inputHeight = edtHeight.text.toString()

        var isEmptyField: Boolean = false
        var isInvalidDouble: Boolean = false

        if (TextUtils.isEmpty(inputLength)) {
            isEmptyField = true
            edtLength.setError("Field Ini Tidak Boleh Kosong")
        } else if (TextUtils.isEmpty(inputWidth)) {
            isEmptyField = true
            edtWidth.setError("Field Ini Tidak Boleh Kosong")
        } else if (TextUtils.isEmpty(inputHeight)) {
            isEmptyField = true
            edtHeight.setError("Field Ini Tidak Boleh Kosong")
        }

        val lenght: Double = converttoDouble(inputLength)!!
        val width: Double = converttoDouble(inputWidth)!!
        val height: Double = converttoDouble(inputHeight)!!

        if (lenght == null) {
            isInvalidDouble = true
            edtLength.error = "nilai tidak valid"
        }
        if (width == null) {
            isInvalidDouble = true
            edtWidth.error = "nilai tidak valid"
        }

        if (height == null) {
            isInvalidDouble = true
            edtHeight.error = "nilai tidak valid"
        }

        if (!isEmptyField && !isInvalidDouble) {
            val volume = height!!.toDouble() * lenght!!.toDouble() * width!!.toDouble()
            tvResult.text = volume.toString()
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(STATE_RESULT, tvResult.text.toString())
    }

    private fun converttoDouble(str: String): Double? {


        return try {
            str.toDouble()
        } catch (e: NumberFormatException) {
            null
        }
    }

    companion object {
        private const val STATE_RESULT = "state_result"
    }
}

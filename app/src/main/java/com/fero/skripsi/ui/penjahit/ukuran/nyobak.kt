package com.example.andoridcafe

import android.R
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity

//class OrderActivity : AppCompatActivity(), OnItemSelectedListener {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_order)
//
//
//        val spinner = findViewById<Spinner>(R.id.spinner)
//        if (spinner != null) {
//            spinner.onItemSelectedListener = this
//        }
//        val adapter = ArrayAdapter.createFromResource(
//            this,
//            R.array.labels_array,
//            R.layout.simple_spinner_item
//        )
//        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
//        if (spinner != null) {
//            spinner.adapter = adapter
//        }
//    }
//
//    fun displayToast(message: String?) {
//        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
//    }
//
//    override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
//        val spinnerLabel = adapterView.getItemAtPosition(i).toString()
//        displayToast(spinnerLabel)
//    }
//
//
//}
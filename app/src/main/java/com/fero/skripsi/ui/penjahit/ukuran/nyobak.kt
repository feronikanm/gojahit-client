package com.example.andoridcafe

import android.R
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment

//class OrderActivity : AppCompatActivity(), OnItemSelectedListener {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_order)
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
//    override fun onNothingSelected(adapterView: AdapterView<*>?) {}
//    fun showDatePicker(view: View?) {
//        val newFragment: DialogFragment = DatePickerFragment()
//        newFragment.show(supportFragmentManager, "datePicker")
//    }
//
//    fun processDatePickerResult(year: Int, month: Int, day: Int) {
//        val day_string = Integer.toString(day)
//        val month_string = Integer.toString(month + 1)
//        val year_string = Integer.toString(year)
//        val dateMessage = "$day_string/$month_string/$year_string"
//        Toast.makeText(this, "Date : $dateMessage", Toast.LENGTH_LONG).show()
//    }
//
//    fun showTimePicker(view: View?) {
//        val newFragmant: DialogFragment = TImePickerFragment()
//        newFragmant.show(supportFragmentManager, "timePicker")
//    }
//
//    fun processTimePickerResult(hour: Int, minute: Int) {
//        val hour_string = Integer.toString(hour)
//        val minute_string = Integer.toString(minute)
//        val timeMessage = "$hour_string:$minute_string"
//        Toast.makeText(this, "Time $timeMessage", Toast.LENGTH_SHORT).show()
//    }
//}
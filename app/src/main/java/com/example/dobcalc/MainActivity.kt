package com.example.dobcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate : TextView? = null
    private var tvAgeInMin : TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMin = findViewById(R.id.tvAgeInMin)
        val btnDatePicker: Button = findViewById(R.id.btnDatePicker)
        btnDatePicker.setOnClickListener {
            clickDatePicker()

        }
    }

    private fun clickDatePicker(){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
        DatePickerDialog.OnDateSetListener{ _, year, month, dayOfMonth ->

            val selectedDate = "$dayOfMonth/${month+1}/$year"

            tvSelectedDate?.text = selectedDate

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val theDate = sdf.parse(selectedDate)
            theDate?.let{
                val selectedDateInHours = theDate.time / 3600000

                val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                currentDate?.let{
                    val currentDateInHours = currentDate.time / 3600000

                    val differenceInHours = currentDateInHours - selectedDateInHours

                    tvAgeInMin?.text = differenceInHours.toString()
            }}
        },
        year,
        month,
        day)
        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()
    }
}
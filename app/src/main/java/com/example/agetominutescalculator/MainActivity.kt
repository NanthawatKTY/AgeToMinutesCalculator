package com.example.agetominutescalculator

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private val minutes: String = "Minutes"
    private val minute: String = "Minute"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDatePicker.setOnClickListener { view ->
            clickedDatePicker(view)

        }
    }

    private fun clickedDatePicker(viewDate: View){
        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{
                _, selectedYear, selectedMonth, selectedDay ->
            //Toast.makeText(this, "Date picker is worked $selectedYear / $selectedMonth / $selectedDay" ,Toast.LENGTH_LONG).show()

            val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"

            tvSelectedDate.text = selectedDate

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            //Convert string date to Date format
            val theDate = sdf.parse(selectedDate)

            //Minutes to milliseconds
            val selectedDateMinutes = theDate!!.time / 60000

            val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
            val currentDateToMinutes = currentDate!!.time / 60000

            var differenceInMin = currentDateToMinutes - selectedDateMinutes

            if (differenceInMin.toInt() == 0){
                tvAgeInMin.text = "$differenceInMin $minute"
            }else {
                tvAgeInMin.text = "$differenceInMin $minutes"
            }


        },year, month, day)

        dpd.datePicker.maxDate = Date().time //Maximum date are current date (86400000 ms. = 1 day)
        dpd.show()
    }
}
package com.datphoenixstudios.ageindays

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDay: TextView? = null
    private var tvAgeInDays: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.buttonCalc)

        tvSelectedDay = findViewById(R.id.tvDate)
        tvAgeInDays = findViewById(R.id.tvDays)


        button.setOnClickListener {
            clickDatePicker()
        }

    }

    //function to enable the datepicker and get the date
    private fun clickDatePicker() {

        //creating instance of Calendar
        val myCalender = Calendar.getInstance()
        //getting the year, month and day
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

        //creating the datepicker dialog
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                Toast.makeText(
                    this,
                    "Year was $year months was ${month + 1} and day was $dayOfMonth",
                    Toast.LENGTH_LONG
                ).show()

                //displays the picked date on our screen
                val selectedDay = "$dayOfMonth/${month + 1}/$year"
                tvSelectedDay?.text = selectedDay

                //formats our date and our calendar
                val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.GERMAN)

                //uses the format on our picked date
                val theDate = simpleDateFormat.parse(selectedDay)

                val selectedDateInMinutes = (theDate!!.time / 60000) / 1440 // to get AGE IN DAYS
//                val selectedDateInMinutes = theDate!!.time / 60000 // to get the minutes from 1st Jan. 1970
                // Toast is added to view resulting number
                Toast.makeText(this, "SELECTED date in minutes $selectedDateInMinutes", Toast.LENGTH_LONG).show()

                val currentDate = simpleDateFormat.parse(simpleDateFormat.format(System.currentTimeMillis()))
//                val currentDateInMinutes = currentDate!!.time / 60000
                val currentDateInDays = (currentDate!!.time / 60000) / 1440 // to get AGE IN DAYS
                // Toast is added to view resulting number
                Toast.makeText(this, "Current date in minutes $currentDateInDays", Toast.LENGTH_LONG).show()

                val differenceInMinutes = currentDateInDays - selectedDateInMinutes // the age in minutes

                tvAgeInDays?.text = differenceInMinutes.toString()
            },
            year,
            month,
            day
        )
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis() - 86400000
        datePickerDialog.show()


    }


}
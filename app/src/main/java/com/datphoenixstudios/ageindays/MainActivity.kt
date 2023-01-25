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
    //TEST COMMENT


    //creates variables in which we later save our tv's from the layout
    private var tvSelectedDay: TextView? = null
    private var tvAgeInDays: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.buttonCalc)

        //saving the layout tv's in our variables
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
                //checking the selected date with a print statement
                println("Year was $year months was ${month + 1} and day was $dayOfMonth")

                //displays the picked date on our screen
                val selectedDay = "$dayOfMonth/${month + 1}/$year"
                tvSelectedDay?.text = selectedDay

                //saves the date formatting in a variable
                val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.GERMAN)

                //uses the format on our picked date
                val theDate = simpleDateFormat.parse(selectedDay)

                //calculating the day of our picked date
                val selectedDateInDays = (theDate!!.time / 60000) / 1440

                //getting the current date and converting it to days
                val currentDate = simpleDateFormat.parse(simpleDateFormat.format(System.currentTimeMillis()))
                val currentDateInDays = (currentDate!!.time / 60000) / 1440

                //calculating the difference between the current day and the picked day and displaying it
                val differenceInDays = currentDateInDays - selectedDateInDays
                tvAgeInDays?.text = differenceInDays.toString()
            },
            year,
            month,
            day
        )
        //enabling to only access days in the past (starting from yesterday)
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis() - 86400000
        datePickerDialog.show()
    }
}
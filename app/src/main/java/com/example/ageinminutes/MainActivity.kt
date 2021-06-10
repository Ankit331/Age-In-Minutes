package com.example.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonDatePicker : Button =findViewById(R.id.btnDatePicker)
        buttonDatePicker.setOnClickListener(View.OnClickListener { view ->

            onClickDatePicker(view)
        })


    }

    fun onClickDatePicker(view: View) {

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val dayOfMonth = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener{ view, selectedYear, selectedMonth, selectedDayOfMonth ->

                val tvSelectedDate: TextView =findViewById(R.id.tvSelectedDate)
                val tvCalculatedMinutes : TextView =findViewById(R.id.tvSelectedDateInMinutes)

                val selectedDate = "${selectedMonth + 1}/$selectedDayOfMonth/$selectedYear"  // Month counting starts with 0 so month+1
                tvSelectedDate.setText(selectedDate)      // selected date by user

                // parse into simple date format for easier date computation
                val sdf = SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH)
                val tempDate = sdf.parse(selectedDate)

                val selectedDateInMinutes = tempDate!!.time / 60000    // returns milliseconds  calculating time in minutes so divide by 60000 , get second so divide by 1000

                val tempCurrentDate = sdf.parse(sdf.format(System.currentTimeMillis()))  //current Date
                val currentDateInMinutes = tempCurrentDate!!.time / 60000                // current Minutes

                val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes // calculating diffrence till date

                tvCalculatedMinutes.setText(differenceInMinutes.toString())

            },
            year,
            month,
            dayOfMonth
        )

        dpd.datePicker.maxDate = Date().time - 86400000
        dpd.show()
    }

}
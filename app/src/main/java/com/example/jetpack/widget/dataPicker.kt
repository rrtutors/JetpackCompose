package com.example.jetpack.widget

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun DatePickerDemo(context: Context) {
    val mYear: Int
    val mMonth: Int
    val mDay: Int
    val now = Calendar.getInstance()
    mYear = now.get(Calendar.YEAR)
    mMonth = now.get(Calendar.MONTH)
    mDay = now.get(Calendar.DAY_OF_MONTH)
    now.time = Date()
    val date = remember { mutableStateOf("") }
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            val cal = Calendar.getInstance()
            cal.set(year, month, dayOfMonth)
            date.value = getFormattedDate(cal.time, "dd MMM,yyy")
        }, mYear, mMonth, mDay
    )

    val day1= Calendar.getInstance()
    day1.set(Calendar.DAY_OF_MONTH, 1)
    datePickerDialog.datePicker.minDate = day1.timeInMillis
    datePickerDialog.datePicker.maxDate = Calendar.getInstance().timeInMillis
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            datePickerDialog.show()
        }) {
            Text(text = "Click To Open Date Picker")
        }
        Spacer(modifier = Modifier.size(16.dp))
        Text(text = "Selected date: ${date.value}")
    }
}

fun getFormattedDate(date: Date?, format: String): String {
    try {
        if (date != null) {
            val formatter = SimpleDateFormat(format, Locale.getDefault())
            return formatter.format(date)
        }
    } catch (e: Exception) {

    }
    return ""
}

//https://stackoverflow.com/questions/60417233/jetpack-compose-date-time-picker
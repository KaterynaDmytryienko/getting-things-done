package com.kate.pda_gtd.components


import android.content.Context
import android.widget.DatePicker
import java.util.Calendar

class DatePickerDialog {

    fun showDatePicker(
        context: Context,
        onDateSelected: (String) -> Unit,
        onDismiss: () -> Unit
    ) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = android.app.DatePickerDialog(
            context,
            { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                val selectedDate = "$dayOfMonth/${month + 1}/$year"
                onDateSelected(selectedDate)
            },
            year,
            month,
            day
        )

        datePickerDialog.show()
        datePickerDialog.setOnCancelListener {
            onDismiss()
        }
    }
}
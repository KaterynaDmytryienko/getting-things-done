package com.kate.pda_gtd.components
import android.content.Context
import android.widget.TimePicker
import java.util.Calendar

class TimePickerDialog {
    fun showTimePicker(
        context: Context,
        onTimeSelected: (String) -> Unit,
        onDismiss: () -> Unit
    ) {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = android.app.TimePickerDialog(
            context,
            { _: TimePicker, selectedHour: Int, selectedMinute: Int ->
                val selectedTime = "${selectedHour.twoDigits()}:${selectedMinute.twoDigits()}"
                onTimeSelected(selectedTime)
            },
            hour,
            minute,
            true
        )

        timePickerDialog.show()
        timePickerDialog.setOnCancelListener {
            onDismiss()
        }
    }

    private fun Int.twoDigits() = this.toString().padStart(2, '0')
}
package com.kate.pda_gtd.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerColors
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

class CalendarPage {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Calendar(){
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            val datePickerState = rememberDatePickerState()
            DatePicker(state = datePickerState, modifier = Modifier.padding(16.dp))
    }
}

}


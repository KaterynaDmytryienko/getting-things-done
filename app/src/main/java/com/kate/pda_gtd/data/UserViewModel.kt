package com.kate.pda_gtd.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class UserViewModel() : ViewModel() {
    private val _state = MutableStateFlow(UserState())
    private val _username = MutableStateFlow("")
    val state = combine(_state, _username) { state, username ->
        state.copy(
            username = username
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = UserState()
    )


    fun onEvent(event: UserEvent){
        when(event){
            is UserEvent.SetPhoto -> {
                _state.update {
                    it.copy(
                        photo = event.photo
                    )
                }
            }

            is UserEvent.SetUsername -> TODO()
        }
    }
}
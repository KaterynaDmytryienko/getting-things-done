package com.kate.pda_gtd.data

sealed interface UserEvent {
    data class SetPhoto(val photo: String): UserEvent
    data class SetUsername(val username:String):UserEvent
}
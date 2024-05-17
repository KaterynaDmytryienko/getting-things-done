package com.kate.pda_gtd.data

interface CategoryEvent {
    data class SaveCategory(
        val name:String
    ): CategoryEvent

    data class SetCategoryName(val name: String): CategoryEvent
    data class DeleteCategoryById(val categoryId: Long): CategoryEvent

}
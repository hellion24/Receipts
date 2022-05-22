package ru.mirea.sipirecipes.data.model

import java.math.BigDecimal
import java.util.*

data class RecipeSummary(
    val uuid: UUID,
    val name: String,
    val category: String,
    val price: BigDecimal,
    val durationHours: Int,
    val durationMinutes: Int,
    val rating: Double
) {
    companion object {
        fun getFormattedTime(recipe: RecipeSummary): String {
            return if (recipe.durationHours == 0) {
                "${recipe.durationMinutes} мин."
            } else {
                "${recipe.durationHours} час. ${recipe.durationMinutes} мин."
            }
        }
    }
}

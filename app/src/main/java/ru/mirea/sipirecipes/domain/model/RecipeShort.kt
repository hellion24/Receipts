package ru.mirea.sipirecipes.domain.model

import java.math.BigDecimal
import java.util.*

data class RecipeShort(
    val uuid: UUID,
    val name: String,
    val category: String,
    val price: BigDecimal,
    val durationHours: Int,
    val durationMinutes: Int,
    val rating: Double
)

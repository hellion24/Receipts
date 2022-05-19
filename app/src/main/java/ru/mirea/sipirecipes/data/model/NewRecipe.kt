package ru.mirea.sipirecipes.data.model

import java.math.BigDecimal

data class NewRecipe(
    var category: String,
    var recipeImageUuid: String?,
    var name: String,
    var complexity: Int,
    var description: String,
    var instructions: String,
    var price: BigDecimal,
    var durationHours: Int,
    var durationMinutes: Int,
    var portion: String,
    var calories: BigDecimal,
    var fats: BigDecimal,
    var proteins: BigDecimal,
    var carbohydrates: BigDecimal,
    var status: String
)

package ru.mirea.sipirecipes.data.model

import java.math.BigDecimal

data class RecipeInfo(
    var uuid: String,
    var category: String,
    var recipeImageUuid: String?,
    var name: String,
    var complexity: Int = 0,
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
    var status: RecipeStatus,
    var rating: Double
) {
    companion object {
        fun getNutritionDetails(recipe: RecipeInfo?): String {
            return if (recipe != null) {
                return "Калории: ${recipe.calories}\n" +
                        "Белки: ${recipe.proteins}\n" +
                        "Жиры: ${recipe.fats}\n" +
                        "Углеводы: ${recipe.carbohydrates}"
            } else {
                ""
            }
        }

        fun getFormattedTime(recipe: RecipeInfo?): String {
            return if (recipe != null) {
                return if (recipe.durationHours == 0) {
                    "${recipe.durationMinutes} мин."
                } else {
                    "${recipe.durationHours} час. ${recipe.durationMinutes} мин."
                }
            } else {
                ""
            }
        }
    }
}
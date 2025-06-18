// app/src/main/java/com/asparrin/carlos/laboratoriocalificado03/data/model/TeachersResponse.kt
package com.asparrin.carlos.laboratoriocalificado03.data.model

import com.google.gson.annotations.SerializedName

// TeachersResponse.kt
data class TeachersResponse(
    @SerializedName("teachers")
    val teachers: List<Teacher>
)

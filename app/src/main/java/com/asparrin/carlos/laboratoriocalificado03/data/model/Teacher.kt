package com.asparrin.carlos.laboratoriocalificado03.data.model

import com.google.gson.annotations.SerializedName

// Teacher.kt
data class Teacher(
    @SerializedName("name")       val firstName:   String,
    @SerializedName("last_name")  val lastName:    String,
    @SerializedName("image_url")  val photoUrl:    String
    // …puedes añadir phoneNumber y email si los necesitas
)

package com.example.modul3compose.model

import androidx.annotation.DrawableRes

data class Friend(
    val id: Int,
    val name: String,
    val date: String,
    val feature: String,
    @DrawableRes val photoResId: Int,
    val instagramUrl: String
)
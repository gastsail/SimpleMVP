package com.gaston.macbook.kotlinsimplemvp.presentation.show_apod.model

import com.google.gson.annotations.SerializedName

data class Apod(val explanation: String = "",
                val hdurl: String = "",
                @SerializedName("url") val lowresurl: String = "",
                val title: String = "",
                val date: String = "",
                val copyright: String = "")
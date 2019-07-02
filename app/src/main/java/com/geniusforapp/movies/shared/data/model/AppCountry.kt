package com.geniusforapp.movies.shared.data.model


import com.google.gson.annotations.SerializedName

data class AppCountry(
        @SerializedName("english_name")
        val englishName: String,
        @SerializedName("iso_3166_1")
        val iso31661: String
)
package com.geniusforapp.movies.shared.data.model


import com.google.gson.annotations.SerializedName

data class AppConfig(
        @SerializedName("change_keys")
        val changeKeys: List<String>,
        @SerializedName("images")
        val images: Images
) {
    data class Images(
            @SerializedName("backdrop_sizes")
            val backdropSizes: Set<String>,
            @SerializedName("base_url")
            val baseUrl: String,
            @SerializedName("logo_sizes")
            val logoSizes: Set<String>,
            @SerializedName("poster_sizes")
            val posterSizes: Set<String>,
            @SerializedName("profile_sizes")
            val profileSizes: Set<String>,
            @SerializedName("secure_base_url")
            val secureBaseUrl: String,
            @SerializedName("still_sizes")
            val stillSizes: Set<String>
    )
}
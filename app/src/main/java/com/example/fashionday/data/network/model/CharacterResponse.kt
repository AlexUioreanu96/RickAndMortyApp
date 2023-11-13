package com.example.fashionday.data.network.model

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    @SerialName("results")
    val results: List<ResultsItem?>? = null,

    @SerialName("info")
    val info: Info? = null
)

@Serializable
data class ResultsItem(

    @SerialName("image")
    val image: String? = null,

    @SerialName("gender")
    val gender: String? = null,

    @SerialName("species")
    val species: String? = null,

    @SerialName("created")
    val created: String? = null,

    @SerialName("origin")
    val origin: Origin? = null,

    @SerialName("name")
    val name: String? = null,

    @SerialName("location")
    @Contextual
    val location: Location? = null,

    @SerialName("episode")
    val episode: List<String?>? = null,

    @SerialName("id")
    val id: Int? = null,

    @SerialName("type")
    val type: String? = null,

    @SerialName("url")
    val url: String? = null,

    @SerialName("status")
    val status: String? = null
)

@Serializable
data class Info(

    @SerialName("next")
    val next: String? = null,

    @SerialName("pages")
    val pages: Int? = null,

    @SerialName("prev")
    val prev: String? = null,

    @SerialName("count")
    val count: Int? = null
)

@Serializable
data class Origin(

    @SerialName("name")
    val name: String? = null,

    @SerialName("url")
    val url: String? = null
)

@Serializable
data class Location(

    @SerialName("name")
    val name: String? = null,

    @SerialName("url")
    val url: String? = null
)

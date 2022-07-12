package studio.sanguine.codingtest.models

import java.io.Serializable

data class Movie(
    var title: String?,
    val content: String?,
    val year: Int?,
    val categories: List<String>?,
    val imagethumburl: String?,
    val rating: Int?,
    val characterName:String?,
    val actorName: String?
) : Serializable
package com.jesse.c25a.pag3.data.model

import com.jesse.c25a.pag3.domain.model.CharacterItem

data class Results(
    val created: String,
    val episode: List<String>,
    val gender: String,
    val id: Int,
    val image: String,
    val location: Location,
    val name: String,
    val origin: Origin,
    val species: String,
    val status: String,
    val type: String,
    val url: String
)

fun Results.toDomain() = CharacterItem(
    id = id,
    name = name,
    status = status,
    species = species,
    gender = gender,
    image = image
)
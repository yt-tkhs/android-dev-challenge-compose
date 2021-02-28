package com.example.androiddevchallenge.data.dto

import com.example.androiddevchallenge.domain.models.Puppy
import com.example.androiddevchallenge.domain.valueobject.Id
import kotlinx.serialization.Serializable

@Serializable
data class PuppyResponse(
  val id: Int,
  val name: String,
  val life_span: String,
  val temperament: String? = null,
  val origin: String? = null,
  val image: Image,
) {

  @Serializable
  data class Image(
    val id: String,
    val width: Int,
    val height: Int,
    val url: String,
  )
}

fun PuppyResponse.toModel() =
  Puppy(
    id = Id.Puppy(id),
    name = name,
    temperaments = temperament?.split(",")?.map { it.trim() } ?: emptyList(),
    lifeSpan = life_span,
    origin = origin,
    imageUrl = image.url,
  )

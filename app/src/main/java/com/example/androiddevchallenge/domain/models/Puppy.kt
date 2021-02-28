package com.example.androiddevchallenge.domain.models

import com.example.androiddevchallenge.domain.valueobject.Id

data class Puppy(
  val id: Id.Puppy,
  val name: String,
  val temperaments: List<String>,
  val lifeSpan: String,
  val origin: String?,
  val imageUrl: String,
)

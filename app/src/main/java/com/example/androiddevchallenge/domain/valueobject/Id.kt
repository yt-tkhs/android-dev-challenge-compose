package com.example.androiddevchallenge.domain.valueobject

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

sealed class Id<T : Any>(open val value: T) : Parcelable {

  @Parcelize
  data class Puppy(override val value: Int) : Id<Int>(value)
}

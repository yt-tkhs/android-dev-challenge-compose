/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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

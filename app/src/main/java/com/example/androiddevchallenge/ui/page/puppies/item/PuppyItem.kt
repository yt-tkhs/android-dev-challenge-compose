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
package com.example.androiddevchallenge.ui.page.puppies.item

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bumptech.glide.request.RequestOptions
import com.example.androiddevchallenge.domain.models.Puppy
import com.example.androiddevchallenge.domain.valueobject.Id
import com.example.androiddevchallenge.ui.theme.MyTheme
import dev.chrisbanes.accompanist.glide.GlideImage

@Composable
fun PuppyItem(puppy: Puppy, onClick: () -> Unit) {
    Card(
        modifier = Modifier.clickable(onClick = onClick),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            GlideImage(
                modifier = Modifier.size(80.dp),
                data = puppy.imageUrl,
                contentDescription = "Puppy image",
                fadeIn = true,
                requestBuilder = {
                    apply(RequestOptions().centerCrop())
                }
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 12.dp, horizontal = 8.dp),
            ) {
                Text(text = puppy.name, style = MaterialTheme.typography.subtitle1)
            }
        }
    }
}

@Preview("PuppyItem", widthDp = 360, heightDp = 240)
@Composable
fun PuppyItemPreview() {
    MyTheme {
        PuppyItem(
            puppy = Puppy(
                id = Id.Puppy(1),
                name = "Affenpinscher",
                temperaments = listOf(
                    "Stubborn",
                    "Curious",
                    "Playful",
                    "Adventurous",
                    "Active",
                    "Fun-loving"
                ),
                lifeSpan = "10 - 12 years",
                origin = "Germany, France",
                imageUrl = "https://cdn2.thedogapi.com/images/BJa4kxc4X.jpg"
            ),
            onClick = {
                // no op
            }
        )
    }
}

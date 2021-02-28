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
package com.example.androiddevchallenge.ui.page.puppydetail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.bumptech.glide.request.RequestOptions
import com.example.androiddevchallenge.domain.valueobject.Id
import dev.chrisbanes.accompanist.glide.GlideImage

@Composable
fun PuppyDetailScreen(puppyId: Id.Puppy) {
    val viewModel = viewModel(PuppyDetailViewModel::class.java)
    val puppyState = viewModel.puppy.collectAsState(initial = null)

    LaunchedEffect(true) {
        viewModel.initialize(puppyId)
    }

    puppyState.value?.let { puppy ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {
            GlideImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp),
                data = puppy.imageUrl,
                contentDescription = "Puppy image",
                fadeIn = true,
                requestBuilder = {
                    apply(RequestOptions().centerCrop())
                }
            )
            Column(modifier = Modifier.wrapContentHeight()) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(fraction = 1f)
                        .padding(20.dp),
                    textAlign = TextAlign.Center,
                    text = puppy.name,
                    style = MaterialTheme.typography.h5
                )
            }
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 0.dp)
            ) {
                items(puppy.temperaments) { temperament ->
                    Box(Modifier.padding(horizontal = 4.dp, vertical = 0.dp)) {
                        Card(
                            modifier = Modifier
                                .wrapContentWidth()
                                .wrapContentHeight()
                        ) {
                            Text(
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                text = temperament
                            )
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(horizontal = 20.dp, vertical = 40.dp),
            ) {
                Text(text = "Interesting?")
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        /* TODO */
                    }
                ) {
                    Text(text = "Adopt now!")
                }
            }
        }
    }
}

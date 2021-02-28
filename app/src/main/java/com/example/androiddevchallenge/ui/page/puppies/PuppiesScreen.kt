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
package com.example.androiddevchallenge.ui.page.puppies

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import com.example.androiddevchallenge.ui.page.puppies.item.PuppyItem

@Composable
fun PuppiesScreen(navController: NavHostController) {
    val viewModel = viewModel(PuppiesViewModel::class.java)
    val puppies = viewModel.puppies.collectAsState(initial = emptyList())
    Column {
        LazyColumn(
            modifier = Modifier.fillMaxHeight(),
            contentPadding = PaddingValues(horizontal = 0.dp, vertical = 8.dp),
        ) {
            item {
                Text(
                    modifier = Modifier.padding(horizontal = 20.dp, vertical = 20.dp),
                    text = "Puppies",
                    style = MaterialTheme.typography.h4,
                )
            }
            items(puppies.value) { puppy ->
                Box(modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)) {
                    PuppyItem(
                        puppy = puppy,
                        onClick = {
                            Log.d("Puppies", "onClick")
                            navController.navigate("puppies/${puppy.id.value}")
                        }
                    )
                }
            }
        }
    }
}

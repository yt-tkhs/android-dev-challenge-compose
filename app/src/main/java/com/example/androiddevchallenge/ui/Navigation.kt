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
package com.example.androiddevchallenge.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import com.example.androiddevchallenge.domain.valueobject.Id
import com.example.androiddevchallenge.ui.page.puppies.PuppiesScreen
import com.example.androiddevchallenge.ui.page.puppydetail.PuppyDetailScreen

sealed class Destination {

    abstract val route: String

    object Puppies : Destination() {
        override val route: String
            get() = "puppies"
    }

    object PuppyDetail : Destination() {

        override val route: String
            get() = "puppy_detail"
    }
}

@Composable
fun MyNavHost(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Destination.Puppies.route,
    ) {
        composable(Destination.Puppies.route) {
            PuppiesScreen(navController = navController)
        }
        composable(
            "puppies/{puppyId}",
            arguments = listOf(
                navArgument("puppyId") {
                    type = NavType.IntType
                }
            )
        ) {
            PuppyDetailScreen(Id.Puppy(it.arguments?.getInt("puppyId") ?: -1))
        }
    }
}

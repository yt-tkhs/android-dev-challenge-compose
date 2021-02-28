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

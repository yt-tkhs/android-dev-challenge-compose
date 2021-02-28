package com.example.androiddevchallenge.ui.page.puppies

import android.util.Log
import androidx.compose.foundation.layout.*
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

package com.example.androiddevchallenge.ui.page.puppydetail

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
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
  val snackbarHostState = remember { mutableStateOf(SnackbarHostState()) }

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

package com.example.androiddevchallenge.ui.page.puppydetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.data.repository.PuppyRepository
import com.example.androiddevchallenge.domain.models.Puppy
import com.example.androiddevchallenge.domain.valueobject.Id
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class PuppyDetailViewModel : ViewModel() {

  private val puppyRepository by lazy { PuppyRepository() }

  private val _puppy by lazy { MutableSharedFlow<Puppy?>(replay = 1) }
  val puppy: Flow<Puppy?> get() = _puppy

  fun initialize(puppyId: Id.Puppy) {
    viewModelScope.launch {
      _puppy.emit(puppyRepository.getPuppy(puppyId))
    }
  }
}

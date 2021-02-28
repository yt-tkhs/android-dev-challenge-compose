package com.example.androiddevchallenge.ui.page.puppies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.data.repository.PuppyRepository
import com.example.androiddevchallenge.domain.models.Puppy
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class PuppiesViewModel : ViewModel() {

  private val puppyRepository by lazy { PuppyRepository() }

  private val _puppies by lazy { MutableSharedFlow<List<Puppy>>(replay = 1) }
  val puppies: Flow<List<Puppy>> get() = _puppies

  init {
    viewModelScope.launch {
      _puppies.emit(puppyRepository.getPuppies())
    }
  }
}

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

/*
 * Copyright 2022 ravishrajput.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ravishrajput.compose.mvi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<ViewState, ViewEvent, ViewAction> : ViewModel() {

  protected abstract val initialViewState: ViewState
  protected abstract fun processAction(action: ViewAction)

  protected var lastViewState: ViewState = initialViewState

  private val _viewState = MutableStateFlow(initialViewState)
  val viewState = _viewState.asStateFlow()

  private val _viewEvents = Channel<ViewEvent?>(Channel.BUFFERED)
  val viewEvents = _viewEvents.receiveAsFlow()

  private val _viewActions = Channel<ViewAction>(Channel.BUFFERED)

  init {
    viewModelScope.launch {
      _viewActions.consumeEach { action ->
        processAction(action)
      }
    }
  }

  open protected fun updateViewState(state: ViewState) {
    _viewState.value = state
    lastViewState = state
  }

  protected fun sendEvent(event: ViewEvent) = viewModelScope.launch {
    _viewEvents.send(event)
  }

  fun applyAction(action: ViewAction) = viewModelScope.launch {
    _viewActions.send(action)
  }

}
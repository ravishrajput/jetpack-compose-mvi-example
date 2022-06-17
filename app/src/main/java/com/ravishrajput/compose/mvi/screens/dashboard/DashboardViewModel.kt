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

package com.ravishrajput.compose.mvi.screens.dashboard

import androidx.lifecycle.viewModelScope
import com.ravishrajput.compose.mvi.viewmodel.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class DashboardViewModel :
  BaseViewModel<DashboardViewState, DashboardViewEvent, DashboardViewAction>() {

  override val initialViewState: DashboardViewState
    get() = DashboardViewState.Default

  override fun processAction(action: DashboardViewAction) {
    when (action) {
      is DashboardViewAction.Launch -> fetchApiData()
    }
  }

  private fun fetchApiData() {
    viewModelScope.launch {
      delay(3000)
      updateViewState(DashboardViewState.LoadingComplete)
    }
  }
}
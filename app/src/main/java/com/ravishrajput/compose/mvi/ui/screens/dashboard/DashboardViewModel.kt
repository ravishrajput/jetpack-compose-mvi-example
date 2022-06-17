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

package com.ravishrajput.compose.mvi.ui.screens.dashboard

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.ravishrajput.compose.mvi.domain.mappers.UsersListMapper
import com.ravishrajput.compose.mvi.domain.usecase.UserDetailsUseCase
import com.ravishrajput.compose.mvi.ui.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
  private val useCase: UserDetailsUseCase,
  private val mapper: UsersListMapper
) : BaseViewModel<DashboardViewState, DashboardViewEvent, DashboardViewAction>() {

  override val initialViewState: DashboardViewState
    get() = DashboardViewState.Default

  override fun processAction(action: DashboardViewAction) {
    when (action) {
      is DashboardViewAction.Launch -> fetchApiData()
    }
  }

  private fun fetchApiData() {
    viewModelScope.launch {
      useCase.fetchUserData()
        .catch { error ->
          Log.e("Demo", "fetchApiData: ${error.message}")
        }
        .collect {
          updateViewState(DashboardViewState.Data(mapper.toUserDetails(it)))
        }
    }
  }
}
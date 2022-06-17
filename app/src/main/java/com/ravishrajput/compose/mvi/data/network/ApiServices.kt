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

package com.ravishrajput.compose.mvi.data.network

import com.ravishrajput.compose.mvi.data.model.UsersData
import retrofit2.Retrofit
import retrofit2.http.GET

interface ApiServices {

  @GET("/v1/user")
  suspend fun getUsers(): UsersData

  companion object Factory {
    fun create(retrofit: Retrofit): ApiServices = retrofit.create(ApiServices::class.java)
  }
}
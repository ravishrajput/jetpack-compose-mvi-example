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

package com.ravishrajput.compose.mvi.di

import com.ravishrajput.compose.mvi.data.network.ApiRepositoryImpl
import com.ravishrajput.compose.mvi.data.network.ApiServices
import com.ravishrajput.compose.mvi.domain.network.ApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@InstallIn(SingletonComponent::class)
@Module
object AppRepoModule {
  @Provides
  fun providesApiRepository(apiServices: ApiServices): ApiRepository =
    ApiRepositoryImpl(apiServices)
}

@InstallIn(SingletonComponent::class)
@Module
object ApiServiceModule {
  @Provides
  fun providesApiServices(retrofit: Retrofit): ApiServices = ApiServices.create(retrofit)
}
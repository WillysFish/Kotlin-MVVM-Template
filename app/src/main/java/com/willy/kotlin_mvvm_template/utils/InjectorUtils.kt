/*
 * Copyright 2018 Google LLC
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

package com.willy.kotlin_mvvm_template.utils

import android.app.Application
import com.willy.kotlin_mvvm_template.data.osc.CameraNetwork
import com.willy.kotlin_mvvm_template.data.repositories.CameraRepository
import com.willy.kotlin_mvvm_template.ui.main.LookingUpViewModelFactory

/**
 * Static methods used to inject classes needed for various Activities and Fragments.
 */
object InjectorUtils {

    private fun getCameraRepository(context: Application): CameraRepository {
        return CameraRepository.getInstance(CameraNetwork.getInstance(context))
    }

    fun provideLookingUpViewModelFactory(context: Application): LookingUpViewModelFactory {
        val repository = getCameraRepository(context)
        return LookingUpViewModelFactory(repository)
    }
}

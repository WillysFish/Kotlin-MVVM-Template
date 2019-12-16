package com.willy.kotlin_mvvm_template.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.willy.kotlin_mvvm_template.data.repositories.CameraRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LookingUpViewModel(private val repository: CameraRepository) : ViewModel() {

    fun apInit() {
        launch({
            withContext(Dispatchers.IO) {
//                repository.apInit()
            }
        }, {
        })
    }

    fun openCamera() {
        launch({
            withContext(Dispatchers.IO) {
//                repository.openCamera()
            }
        }, {
        })
    }

    fun closeCamera() {
        launch({
            withContext(Dispatchers.IO) {
//                repository.closeCamera()
//                repository.deInit()
            }
        }, {
        })
    }

    private fun launch(block: suspend () -> Unit, error: suspend (Throwable) -> Unit) =
        viewModelScope.launch {
            try {
                block()
            } catch (e: Throwable) {
                error(e)
            }
        }
}

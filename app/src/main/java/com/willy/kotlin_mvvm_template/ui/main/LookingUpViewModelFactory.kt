package com.willy.kotlin_mvvm_template.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.willy.kotlin_mvvm_template.data.repositories.CameraRepository

class LookingUpViewModelFactory(private val repository: CameraRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LookingUpViewModel(repository) as T
    }
}
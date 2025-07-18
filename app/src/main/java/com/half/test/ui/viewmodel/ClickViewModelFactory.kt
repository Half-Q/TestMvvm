package com.half.test.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.half.test.data.repository.ClickRepository

class ClickViewModelFactory (
    private val repository: ClickRepository
): ViewModelProvider.Factory{

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ClickViewModel::class.java)) {
            return ClickViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
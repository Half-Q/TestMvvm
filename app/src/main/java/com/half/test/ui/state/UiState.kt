package com.half.test.ui.state

sealed class UiState {
    object Idle: UiState()
    object Loading: UiState()
    data class Success(val message: String) : UiState()
    data class Error(val message: String) : UiState()
}
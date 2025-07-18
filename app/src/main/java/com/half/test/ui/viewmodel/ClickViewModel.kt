package com.half.test.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.half.test.data.repository.ClickRepository
import com.half.test.ui.state.UiState
import com.half.test.util.NetworkStatus
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ClickViewModel(
    private val repository: ClickRepository
): ViewModel() {

    // UI状态
    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState: StateFlow<UiState> = _uiState.asStateFlow()

    // 添加新纪录
    fun addRecord(content : String) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            try {
                repository.addNewRecord(content)
                _uiState.value = UiState.Success("记录已添加")
            } catch (e : Exception) {
                _uiState.value = UiState.Success("添加失败: ${e.message}")
            }

        }
    }

    // 同步记录
    fun syncRecords() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            when (val result = repository.syncRecords()) {
                is NetworkStatus.Success -> {
                    _uiState.value = UiState.Success("同步完成")
                }
                is NetworkStatus.Error -> {
                    _uiState.value = UiState.Error("同步失败: ${result.message}")
                }
            }
        }
    }

    // 刷新数据
    fun refreshRecords() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            when (val result = repository.refreshRecords()) {
                is NetworkStatus.Success -> {
                    _uiState.value = UiState.Success("数据已刷新")
                }
                is NetworkStatus.Error -> {
                    _uiState.value = UiState.Error("刷新失败: ${result.message}")
                }
            }
        }
    }
}
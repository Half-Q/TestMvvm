package com.half.test.ui.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.half.test.databinding.ActivityMainBinding
import com.half.test.di.AppModule
import com.half.test.ui.state.UiState
import com.half.test.ui.viewmodel.ClickViewModel
import com.half.test.ui.viewmodel.ClickViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"

    private lateinit var binding: ActivityMainBinding
    private val viewModel: ClickViewModel by viewModels {
        ClickViewModelFactory(AppModule.provideRepository())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUI()
        setupObserve()

    }

    private fun setupUI() {

        binding.btnAddRecord.setOnClickListener {
            Log.d(TAG, "btnAddRecord")
            viewModel.addRecord("按钮点击记录")
        }

        binding.btnSync.setOnClickListener {
            Log.d(TAG, "btnSync")
            viewModel.syncRecords()
        }

        binding.btnRefresh.setOnClickListener {
            Log.d(TAG, "btnRefresh")
            viewModel.refreshRecords()
        }
    }

    private fun setupObserve() {
        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                when (state) {
                    is UiState.Loading -> showLoading()
                    is UiState.Success -> showSuccess(state.message)
                    is UiState.Error -> showError(state.message)
                    UiState.Idle -> hideLoading()
                }
            }
        }
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }

    private fun showSuccess(message: String) {
        hideLoading()
        // 更新UI或显示Toast
    }

    private fun showError(message: String) {
        hideLoading()
        // 显示错误提示
    }

}
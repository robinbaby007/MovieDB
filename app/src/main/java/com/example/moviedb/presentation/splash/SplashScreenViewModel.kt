package com.example.moviedb.presentation.splash
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SplashScreenViewModel : ViewModel() {

    private val _sync = MutableStateFlow(true)
    val sync = _sync.asStateFlow()
    init {
        viewModelScope.launch {
            delay(3000)
            _sync.value = false
        }
    }
}
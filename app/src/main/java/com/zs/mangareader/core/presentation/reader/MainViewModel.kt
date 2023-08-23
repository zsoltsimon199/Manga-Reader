package com.zs.mangareader.core.presentation.reader

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zs.mangareader.core.domain.helpers.ThemeDataStore
import com.zs.mangareader.core.presentation.reader.ui.ThemeChooser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val savedThemeState: ThemeDataStore
) : ViewModel() {

    init {
        getCurrentTheme()
    }

    private fun getCurrentTheme(){
        viewModelScope.launch {
            ThemeChooser.isDarkTheme.value = savedThemeState.isDarkTheme.first()
        }
    }

    fun saveTheme(theme: Boolean) {
        viewModelScope.launch {
            savedThemeState.savePreference(theme)
            ThemeChooser.isDarkTheme.value = theme
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}
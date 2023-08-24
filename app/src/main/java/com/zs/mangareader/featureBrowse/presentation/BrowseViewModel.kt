package com.zs.mangareader.featureBrowse.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zs.mangareader.core.domain.models.Resource
import com.zs.mangareader.core.domain.models.mangaResponse.MangaResponse
import com.zs.mangareader.featureBrowse.domain.useCase.MangaListUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BrowseViewModel @Inject constructor(
    private val mangaListUseCases: MangaListUseCases
) : ViewModel() {

    private var _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private var _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()

    private var _mangas = MutableStateFlow<MangaResponse?>(null)
    val mangas = _mangas.asStateFlow()

    fun getMangas() {
        viewModelScope.launch {
            _isLoading.value = true
            when (val result = mangaListUseCases.getMangasUseCase.execute()) {
                is Resource.Success -> {
                    _isLoading.value = false
                    val data = result.data
                    _mangas.value = data
                }
                is Resource.Error -> {
                    _isLoading.value = false

                    _error.value = result.message
                    if (result.data != null) {
                        val data = result.data
                        _mangas.value = data
                    }
                }
            }
        }
    }

    fun clearMangas() {
        _mangas.value = null
    }

    fun clearError(){
        _error.value = ""
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}
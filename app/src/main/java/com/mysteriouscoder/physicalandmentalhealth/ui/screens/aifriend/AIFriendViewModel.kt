package com.mysteriouscoder.physicalandmentalhealth.ui.screens.aifriend


import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.ai.client.generativeai.GenerativeModel
import com.google.ai.client.generativeai.type.content
import com.google.ai.client.generativeai.type.generationConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Initial)
    val uiState = _uiState.asStateFlow()

    private lateinit var generativeModel: GenerativeModel
    private lateinit var generativeTextModel: GenerativeModel

    init {
        val config = generationConfig {
            temperature = 0.70f
        }

        generativeModel = GenerativeModel(
            modelName = "gemini-pro-vision",
            apiKey = "AIzaSyDZeborAwVi9Yl7lgWEWrPkXm6J7DwkOys",
            generationConfig = config
        )
        generativeTextModel = GenerativeModel(
            modelName = "gemini-pro",
            apiKey = "AIzaSyDZeborAwVi9Yl7lgWEWrPkXm6J7DwkOys",
            generationConfig = config
        )
    }

    fun questioning(userInput: String, selectedImages: List<Bitmap>) {
        _uiState.value = HomeUiState.Loading
        var prompt = ""
        if (selectedImages.isEmpty()) {
            prompt = "As my personal therapist answer me: $userInput"
        } else {
            prompt = "Take a look at images and then answer the following question: $userInput"
        }

        if (selectedImages.isNotEmpty()) {
            viewModelScope.launch(Dispatchers.IO) {
                try {

                    val content = content {
                        for (bitmap in selectedImages) {
                            image(bitmap)
                        }
                        text(prompt)
                    }
                    var output = ""
                    generativeModel.generateContentStream(content).collect {
                        output += it.text
                        _uiState.value = HomeUiState.Success(output)
                    }
                } catch (e: Exception) {
                    _uiState.value =
                        HomeUiState.Error(e.localizedMessage ?: "Error in generating content")
                }
            }
        }else{
            viewModelScope.launch(Dispatchers.IO) {
                try {

                    val content = content {
                        text(prompt)
                    }
                    var output = ""
                    generativeTextModel.generateContentStream(content).collect {
                        output += it.text
                        _uiState.value = HomeUiState.Success(output)
                    }
                } catch (e: Exception) {
                    _uiState.value =
                        HomeUiState.Error(e.localizedMessage ?: "Error in generating content")
                }
            }
        }

    }
}

sealed interface HomeUiState {
    object Initial : HomeUiState
    object Loading : HomeUiState
    data class Success(val outputText: String) : HomeUiState
    data class Error(val error: String) : HomeUiState

}
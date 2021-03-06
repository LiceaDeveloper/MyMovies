package com.liceadev.architectcoders.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.liceadev.architectcoders.ui.common.ScopedViewModel
import com.liceadev.domain.Photo
import com.liceadev.usecases.FindPhotoById
import com.liceadev.usecases.TogglePhotoFavorite
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class DetailViewModel(
    private val photoId: Int,
    private val findPhotoById: FindPhotoById,
    private val togglePhotoFavorite: TogglePhotoFavorite,
    uiDispatcher: CoroutineDispatcher
) : ScopedViewModel(uiDispatcher) {

    data class UiModel(val photo: Photo)

    private val _model = MutableLiveData<UiModel>()
    val model: LiveData<UiModel>
        get() {
            if (_model.value == null) findPhoto()
            return _model
        }

    private fun findPhoto() = launch {
        _model.value = UiModel(findPhotoById.invoke(photoId))
    }

    fun onFavoriteClicked() = launch {
        _model.value?.photo?.let {
            _model.value = UiModel(togglePhotoFavorite.invoke(it))
        }
    }
}
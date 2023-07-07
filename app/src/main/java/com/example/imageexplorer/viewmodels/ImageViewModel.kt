package com.example.imageexplorer.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.imageexplorer.core.UIState
import com.example.imageexplorer.data.model.Hit
import com.example.imageexplorer.data.model.ImageResponse
import com.example.imageexplorer.data.remote.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ImageViewModel @Inject constructor(private val imageRepository: ImageRepository) :
    ViewModel() {
    var imageLiveData = MutableLiveData<UIState<ImageResponse<Hit>>>()
    var filterLiveData = MutableLiveData<UIState<ImageResponse<Hit>>>()

    fun getImages(page: Int, perPage: Int, category:List<String>): LiveData<UIState<ImageResponse<Hit>>> {
        imageLiveData.value = UIState.Loading()

        imageLiveData = imageRepository.getImage(page, perPage, category)
        return imageLiveData
    }
    fun filterCategory(category:List<String>): LiveData<UIState<ImageResponse<Hit>>>{
        filterLiveData.value = UIState.Loading()

        filterLiveData = imageRepository.getFilterResult(category)
        return filterLiveData
    }

}

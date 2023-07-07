package com.example.imageexplorer.data.remote

import androidx.lifecycle.MutableLiveData
import com.example.imageexplorer.core.UIState
import com.example.imageexplorer.data.model.Hit
import com.example.imageexplorer.data.model.ImageResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class ImageRepository @Inject constructor(private val imageApiService: ImageApiService) {
    fun getImage(
        page: Int,
        perPage: Int,
        category: List<String>
    ): MutableLiveData<UIState<ImageResponse<Hit>>> {
        val liveData = MutableLiveData<UIState<ImageResponse<Hit>>>()
        liveData.value = UIState.Loading()

        imageApiService.getImage(page = page, perPage = perPage, category = category)
            .enqueue(object : Callback<ImageResponse<Hit>> {
                override fun onResponse(
                    call: Call<ImageResponse<Hit>>,
                    response: Response<ImageResponse<Hit>>
                ) {
                    if (response.isSuccessful) {
                        val imageResponse = response.body()
                        liveData.value = UIState.Success(imageResponse)
                    } else {
                        liveData.value = UIState.Error(" unsuccessful request, try again")
                    }
                }

                override fun onFailure(call: Call<ImageResponse<Hit>>, t: Throwable) {
                    liveData.value = UIState.Error(t.localizedMessage ?: "ERROR")
                }
            })

        return liveData
    }

    fun getFilterResult(category: List<String>): MutableLiveData<UIState<ImageResponse<Hit>>> {
        val liveData = MutableLiveData<UIState<ImageResponse<Hit>>>()
        liveData.value = UIState.Loading()

        imageApiService.getImage(category = category)
            .enqueue(object : Callback<ImageResponse<Hit>> {
                override fun onResponse(
                    call: Call<ImageResponse<Hit>>,
                    response: Response<ImageResponse<Hit>>
                ) {
                    if (response.isSuccessful) {
                        val imageResponse = response.body()
                        liveData.value = UIState.Success(imageResponse)
                    } else {
                        liveData.value = UIState.Error("error")
                    }
                }

                override fun onFailure(call: Call<ImageResponse<Hit>>, t: Throwable) {
                    liveData.value = UIState.Error(t.localizedMessage ?: "ERROR")
                }
            })

        return liveData
    }
}
package com.example.imageexplorer.data.model

data class ImageResponse<T>(
    val hits: List<Hit>,
    val total: Int,
    val totalHits: Int
)
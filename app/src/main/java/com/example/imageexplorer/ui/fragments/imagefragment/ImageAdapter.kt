package com.example.imageexplorer.ui.fragments.imagefragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.imageexplorer.data.model.Hit
import com.example.imageexplorer.databinding.ItemImgBinding

class ImageAdapter :
    androidx.recyclerview.widget.ListAdapter<Hit, ImageAdapter.ImageViewHolder>(ImageDiffUtil()) {
    inner class ImageViewHolder(private val binding: ItemImgBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(imgModel: Hit) {
            Glide.with(binding.root).load(imgModel.webformatURL).centerCrop().into(binding.itemImg)
            binding.itemTvType.text = imgModel.tags
        }
    }

    class ImageDiffUtil : DiffUtil.ItemCallback<Hit>() {
        override fun areItemsTheSame(oldItem: Hit, newItem: Hit): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Hit, newItem: Hit): Boolean {
            return oldItem == newItem
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ImageViewHolder(
        ItemImgBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val itemPosition = getItem(position)
        holder.bind(itemPosition)
    }
}
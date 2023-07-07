package com.example.imageexplorer.ui.fragments.imagefragment

import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.imageexplorer.base.BaseFragment
import com.example.imageexplorer.core.UIState
import com.example.imageexplorer.databinding.FragmentImageBinding
import com.example.imageexplorer.ui.fragments.filterfragment.FilterFragment
import com.example.imageexplorer.viewmodels.ImageViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageFragment : BaseFragment<FragmentImageBinding>(FragmentImageBinding::inflate),
    FilterFragment.FilterResult {
    private var category =
        listOf(
            "music",
            "backgrounds",
            "nature",
            "science",
            "education",
            "feelings",
            "health",
            "computer",
            "food",
            "travel"
        )
    private var page: Int = 1
    private var perPage: Int = 25
    private val imageViewModel by lazy { ViewModelProvider(this)[ImageViewModel::class.java] }
    private val adapter: ImageAdapter by lazy { ImageAdapter() }

    override fun setupUI() {
        binding.recyclerView.adapter = adapter
        binding.filterBtn.setOnClickListener {
            val filterFragment = FilterFragment(this)
            filterFragment.show(childFragmentManager,  "tag")
        }
        fetchData()
    }

    private fun fetchData() {
        imageViewModel.getImages(page, perPage, category)
        binding.refresh.setOnRefreshListener {
            binding.refresh.isRefreshing = false
            page++
            imageViewModel.getImages(page, perPage, category)
            getWeather()
        }
        getWeather()
    }

    private fun getWeather() {
        imageViewModel.imageLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is UIState.Loading -> {
                    Toast.makeText(requireContext(), "Loading, please wait", Toast.LENGTH_SHORT)
                        .show()
                }
                is UIState.Success -> {
                    adapter.submitList(it.data?.hits)
                }
                is UIState.Error -> {
                    Toast.makeText(
                        requireContext(),
                        "Error 303, please try again",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            }
        }
    }
    override fun resultListener(result: List<String>) {
        imageViewModel.filterCategory(result)
    }
}






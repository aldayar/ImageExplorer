package com.example.imageexplorer.ui.fragments.filterfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.imageexplorer.databinding.FragmentFilterBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class FilterFragment(private val listener: FilterResult) :
    BottomSheetDialogFragment() {
    private lateinit var binding: FragmentFilterBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  {
        binding = FragmentFilterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnFilterResult.setOnClickListener {
            val result = binding.etFilter.text.trim().toString()
            val resultList = listOf(result)
            listener.resultListener(result = resultList)
            onDestroyView()
        }
    }

    interface FilterResult {
        fun resultListener(result: List<String>)
    }
}
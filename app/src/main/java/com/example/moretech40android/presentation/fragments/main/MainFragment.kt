package com.example.moretech40android.presentation.fragments.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import com.example.moretech40android.databinding.FragmentMainBinding
import com.example.moretech40android.presentation.activity.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentMainBinding.inflate(inflater, container, false)
        val mainActivityViewModel: MainActivityViewModel =
            ViewModelProvider(requireActivity())[MainActivityViewModel::class.java]
        mainActivityViewModel.showActionBar()

        return binding.root
    }
}
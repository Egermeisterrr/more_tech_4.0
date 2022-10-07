package com.example.moretech40android.presentation.fragments.insides

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.moretech40android.databinding.FragmentInsidesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class InsidesFragment : Fragment() {

    private val viewModel: InsidesViewModel by viewModels()
    private lateinit var binding: FragmentInsidesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentInsidesBinding.inflate(inflater, container, false)

        return binding.root
    }
}
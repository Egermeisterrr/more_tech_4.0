package com.example.moretech40android.presentation.fragments.profile.editusername

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.moretech40android.databinding.FragmentChangeUsernameBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditUsernameFragment: Fragment() {

    private lateinit var binding: FragmentChangeUsernameBinding
    private val viewModel: EditUsernameViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentChangeUsernameBinding.inflate(inflater, container, false)
        viewModel.navEvent.observe(viewLifecycleOwner) { action ->
            findNavController().navigate(action)
        }
        binding.backToProfile.setOnClickListener {
            viewModel.toProfileNavigation()
        }

        return binding.root
    }
}
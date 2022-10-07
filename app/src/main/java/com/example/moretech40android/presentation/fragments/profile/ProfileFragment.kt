package com.example.moretech40android.presentation.fragments.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.moretech40android.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.editIcon1.setOnClickListener {
            viewModel.toEditEmailNavigation()
        }
        binding.editIcon2.setOnClickListener {
            viewModel.toEditUsernameNavigation()
        }
        viewModel.navEvent.observe(viewLifecycleOwner) { action ->
            findNavController().navigate(action)
        }

        return binding.root
    }
}
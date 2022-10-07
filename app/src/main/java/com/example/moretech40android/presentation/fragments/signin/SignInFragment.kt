package com.example.moretech40android.presentation.fragments.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.moretech40android.databinding.FragmentSignInBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment(): Fragment() {

    private val viewModel: SignInViewModel by viewModels()
    private lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSignInBinding.inflate(inflater, container, false)
        binding.singUpLink.setOnClickListener {
            viewModel.toSignUpNavigation()
        }
        viewModel.navEvent.observe(viewLifecycleOwner) { action ->
            this.findNavController().navigate(action)
        }
        binding.singInButton.setOnClickListener {
            viewModel.toMainFragmentNavigation()
        }

        return binding.root
    }
}
package com.example.moretech40android.presentation.fragments.signin

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.moretech40android.databinding.FragmentSignInBinding
import com.google.android.material.snackbar.Snackbar
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
            val inputMethod =
                activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethod.hideSoftInputFromWindow(requireView().windowToken, 0)
            viewModel.loginUser(binding.userNameEditText.text.toString(), binding.passwordEditText.text.toString())
        }
        viewModel.errorMessage.observe(viewLifecycleOwner) { error->
            Snackbar.make(binding.root, error, Snackbar.LENGTH_LONG).show()
        }
        viewModel.loginSuccessful.observe(viewLifecycleOwner) {
            viewModel.toMainFragmentNavigation()
        }

        return binding.root
    }
}
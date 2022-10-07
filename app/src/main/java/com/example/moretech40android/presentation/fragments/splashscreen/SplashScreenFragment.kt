package com.example.moretech40android.presentation.fragments.splashscreen

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.moretech40android.databinding.FragmentSplashScreenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreenFragment : Fragment() {

    private lateinit var binding: FragmentSplashScreenBinding
    private val viewModel: SplashScreenViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentSplashScreenBinding.inflate(inflater)

        viewModel.navEvent.observe(viewLifecycleOwner) { action ->
            Handler(Looper.myLooper()!!).postDelayed({
                this.findNavController().navigate(action)
            }, 1500)
        }
        viewModel.checkSharedPref()

        return binding.root
    }
}
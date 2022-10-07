package com.example.moretech40android.presentation.fragments.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.moretech40android.databinding.FragmentOnboardingBinding
import com.example.moretech40android.presentation.activity.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnboardingFragment : Fragment() {

    private lateinit var adapter: OnboardingAdapter
    private lateinit var binding: FragmentOnboardingBinding
    private val viewModel: OnboardingViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentOnboardingBinding.inflate(inflater, container, false)
        adapter = OnboardingAdapter(requireContext(), activity as MainActivity)
        binding.onboardingViewpager.adapter = adapter

        viewModel.navEvent.observe(viewLifecycleOwner) { action ->
            this.findNavController().navigate(action)
        }

        return binding.root
    }

    internal fun swipeRight(x: Int) {
        if (x < 1) {
            binding.onboardingViewpager.currentItem = x + 1
        } else if (x == 1) {
            viewModel.toSignInNavigation()
        }
    }
}
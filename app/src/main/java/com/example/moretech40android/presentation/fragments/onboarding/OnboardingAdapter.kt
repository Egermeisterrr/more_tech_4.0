package com.example.moretech40android.presentation.fragments.onboarding

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.example.moretech40android.R
import com.example.moretech40android.databinding.OnboardingItemBinding
import com.example.moretech40android.presentation.activity.MainActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class OnboardingAdapter @Inject constructor(
    @ApplicationContext val context: Context,
    val activity: MainActivity
) : RecyclerView.Adapter<OnboardingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OnboardingViewHolder {
        val binding = OnboardingItemBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return OnboardingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OnboardingViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                binding.card.setImageDrawable(this)
                binding.onboardingButton.setOnClickListener {
                    val fragmentManager = activity.supportFragmentManager
                    val navHostFragment = fragmentManager.fragments.last() as NavHostFragment
                    val onboardingFragment =
                        navHostFragment.childFragmentManager.fragments[0] as OnboardingFragment
                    onboardingFragment.swipeRight(position)
                }
            }
        }
    }

    override fun getItemCount(): Int = list.size

    private val list = arrayListOf(
        ContextCompat.getDrawable(context, R.drawable.slide1),
        ContextCompat.getDrawable(context, R.drawable.slide2)
    )
}
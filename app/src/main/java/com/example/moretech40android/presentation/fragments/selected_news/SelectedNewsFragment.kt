package com.example.moretech40android.presentation.fragments.selected_news

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.domain.model.NewsModel
import com.example.moretech40android.databinding.FragmentSelectedNewsBinding
import com.example.moretech40android.presentation.fragments.main.MainFragment
import com.example.moretech40android.presentation.fragments.main.MainFragment.Companion.CURRENT_FRAGMENT
import com.example.moretech40android.presentation.fragments.main.MainFragment.Companion.MAIN_FRAGMENT
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SelectedNewsFragment : Fragment() {

    private lateinit var binding: FragmentSelectedNewsBinding
    private val viewModel: SelectedNewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSelectedNewsBinding.inflate(inflater, container, false)
        viewModel.setNews(
            NewsModel(
                arguments?.getString(MainFragment.NEWS_TITLE)!!,
                arguments?.getString(MainFragment.NEWS_IMAGE)!!,
                arguments?.getStringArrayList(MainFragment.NEWS_TAGS) as ArrayList<String>,
                arguments?.getString(MainFragment.NEWS_TIME)!!,
                arguments?.getString(MainFragment.NEWS_CONTENT)!!,
            )
        )
        viewModel.currentNews.observe(viewLifecycleOwner) { news->
            Glide.with(requireContext())
                .load(Uri.parse("android.resource://com.example.moretech40android/drawable/heart_gray"))
                .transform(MultiTransformation(CenterCrop(), RoundedCorners(20)))
                .into(binding.newsImage)
            binding.newsTitle.text = news.title
            binding.newsDate.text = news.time
            binding.newsContent.text = news.content
        }
        viewModel.navEvent.observe(viewLifecycleOwner) { action ->
            this.findNavController().navigate(action)
        }
        binding.backToMain.setOnClickListener {
            if(arguments?.getString(CURRENT_FRAGMENT).equals(MAIN_FRAGMENT)) {
                viewModel.toMainNavigation()
            } else {
                viewModel.toInsightsNavigation()
            }
        }

        return binding.root
    }
}
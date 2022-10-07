package com.example.moretech40android.presentation.fragments.main.trends

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.domain.model.NewsModel
import com.example.moretech40android.databinding.TrendItemBinding
import com.example.moretech40android.presentation.fragments.main.MainViewModel
import dagger.hilt.android.qualifiers.ApplicationContext

class TrendsAdapter(
    @ApplicationContext private val context: Context,
    private val viewModel: MainViewModel
) : ListAdapter<NewsModel, TrendsViewHolder>(TrendsDiffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrendsViewHolder {
        val binding = TrendItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TrendsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TrendsViewHolder, position: Int) {
        with(holder) {
            with(getItem(position)) {
                binding.newsTitle.text = title
                Glide.with(context)
                    .load(Uri.parse("android.resource://com.example.moretech40android/drawable/heart_gray"))
                    .transform(MultiTransformation(CenterCrop(), RoundedCorners(30)))
                    .into(binding.newsImage)
                binding.contentField1.setOnClickListener {
                    viewModel.newsSelected(this)
                }
            }
        }
    }

    object TrendsDiffUtil : DiffUtil.ItemCallback<NewsModel>() {
        override fun areItemsTheSame(
            oldItem: NewsModel,
            newItem: NewsModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: NewsModel,
            newItem: NewsModel
        ): Boolean {
            return oldItem.title == newItem.title
        }
    }
}
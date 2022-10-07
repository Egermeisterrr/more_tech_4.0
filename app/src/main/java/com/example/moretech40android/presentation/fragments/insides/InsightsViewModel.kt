package com.example.moretech40android.presentation.fragments.insides

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.model.NewsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class InsightsViewModel @Inject constructor(): ViewModel() {

    private val _selectedNews = MutableLiveData<NewsModel>()
    val selectedNews: LiveData<NewsModel> = _selectedNews

    fun newsSelected(news: NewsModel) {
        _selectedNews.value = news
    }
}
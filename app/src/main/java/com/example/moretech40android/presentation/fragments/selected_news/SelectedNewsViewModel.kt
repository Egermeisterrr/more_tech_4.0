package com.example.moretech40android.presentation.fragments.selected_news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavDirections
import com.example.domain.model.NewsModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectedNewsViewModel @Inject constructor(): ViewModel() {

    private val _navEvent = MutableLiveData<NavDirections>()
    val navEvent: LiveData<NavDirections> = _navEvent

    private val _currentNews = MutableLiveData<NewsModel>()
    val currentNews: LiveData<NewsModel> = _currentNews

    fun toMainNavigation() {
        _navEvent.postValue(SelectedNewsFragmentDirections.actionSelectedNewsFragmentToMainFragment())
    }

    fun setNews(news: NewsModel) {
        _currentNews.value = news
    }
}
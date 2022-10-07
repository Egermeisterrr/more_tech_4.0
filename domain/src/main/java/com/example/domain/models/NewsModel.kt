package com.example.domain.models

data class NewsModel(
    val title: String,
    val image: String,
    val tags: List<String>,
    val time: String
)
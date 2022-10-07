package com.example.domain.model

data class NewsModel(
    val title: String,
    val image: String,
    val tags: List<String>,
    val time: String,
    val content: String
)
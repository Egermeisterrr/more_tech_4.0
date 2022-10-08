package com.example.domain.models

data class DigestModel(
    val clean_text_list_lem: List<String>,
    val img: String,
    val tags: List<String>,
    val text: String,
    val text_clean: String,
    val time: String,
    val title: String
)
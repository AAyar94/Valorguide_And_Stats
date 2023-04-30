package com.aayar94.valorantguidestats.data.models.user_stats.last_matches

data class UserMatchesDataModel(
    val `data`: List<Data>,
    val name: String,
    val results: Results,
    val status: Int,
    val tag: String
)
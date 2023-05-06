package com.aayar94.valorantguidestats.data.models.user_stats.match_details

data class Data(
    val metadata: Metadata,
    val players: Players,
    val rounds: List<Round>,
    val teams: Teams
)
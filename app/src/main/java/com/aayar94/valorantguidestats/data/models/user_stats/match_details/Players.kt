package com.aayar94.valorantguidestats.data.models.user_stats.match_details

data class Players(
    val all_players: List<AllPlayer>,
    val blue: List<Blue>,
    val red: List<Red>
)
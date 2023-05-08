package com.aayar94.valorantguidestats.data.models.user_stats.match_details

data class Round(
    val player_stats: List<PlayerStat>,
    val winning_team: String
)
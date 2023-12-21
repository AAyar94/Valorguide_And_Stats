package com.aayar94.valorguidestats.data.models.user_stats.match_details

data class Round(
    val player_stats: List<PlayerStat>,
    val winning_team: String
)
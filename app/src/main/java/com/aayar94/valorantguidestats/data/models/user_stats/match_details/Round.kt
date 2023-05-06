package com.aayar94.valorantguidestats.data.models.user_stats.match_details

data class Round(
    val bomb_defused: Boolean,
    val bomb_planted: Boolean,
    val end_type: String,
    val player_stats: List<PlayerStat>,
    val winning_team: String
)
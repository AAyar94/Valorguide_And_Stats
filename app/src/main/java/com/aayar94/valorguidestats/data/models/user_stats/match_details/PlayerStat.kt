package com.aayar94.valorguidestats.data.models.user_stats.match_details

data class PlayerStat(
    val bodyshots: Int,
    val damage: Int,
    val headshots: Int,
    val kills: Int,
    val legshots: Int,
    val player_display_name: String,
    val player_puuid: String,
    val player_team: String,
)
package com.aayar94.valorantguidestats.data.models.user_stats.match_details

data class PlayerStat(
    val bodyshots: Int,
    val damage: Int,
    val headshots: Int,
    val kills: Int,
    val legshots: Int,
    val player_display_name: String,
    val player_puuid: String,
    val player_team: String,
    val score: Int,
    val stayed_in_spawn: Boolean,
    val was_afk: Boolean,
    val was_penalized: Boolean
)
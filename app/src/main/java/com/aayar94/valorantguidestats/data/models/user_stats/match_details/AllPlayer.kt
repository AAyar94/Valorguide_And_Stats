package com.aayar94.valorantguidestats.data.models.user_stats.match_details

data class AllPlayer(
    val assets: Assets,
    val character: String,
    val currenttier: Int,
    val currenttier_patched: String,
    val damage_made: Int,
    val damage_received: Int,
    val level: Int,
    val name: String,
    val party_id: String,
    val player_card: String,
    val player_title: String,
    val puuid: String,
    val stats: Stats,
    val tag: String,
    val team: String
)
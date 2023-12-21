package com.aayar94.valorguidestats.data.models.user_stats.user_mmr_change

data class Data(
    val currenttier: Int,
    val currenttierpatched: String,
    val elo: Int,
    val images: İmages,
    val mmr_change_to_last_game: Int,
    val name: String,
    val old: Boolean,
    val ranking_in_tier: Int,
    val tag: String
)
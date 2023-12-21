package com.aayar94.valorguidestats.data.models.user_stats.last_matches

data class Stats(
    val assists: Int,
    val character: Character,
    val damage: Damage,
    val deaths: Int,
    val kills: Int,
    val level: Int,
    val puuid: String,
    val score: Int,
    val shots: Shots,
    val team: String,
    val tier: Int
)
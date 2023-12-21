package com.aayar94.valorguidestats.data.models.user_stats.last_matches

data class Meta(
    val cluster: String,
    val id: String,
    val map: Map,
    val mode: String,
    val region: String,
    val season: Season,
    val started_at: String,
    val version: String
)
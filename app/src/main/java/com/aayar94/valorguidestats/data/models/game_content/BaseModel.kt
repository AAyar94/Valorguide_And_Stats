package com.aayar94.valorguidestats.data.models.game_content

data class BaseModel<T>(
    val status: Int,
    val data: T
)
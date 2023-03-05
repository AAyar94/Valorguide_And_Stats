package com.aayar94.valorantguidestats.data.models

data class BaseModel<T>(
    val status: Int,
    val data: T
)
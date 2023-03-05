package com.aayar94.valorantguidestats.data.models

import java.util.*

data class Version(
    val branch: String,
    val version: String,
    val buildVersion: String,
    val buildDate: Date
)
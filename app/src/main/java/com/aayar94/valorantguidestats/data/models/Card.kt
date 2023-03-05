package com.aayar94.valorantguidestats.data.models

class Card(
    val uuid: String,
    val displayName: String,
    val isHiddenIfNotOwner: Boolean,
    val displayIcon: String,
    val smallArt: String,
    val wideArt: String,
    val largeArt: String,
    val assetPath: String
)
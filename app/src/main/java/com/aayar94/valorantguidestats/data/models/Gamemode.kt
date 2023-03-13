package com.aayar94.valorantguidestats.data.models
class Gamemode(
    val displayName: String,
    val duration: String,
    val displayIcon: String,

)

class OverriddenGameFeature(
    val featureName: String,
    val state: Boolean
)

class GamemodeEquippable(
    val uuid: String,
    val displayName: String,
    val category: String,
    val displayIcon: String,
    val killStreamIcon: String,
    val assetPath: String
)
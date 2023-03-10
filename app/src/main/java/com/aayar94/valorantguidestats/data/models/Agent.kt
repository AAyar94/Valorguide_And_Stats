package com.aayar94.valorantguidestats.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Agent(
    val uuid: String,
    val displayName: String,
    val description: String,
    val developerName: String,
    val characterTags: Array<String>?,
    val displayIcon: String,
    val bustPortrait: String,
    val fullPortrait: String,
    val isFullPortraitRightFacing: Boolean,
    val isPlayableCharacter: Boolean,
    val isAvailableForTest: Boolean,
    val role: AgentRole,
    val abilities: Array<AgentAbility>?
) : Parcelable

@Parcelize
class AgentAbility(
    val slot: String,
    val displayName: String?,
    val description: String?,
    val displayIcon: String?
) : Parcelable


@Parcelize
class AgentRole(
    val uuid: String,
    val displayName: String,
    val description: String,
    val displayIcon: String,
    val assetPath: String
) : Parcelable
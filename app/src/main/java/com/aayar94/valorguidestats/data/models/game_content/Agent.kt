package com.aayar94.valorguidestats.data.models.game_content

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
class Agent(
    val uuid: String,
    val displayName: String,
    val description: String,
    val displayIcon: String,
    val fullPortrait: String,
    val role: AgentRole,
    val background: String,
    val backgroundGradientColors: Array<String>,
    val abilities: Array<AgentAbility>?
) : Parcelable

@Parcelize
class AgentAbility(
    val displayName: String?,
    val description: String?,
    val displayIcon: String?
) : Parcelable


@Parcelize
class AgentRole(
    val displayName: String,
    val displayIcon: String,
) : Parcelable
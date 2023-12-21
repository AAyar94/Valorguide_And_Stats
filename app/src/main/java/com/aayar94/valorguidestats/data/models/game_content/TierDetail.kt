package com.aayar94.valorguidestats.data.models.game_content

import com.google.gson.annotations.SerializedName


data class TierDetail (

  @SerializedName("tier"                 ) var tier                 : Int?    = null,
  @SerializedName("tierName"             ) var tierName             : String? = null,
  @SerializedName("division"             ) var division             : String? = null,
  @SerializedName("divisionName"         ) var divisionName         : String? = null,
  @SerializedName("color"                ) var color                : String? = null,
  @SerializedName("backgroundColor"      ) var backgroundColor      : String? = null,
  @SerializedName("smallIcon"            ) var smallIcon            : String? = null,
  @SerializedName("largeIcon"            ) var largeIcon            : String? = null,
  @SerializedName("rankTriangleDownIcon" ) var rankTriangleDownIcon : String? = null,
  @SerializedName("rankTriangleUpIcon"   ) var rankTriangleUpIcon   : String? = null

)
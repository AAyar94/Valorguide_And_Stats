package com.aayar94.valorguidestats.ui.fragment.your_stats_preview

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aayar94.valorguidestats.data.models.user_stats.last_matches.UserMatchesDataModel
import com.aayar94.valorguidestats.R
import com.aayar94.valorguidestats.databinding.RowLayoutUserMatchesBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class LastMatchesAdapter(val onClick: (userMatchId: String) -> Unit) :
    RecyclerView.Adapter<LastMatchesAdapter.MatchesViewHolder>() {

    private var matchList: MutableList<UserMatchesDataModel> = mutableListOf()

    inner class MatchesViewHolder(private val binding: RowLayoutUserMatchesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            binding.mapNameText.text = matchList[0].data[position].meta.map.name
            binding.matchTypeText.text = gameModeTranslator(matchList[0].data[position].meta.mode)
            binding.matchDateText.text = newFormatDate(matchList[0].data[position].meta.started_at)
            binding.root.setOnClickListener {
                if (matchList[0].data[position].meta.mode == "Deathmatch") {
                    val alertDialogBuilder = MaterialAlertDialogBuilder(binding.root.context)
                    alertDialogBuilder.setTitle(binding.root.context.getString(R.string.deathmatch))
                    alertDialogBuilder.setIcon(R.drawable.ic_maps)
                    alertDialogBuilder.setMessage(binding.root.context.getString(R.string.deathmatch_mode_haven_t_detailed_match_view))
                    alertDialogBuilder.setPositiveButton(
                        binding.root.context.getString(R.string.okay),
                        DialogInterface.OnClickListener { dialog, which ->
                            dialog.dismiss()
                        })
                    alertDialogBuilder.show()
                } else {
                    onClick(matchList[0].data[position].meta.id)
                }
            }
        }

        private fun gameModeTranslator(gameMode: String): String {
            return when (gameMode) {
                "Competitive" -> binding.root.context.getString(R.string.competitive)
                "Unrated" -> binding.root.context.getString(R.string.unrated)
                "Premier" -> binding.root.context.getString(R.string.premier)
                "Deathmatch" -> binding.root.context.getString(R.string.deathmatch)
                "Escalation" -> binding.root.context.getString(R.string.escalation)
                "Onboarding" -> binding.root.context.getString(R.string.onboarding)
                "Replication" -> binding.root.context.getString(R.string.replication)
                "Spike Rush" -> binding.root.context.getString(R.string.spike_rush)
                "PRACTICE" -> binding.root.context.getString(R.string.practice)
                "Snowball Fight" -> binding.root.context.getString(R.string.snowball_fight)
                "Swiftplay" -> binding.root.context.getString(R.string.swiftplay)
                "Team Deathmatch" -> binding.root.context.getString(R.string.team_deathmatch)
                else -> {
                    ""
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesViewHolder {
        val binding =
            RowLayoutUserMatchesBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return MatchesViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return matchList[0].data.size
    }

    override fun onBindViewHolder(holder: MatchesViewHolder, position: Int) {
        holder.bind(position)
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: UserMatchesDataModel) {
        matchList.clear()
        matchList.add(list)
        this.notifyDataSetChanged()
    }

    fun newFormatDate(startDate: String): String? {
        val timestamp = Instant.parse(startDate)
        val userTimeZone = ZoneId.systemDefault()
        val userDateTime = LocalDateTime.ofInstant(timestamp, userTimeZone)
        return userDateTime.format(DateTimeFormatter.ofPattern("hh:mm dd-MM-yyyy"))
    }

}


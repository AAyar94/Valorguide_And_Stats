package com.aayar94.valorantguidestats.ui.fragment.agents

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aayar94.valorantguidestats.R
import com.aayar94.valorantguidestats.data.models.game_content.Agent
import com.aayar94.valorantguidestats.databinding.RowLayoutAgentFragmentBinding
import com.aayar94.valorantguidestats.util.Constants.Companion.GlideImageLoader


class AgentsFragmentAdapter(
    val onItemClick: (agent: Agent) -> Unit
) :
    ListAdapter<Agent, AgentsFragmentAdapter.AgentsFragmentViewHolder>(
        AgentsDiffUtil()
    ) {

    inner class AgentsFragmentViewHolder(private val binding: RowLayoutAgentFragmentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val agent = currentList[position]
            with(binding) {
                with(agent) {
                    GlideImageLoader(
                        binding.root.context,
                        displayIcon,
                        agentImage
                    )
                    /***val gradientDrawable = GradientDrawable(
                        GradientDrawable.Orientation.TOP_BOTTOM, intArrayOf(
                            Color.parseColor(agent.backgroundGradientColors[0]),
                            Color.parseColor(agent.backgroundGradientColors[1]),
                            Color.parseColor(agent.backgroundGradientColors[2]),
                            Color.parseColor(agent.backgroundGradientColors[3]),
                        )
                    )
                    root.background = gradientDrawable*/
                    agentName.text = displayName
                    root.setOnClickListener {
                        onItemClick(currentList[position])
                    }
                    root.animation = AnimationUtils.loadAnimation(
                        root.context,
                        R.anim.recycler_view_item_falldown_anim
                    )
                }

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentsFragmentViewHolder {
        val binding = RowLayoutAgentFragmentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AgentsFragmentViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(holder: AgentsFragmentViewHolder, position: Int) {
        holder.bind(position)
    }
}

class AgentsDiffUtil : DiffUtil.ItemCallback<Agent>() {
    override fun areItemsTheSame(oldItem: Agent, newItem: Agent): Boolean {
        return oldItem == newItem
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Agent, newItem: Agent): Boolean {
        return oldItem == newItem
    }
}

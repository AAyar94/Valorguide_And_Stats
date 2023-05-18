package com.aayar94.valorantguidestats.ui.fragment.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aayar94.valorantguidestats.data.models.game_content.Agent
import com.aayar94.valorantguidestats.databinding.RowLayoutAgentsBinding
import com.aayar94.valorantguidestats.util.Constants.Companion.GlideImageLoader

class AgentsAdapter(val onItemClick: (agent: Agent) -> Unit) :
    ListAdapter<Agent, AgentsAdapter.AgentsViewHolder>(
        AgentDiffUtil()
    ) {

    inner class AgentsViewHolder(private val binding: RowLayoutAgentsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            val agent = currentList[position]
            with(binding) {
                with(agent) {
                    GlideImageLoader(
                        root.context,
                        displayIcon,
                        binding.carouselImageView
                    )

                    tvAgentName.text = displayName
                    root.setOnClickListener {
                        onItemClick(this)
                    }

                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AgentsViewHolder {
        val binding =
            RowLayoutAgentsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AgentsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return currentList.size
    }

    override fun onBindViewHolder(holder: AgentsViewHolder, position: Int) {
        holder.bind(position)
    }

    class AgentDiffUtil : DiffUtil.ItemCallback<Agent>() {
        override fun areItemsTheSame(oldItem: Agent, newItem: Agent): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: Agent, newItem: Agent): Boolean {
            return oldItem == newItem
        }
    }
}

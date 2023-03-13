package com.aayar94.valorantguidestats.ui.fragment.agents

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.aayar94.valorantguidestats.data.models.Agent
import com.aayar94.valorantguidestats.databinding.RowLayoutAgentFragmentBinding

class AgentsFragmentAdapter(
    val onItemClick: (agent: Agent) -> Unit
) :
    RecyclerView.Adapter<AgentsFragmentAdapter.AgentsFragmentViewHolder>() {

    private var agentsList: MutableList<Agent> = mutableListOf()

    inner class AgentsFragmentViewHolder(private val binding: RowLayoutAgentFragmentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            with(binding) {
                binding.agentImage.transitionName = "agent_small_image_$position"
                agentImage.load(agentsList[position].displayIcon) {
                    crossfade(true)
                }
                agentName.text = agentsList[position].displayName
                binding.root.setOnClickListener {
                    onItemClick(agentsList[position])
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
        return agentsList.size
    }

    override fun onBindViewHolder(holder: AgentsFragmentViewHolder, position: Int) {
        holder.bind(position)
    }

    fun setData(list: Array<Agent>?) {
        if (list != null) {
            agentsList.clear()
            for (i in list.indices) {
                if (list[i].uuid != "ded3520f-4264-bfed-162d-b080e2abccf9") {
                    agentsList.add(list[i])
                }
            }
        }
        this.notifyDataSetChanged()
    }

}
package com.aayar94.valorantguidestats.ui.fragment.agents

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.aayar94.valorantguidestats.R
import com.aayar94.valorantguidestats.data.models.game_content.Agent
import com.aayar94.valorantguidestats.databinding.RowLayoutAgentFragmentBinding
import com.aayar94.valorantguidestats.util.Constants.Companion.GlideImageLoader

class AgentsFragmentAdapter(
    val onItemClick: (agent: Agent) -> Unit
) :
    RecyclerView.Adapter<AgentsFragmentAdapter.AgentsFragmentViewHolder>() {

    private var agentsList: MutableList<Agent> = mutableListOf()

    inner class AgentsFragmentViewHolder(private val binding: RowLayoutAgentFragmentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int) {
            with(binding) {
                GlideImageLoader(binding.root.context,agentsList[position].displayIcon,agentImage)
                agentName.text = agentsList[position].displayName
                root.setOnClickListener {
                    onItemClick(agentsList[position])
                }
                root.animation=AnimationUtils.loadAnimation(root.context, R.anim.recycler_view_item_falldown_anim)
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
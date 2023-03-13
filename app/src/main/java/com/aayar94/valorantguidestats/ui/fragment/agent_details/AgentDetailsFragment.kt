package com.aayar94.valorantguidestats.ui.fragment.agent_details

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import coil.load
import com.aayar94.valorantguidestats.databinding.FragmentAgentDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AgentDetailsFragment : Fragment() {
    private var _binding: FragmentAgentDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: AgentDetailsFragmentArgs by navArgs()
    val fragments = ArrayList<Fragment>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAgentDetailsBinding.inflate(layoutInflater, container, false)
        setFragments()
        setAgentDetails()
        return binding.root
    }

    private fun setAgentDetails() {

        val title = ArrayList<String>()
        title.add(args.agent.abilities?.get(0)?.displayName.toString())
        title.add(args.agent.abilities?.get(1)?.displayName.toString())
        title.add(args.agent.abilities?.get(2)?.displayName.toString())
        title.add(args.agent.abilities?.get(3)?.displayName.toString())

        val iconsList = ArrayList<Uri?>()
        iconsList.add(args.agent.abilities?.get(0)?.displayIcon?.toUri())
        iconsList.add(args.agent.abilities?.get(1)?.displayIcon?.toUri())
        iconsList.add(args.agent.abilities?.get(2)?.displayIcon?.toUri())
        iconsList.add(args.agent.abilities?.get(3)?.displayIcon?.toUri())

        val adapter = SkillViewPagerAdapter(
            fragments, title, childFragmentManager
        )
        with(binding) {
            ivAgent.load(args.agent.fullPortrait)
            skillImageView.load(args.agent.role.displayIcon)
            agentRoleText.text = args.agent.role.displayName
            tvAgentName.text = args.agent.displayName
            tvDesc.text = args.agent.description
            skillsViewpager.adapter = adapter
            skillsTabBar.setupWithViewPager(skillsViewpager)

            //val inputStream = requireActivity().contentResolver.openInputStream(iconsList[0]!!)
            //val drawable = Drawable.createFromStream(inputStream, iconsList[0].toString())
            //skillsTabBar.getTabAt(0)?.setIcon(drawable)
        }
    }

    private fun setFragments() {
        fragments.add(
            SkillPageFragment(
                args.agent.abilities?.get(0)?.displayName.toString(),
                args.agent.abilities?.get(0)?.description.toString()
            )
        )
        fragments.add(
            SkillPageFragment(
                args.agent.abilities?.get(1)?.displayName.toString(),
                args.agent.abilities?.get(1)?.description.toString()
            )
        )
        fragments.add(
            SkillPageFragment(
                args.agent.abilities?.get(2)?.displayName.toString(),
                args.agent.abilities?.get(2)?.description.toString()
            )
        )
        fragments.add(
            SkillPageFragment(
                args.agent.abilities?.get(3)?.displayName.toString(),
                args.agent.abilities?.get(3)?.description.toString()
            )
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}
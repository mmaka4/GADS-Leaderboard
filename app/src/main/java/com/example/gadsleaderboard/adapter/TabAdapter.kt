package com.example.gadsleaderboard.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.gadsleaderboard.fragments.LearningFragment
import com.example.gadsleaderboard.fragments.SkillFragment

@Suppress("DEPRECATION")
class TabAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {

        return when (position) {
            0 -> LearningFragment()
            1 -> SkillFragment()
            else -> throw IllegalStateException("position $position is invalid for this viewpager")
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        return when (position) {
            0 -> "Learning Leaders"
            1 -> "Skill IQ Leaders"
            else -> throw IllegalStateException("position $position is invalid for this viewpager")
            }
        }
}

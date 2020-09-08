package com.example.gadsleaderboard

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

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
            0 -> "First Tab"
            1 -> "Second Tab"
            else -> throw IllegalStateException("position $position is invalid for this viewpager")
            }
        }
}

package com.example.gadsleaderboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.gadsleaderboard.adapter.LearnerAdapter
import com.example.gadsleaderboard.model.Learner

class SkillFragment : Fragment() {

    private lateinit var rootView: View
    lateinit var recyclerView: RecyclerView
    lateinit var learnerAdapter: LearnerAdapter
    lateinit var fragmentContext: Context
    lateinit var learnersList: ArrayList<Learner>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_skilliq, container, false)
        fragmentContext = this.context!!

        return rootView
    }
}
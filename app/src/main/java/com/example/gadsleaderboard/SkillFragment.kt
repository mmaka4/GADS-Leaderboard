package com.example.gadsleaderboard

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.gadsleaderboard.adapter.LearnerAdapter
import com.example.gadsleaderboard.adapter.SkillAdapter
import com.example.gadsleaderboard.api.NetworkClient
import com.example.gadsleaderboard.api.ServerApi
import com.example.gadsleaderboard.model.Learner
import com.example.gadsleaderboard.model.Skill
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_learning.*
import kotlinx.android.synthetic.main.fragment_learning.shimmer_frame2
import kotlinx.android.synthetic.main.fragment_skilliq.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SkillFragment : Fragment() {

    private lateinit var rootView: View
    lateinit var recyclerView: RecyclerView
    lateinit var skillRefresher: SwipeRefreshLayout
    lateinit var skillAdapter: SkillAdapter
    lateinit var fragmentContext: Context
    lateinit var skillsList: ArrayList<Skill>
    private var listInitialized = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_skilliq, container, false)
        fragmentContext = this.context!!
        initializeRecyclerView()
        initSwipeToRefresh()

        return rootView
    }

    private fun initSwipeToRefresh() {
        //** Set the colors of the Pull To Refresh View
        skillRefresher = rootView.findViewById(R.id.skillSwipeRefresher)
        skillRefresher.setProgressBackgroundColorSchemeColor(
            ContextCompat.getColor(
                fragmentContext,
                R.color.colorPrimary
            )
        )
        skillRefresher.setColorSchemeColors(Color.WHITE)

        skillRefresher.setOnRefreshListener {
            if (listInitialized)
                skillsList.clear()

            loadSkills()
            skillRefresher.isRefreshing = false
        }
    }

    private fun initializeRecyclerView() {
        recyclerView = rootView.findViewById(R.id.skill_recycler)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        loadSkills()
    }

    private fun loadSkills() {

        val api = NetworkClient.getRetrofitClient()?.create(ServerApi::class.java)

        val call = api?.getSkills()

        call?.enqueue(object : Callback<ArrayList<Skill>> {
            val gson = Gson()

            override fun onResponse(
                call: Call<ArrayList<Skill>>,
                response: Response<ArrayList<Skill>>
            ) {
                if (response.isSuccessful) {
                    Log.i("ResponseString : Skill", gson.toJson(response.body()))

                    listInitialized = true
                    skillsList = response.body()!!
                    Log.i("ResponseString : Skill ", skillsList[1].name!!)

                    shimmer_frame2.stopShimmer()
                    shimmer_frame2.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE

                    skillAdapter =
                        SkillAdapter(
                            skillsList,
                            fragmentContext
                        )

                    skillAdapter.notifyDataSetChanged()

                    recyclerView.adapter = skillAdapter

                } else {
                    Log.i("ResponseString : Skill", "Not Successfully!!")
                }
            }

            override fun onFailure(call: Call<ArrayList<Skill>>, t: Throwable) {
                Log.i("ResponseFailure: Skill", t.message!!)
            }

        })

    }
}
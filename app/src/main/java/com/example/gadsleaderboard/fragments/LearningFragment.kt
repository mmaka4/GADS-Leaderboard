package com.example.gadsleaderboard.fragments

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
import com.example.gadsleaderboard.R
import com.example.gadsleaderboard.adapter.LearnerAdapter
import com.example.gadsleaderboard.api.NetworkClient
import com.example.gadsleaderboard.api.ServerApi
import com.example.gadsleaderboard.model.Learner
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_learning.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LearningFragment : Fragment() {

    private lateinit var rootView: View
    lateinit var recyclerView: RecyclerView
    lateinit var learnRefresher: SwipeRefreshLayout
    lateinit var learnerAdapter: LearnerAdapter
    lateinit var fragmentContext: Context
    lateinit var learnersList: ArrayList<Learner>
    private var listInitialized = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_learning, container, false)
        fragmentContext = this.context!!
        initializeRecyclerView()
        swipeToRefresh()

        return rootView
    }

    private fun swipeToRefresh() {
        //** Set the colors of the Pull To Refresh View
        learnRefresher = rootView.findViewById(R.id.learnSwipeRefresher)
        learnRefresher.setProgressBackgroundColorSchemeColor(
            ContextCompat.getColor(
                fragmentContext,
                R.color.colorPrimary
            )
        )
        learnRefresher.setColorSchemeColors(Color.WHITE)

        learnRefresher.setOnRefreshListener {
            if (listInitialized)
                learnersList.clear()

            loadLearners()
            learnRefresher.isRefreshing = false
        }
    }

    private fun initializeRecyclerView() {
        recyclerView = rootView.findViewById(R.id.learning_recycler)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        loadLearners()
    }

    private fun loadLearners() {

        val api = NetworkClient.getRetrofitClient()?.create(ServerApi::class.java)

        val call = api?.getLearners()

        call?.enqueue(object : Callback<ArrayList<Learner>> {
        val gson = Gson()

            override fun onResponse(
                call: Call<ArrayList<Learner>>,
                response: Response<ArrayList<Learner>>
            ) {
                if (response.isSuccessful) {
                    Log.i("ResponseString : Learn ", gson.toJson(response.body()))

                    learnersList = response.body()!!
                    Log.i("ResponseString : Learn ", learnersList[1].name!!)

                    shimmer_frame2.stopShimmer()
                    shimmer_frame2.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE

                    learnerAdapter =
                        LearnerAdapter(
                            learnersList,
                            fragmentContext
                        )

                    learnerAdapter.notifyDataSetChanged()

                    recyclerView.adapter = learnerAdapter

                } else {
                    Log.i("ResponseString : Learn ", "Not Successfully!!")
                }
            }

            override fun onFailure(call: Call<ArrayList<Learner>>, t: Throwable) {
                Log.i("ResponseFailure: Learn ", t.message!!)
            }

        })

    }
}
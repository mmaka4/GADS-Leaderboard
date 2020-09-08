package com.example.gadsleaderboard

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gadsleaderboard.adapter.LearnerAdapter
import com.example.gadsleaderboard.api.NetworkClient
import com.example.gadsleaderboard.api.ServerApi
import com.example.gadsleaderboard.model.Learner
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_learning.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class LearningFragment : Fragment() {

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
        rootView = inflater.inflate(R.layout.fragment_learning, container, false)
        fragmentContext = this.context!!
        initializeRecyclerView()
        return rootView
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
                    Log.i("ResponseString", gson.toJson(response.body()))

                    learnersList = response.body()!!
                    Log.i("ResponseString", learnersList[1].name!!)

//                    shimmer_frame2.stopShimmer()
//                    shimmer_frame2.visibility = View.GONE
//                    listFruitscyclerView.visibility = View.VISIBLE

                    learnerAdapter =
                        LearnerAdapter(
                            learnersList,
                            fragmentContext
                        )

                    learnerAdapter.notifyDataSetChanged()

                    learning_recycler.adapter = learnerAdapter

                } else {
                    Log.i("ResponseString", "Not Successfully!!")
                }
            }

            override fun onFailure(call: Call<ArrayList<Learner>>, t: Throwable) {
                Log.i("ResponseFailure1", t.message!!)
            }

        })

    }
}
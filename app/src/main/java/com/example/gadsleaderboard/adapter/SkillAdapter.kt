package com.example.gadsleaderboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gadsleaderboard.R
import com.example.gadsleaderboard.api.NetworkClient
import com.example.gadsleaderboard.model.Skill
import kotlinx.android.synthetic.main.learning_item.view.*

class SkillAdapter(private val skillList: ArrayList<Skill>, private val context: Context) :
    RecyclerView.Adapter<SkillAdapter.FoodViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view: View = layoutInflater.inflate(R.layout.skill_iq_item, parent, false)
        return FoodViewHolder(view)
    }

    override fun getItemCount(): Int {
        return skillList.size
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.name.text = skillList[position].name
        val hoursAndCountry =
            skillList[position].score.toString() + " skill IQ Score, " + skillList[position].country.toString()
        holder.hours.text = hoursAndCountry
//        Picasso.get().load(context.resources.getString(NetworkClient.BASE_URL)+foodList[position].image).into(holder.image)
    }

    inner class FoodViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.learner_name
        val hours: TextView = itemView.learner_hours
    }
}
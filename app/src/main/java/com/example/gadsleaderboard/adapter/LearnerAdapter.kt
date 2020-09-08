package com.example.gadsleaderboard.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gadsleaderboard.R
import com.example.gadsleaderboard.model.Learner
import kotlinx.android.synthetic.main.learning_item.view.*

class LearnerAdapter (private val learnerList:ArrayList<Learner>, private val context: Context): RecyclerView.Adapter<LearnerAdapter.FoodViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val view:View = layoutInflater.inflate(R.layout.learning_item,parent,false)
        return FoodViewHolder(view)
    }

    override fun getItemCount(): Int {
        return learnerList.size
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        holder.name.text = learnerList[position].name
        val hoursAndCountry = learnerList[position].hours.toString()+ " learning hours, "+ learnerList[position].country.toString()+ "."
        holder.hours.text = hoursAndCountry
        //Picasso.get().load(context.resources.getString(R.string.imageFruitsURL)+foodList[position].image).into(holder.image)
    }

    inner class FoodViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name: TextView = itemView.learner_name
        val hours: TextView = itemView.learner_hours
    }
}
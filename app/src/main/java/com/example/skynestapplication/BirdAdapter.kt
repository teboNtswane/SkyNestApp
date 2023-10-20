package com.example.skynestapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BirdAdapter(val birdType: MutableList<BirdType>): RecyclerView.Adapter<PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardview, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int {
        return birdType.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        return holder.bindView(birdType[position])
    }
}

class PostViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

    private val tvBirdName: TextView = itemView.findViewById(R.id.tvBirdName)
    private val tvSciName: TextView = itemView.findViewById(R.id.tvSciName)

    fun bindView(birdType: BirdType){
        tvBirdName.text = birdType.comName
        tvSciName.text = birdType.sciName
    }
}
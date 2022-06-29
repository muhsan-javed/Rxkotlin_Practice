package com.muhsanapps.rxkotlin_practice.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.muhsanapps.rxkotlin_practice.R
import com.muhsanapps.rxkotlin_practice.models.Food

class FoodAdapter(private val context:Context, private var foodList: ArrayList<Food>) : RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        return  FoodViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.each_row,parent,false))
    }


    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val food = foodList[position]
        holder.name.text=food.title
        holder.price.text=food.id.toString()
        Glide.with(context)
            .load(food.url)
            .into(holder.image)
    }

    fun setData(foodList: ArrayList<Food>) {
        this.foodList= foodList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int  = foodList.size

    inner class FoodViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.findViewById(R.id.itemName)
        val price : TextView = itemView.findViewById(R.id.itemPrcie)
        val image : ImageView = itemView.findViewById(R.id.itemImageView)
    }
}
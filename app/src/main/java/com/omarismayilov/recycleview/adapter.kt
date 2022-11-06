package com.omarismayilov.recycleview

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.omarismayilov.recycleview.databinding.RowItemBinding

class adapter(private var context: Context, private var planetList:ArrayList<modelItem>): RecyclerView.Adapter<adapter.ItemHolder>() {

    private lateinit var binding : RowItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        binding = RowItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return ItemHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        //get data
        val model = planetList[position]

        val title = model.title
        val description = model.description
        val image = model.image

        //set data
        holder.titleTv.text = title
        holder.descriptionTv.text = description
        holder.iconIv.setImageResource(image)

        //clicked
        holder.itemView.setOnClickListener {
            val intent = Intent(context,DetailActivity::class.java)
            intent.putExtra("planets",planetList)
            intent.putExtra("position",position)
            context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return planetList.size
    }









    inner class ItemHolder(itemView: View) : ViewHolder(itemView){
         var titleTv : TextView
         var descriptionTv : TextView
         var iconIv : ImageView

        init{
            binding = RowItemBinding.bind(itemView)

            titleTv = binding.planettitleTv
            descriptionTv = binding.planetdescriptionTv
            iconIv = binding.planetimageIv
        }
    }



}
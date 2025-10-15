package com.datingapp.app.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.datingapp.app.data.Match
import com.datingapp.app.databinding.ItemMatchBinding

class MatchAdapter(private val matches: List<Match>) :
    RecyclerView.Adapter<MatchAdapter.MatchViewHolder>() {

    inner class MatchViewHolder(val binding: ItemMatchBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        val binding = ItemMatchBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        val match = matches[position]
        holder.binding.ivProfile.setImageResource(match.profileImage)
        holder.binding.tvNameAgeHeight.text = "${match.name}, ${match.age}, ${match.height}"
    }

    override fun getItemCount(): Int = matches.size
}

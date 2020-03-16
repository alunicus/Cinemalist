package com.github.alunicus.cinemalist.feature.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.alunicus.cinemalist.data.Cast
import com.github.alunicus.cinemalist.databinding.ItemCastBinding
import com.github.alunicus.cinemalist.extensions.loadCircleImage

class CastAdapter : RecyclerView.Adapter<CastAdapter.ViewHolder>() {
    private var _binding: ItemCastBinding? = null
    private val binding get() = _binding!!

    private val items = mutableListOf<Cast>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        _binding = ItemCastBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.apply {
            val person = items[adapterPosition]

            name.text = person.name
            photo.loadCircleImage(person.profilePath)
        }
    }

    fun setItems(list: List<Cast>) {
        items.clear()
        items.addAll(list)

        notifyDataSetChanged()
    }

    class ViewHolder(binding: ItemCastBinding) : RecyclerView.ViewHolder(binding.root) {
        val photo = binding.castPhoto
        val name = binding.castName
    }
}
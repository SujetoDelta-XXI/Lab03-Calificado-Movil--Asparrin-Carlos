package com.asparrin.carlos.laboratoriocalificado03.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.asparrin.carlos.laboratoriocalificado03.data.model.Teacher
import com.asparrin.carlos.laboratoriocalificado03.databinding.ItemTeacherBinding

class TeacherAdapter(
    private var list: List<Teacher>
) : RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder>() {

    inner class TeacherViewHolder(private val binding: ItemTeacherBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(teacher: Teacher) = with(binding) {
            tvFirstName.text = teacher.firstName
            tvLastName.text  = teacher.lastName
            Glide.with(imgPhoto.context)
                .load(teacher.photoUrl)
                .into(imgPhoto)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        val binding = ItemTeacherBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TeacherViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    /** Actualiza internamente la lista y refresca el RecyclerView */
    fun updateData(newList: List<Teacher>) {
        list = newList
        notifyDataSetChanged()
    }
}

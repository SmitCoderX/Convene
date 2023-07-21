package com.smitcoderx.convene.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.smitcoderx.convene.Model.EducationDataModel
import com.smitcoderx.convene.R
import com.smitcoderx.convene.databinding.LayoutEducationItemBinding

class EducationAdapter : RecyclerView.Adapter<EducationAdapter.EducationViewHolder>() {

    inner class EducationViewHolder(val binding: LayoutEducationItemBinding) :
        RecyclerView.ViewHolder(
            binding.root
        )

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EducationViewHolder {
        val binding =
            LayoutEducationItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EducationViewHolder(binding)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: EducationViewHolder, position: Int) {
        val currentItem = differ.currentList[position]

        currentItem?.let { item ->
            holder.binding.let {
                Glide.with(it.ivSchoolImg.context).load(item.schoolImg)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(it.ivSchoolImg)

                it.tvSchoolName.text = item.schoolName
                it.tvCourseName.text =
                    "${item.degree} - ${item.fieldOfStudy}"
                it.tvDuration.text = "${item.startData} - ${item.endDate}"
                it.tvDesc.text = item.description
                if (item.skills.isEmpty()) {
                    it.tvSkills.visibility = View.GONE
                } else {
                    it.tvSkills.visibility = View.GONE
                }

                if (item.grade.isEmpty()) {
                    it.tvGrade.visibility = View.GONE
                } else {
                    it.tvGrade.visibility = View.GONE
                }

                if (item.activities.isEmpty()) {
                    it.tvAands.visibility = View.GONE
                } else {
                    it.tvAands.visibility = View.GONE
                }

                if (position == differ.currentList.size - 1) {
                    it.view.visibility = View.GONE
                } else {
                    it.view.visibility = View.VISIBLE
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private val differCallback = object : DiffUtil.ItemCallback<EducationDataModel>() {
        override fun areItemsTheSame(
            oldItem: EducationDataModel,
            newItem: EducationDataModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: EducationDataModel,
            newItem: EducationDataModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}
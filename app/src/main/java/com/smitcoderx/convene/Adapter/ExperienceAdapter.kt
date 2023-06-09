package com.smitcoderx.convene.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.smitcoderx.convene.Model.ExperienceDataModel
import com.smitcoderx.convene.databinding.LayoutExperienceItemBinding

class ExperienceAdapter() : RecyclerView.Adapter<ExperienceAdapter.ExperienceViewHolder>() {

    inner class ExperienceViewHolder(val binding: LayoutExperienceItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExperienceViewHolder {
        val binding =
            LayoutExperienceItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExperienceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ExperienceViewHolder, position: Int) {
        val currentItem = differ.currentList[position]

        if (currentItem != null) {
            holder.binding.let {
                Glide.with(it.ivCompanyImg.context).load(currentItem.companyImg)
                    .into(it.ivCompanyImg)

                it.tvJobPost.text = currentItem.jobPost
                it.tvCompanyName.text = currentItem.companyName
                it.tvDuration.text = "${currentItem.startTime} - ${currentItem.endTime}"
                it.tvDesc.text = currentItem.description
                if(currentItem.skills.isNullOrEmpty()) {
                    it.tvSkills.visibility = View.GONE
                } else {
                    it.tvSkills.visibility = View.VISIBLE
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

    private val differCallback = object : DiffUtil.ItemCallback<ExperienceDataModel>() {
        override fun areItemsTheSame(
            oldItem: ExperienceDataModel, newItem: ExperienceDataModel
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ExperienceDataModel, newItem: ExperienceDataModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}
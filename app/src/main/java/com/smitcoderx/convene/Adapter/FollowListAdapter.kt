package com.smitcoderx.convene.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.net.toUri
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.smitcoderx.convene.Ui.Login.Models.LoginData
import com.smitcoderx.convene.databinding.LayoutFollowItemBinding

class FollowListAdapter : RecyclerView.Adapter<FollowListAdapter.FollowListViewHolder>() {

    inner class FollowListViewHolder(val binding: LayoutFollowItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowListViewHolder {
        val binding =
            LayoutFollowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FollowListViewHolder, position: Int) {
        val currentItem = differ.currentList[position]

        if (currentItem != null) {
            val url = currentItem.photoUrl.toUri().buildUpon().scheme("https").build()
            holder.binding.let {
                Glide.with(it.ivImage.context)
                    .load(url)
                    .override(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                    .into(it.ivImage)

                it.tvUserName.text = currentItem.displayName
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private val differCallback = object : DiffUtil.ItemCallback<LoginData>() {
        override fun areItemsTheSame(oldItem: LoginData, newItem: LoginData): Boolean {
            return oldItem.uid == newItem.uid
        }

        override fun areContentsTheSame(oldItem: LoginData, newItem: LoginData): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)
}
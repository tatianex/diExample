package com.proway.diexample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.proway.diexample.R
import com.proway.diexample.databinding.OneUserBinding
import com.proway.diexample.model.User

class GithubUserAdapter: RecyclerView.Adapter<GithubOneUserViewHolder>() {

    private var listOfUser = mutableListOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GithubOneUserViewHolder {

        LayoutInflater.from(parent.context).inflate(R.layout.one_user, parent, false).apply {
            return GithubOneUserViewHolder(this)
        }
    }

    override fun onBindViewHolder(holder: GithubOneUserViewHolder, position: Int) {
        listOfUser[position].let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int = listOfUser.size

    fun refresh(newList: List<User>) {
        listOfUser.addAll(newList)
        notifyDataSetChanged()
    }
}

class GithubOneUserViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val binding = OneUserBinding.bind(view)

    fun bind(model: User) {
        binding.idTextView.text = model.login
        Glide.with(binding.root)
            .load(model.avatar)
            .into(binding.avatarImageView)
    }
}
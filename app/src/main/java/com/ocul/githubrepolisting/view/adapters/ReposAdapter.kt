package com.ocul.githubrepolisting.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ocul.githubrepolisting.databinding.ListItemRepoBinding
import com.ocul.githubrepolisting.model.RepoItem
import com.ocul.githubrepolisting.repository.PrefRepository
import com.ocul.githubrepolisting.view.adapters.viewHoders.ReposViewHolder

class ReposAdapter() : RecyclerView.Adapter<ReposViewHolder>(){

    var repoList: List<RepoItem> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReposViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val dataBinding = ListItemRepoBinding.inflate(inflater, parent, false)
        val prefRepository: PrefRepository=PrefRepository(parent.context)
        return ReposViewHolder(dataBinding,prefRepository)
    }

    override fun getItemCount() = repoList.size

    override fun onBindViewHolder(holder: ReposViewHolder, position: Int) {
        holder.setup(repoList[position])
    }

    fun updateRepoList(repoList: List<RepoItem>) {
        this.repoList = repoList
        notifyDataSetChanged()
    }
}
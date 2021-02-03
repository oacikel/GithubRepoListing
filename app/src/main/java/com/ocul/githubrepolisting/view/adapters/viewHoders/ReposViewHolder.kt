package com.ocul.githubrepolisting.view.adapters.viewHoders

import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ocul.githubrepolisting.BR
import com.ocul.githubrepolisting.R
import com.ocul.githubrepolisting.model.RepoItem
import com.ocul.githubrepolisting.repository.PrefRepository
import kotlinx.android.synthetic.main.list_item_repo.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class ReposViewHolder constructor(
    private val dataBinding: ViewDataBinding,
    private val prefRepository:PrefRepository
) : RecyclerView.ViewHolder(dataBinding.root) {
    private val LOG_TAG="OCULCAN - ReposViewHolder"
    val repoName = itemView.textViewRepoName
    val imageFavourite =itemView.imageButtonFavourite
    fun setup(repoItemData: RepoItem) {
        Log.d(LOG_TAG,"Favourited item count is: "+prefRepository.getFavouriteIds()?.size)
        prefRepository.getFavouriteIds()?.forEach { Log.d(LOG_TAG,"id name: "+it) }
        if(prefRepository.getFavouriteIds()?.contains(repoItemData.id.toString()) == true) {
            Log.i(LOG_TAG,"Repo with id "+repoItemData.id+" is within favourites list. A star icon will be added")
            imageFavourite.visibility = View.VISIBLE
        }else{
            Log.i(LOG_TAG,"Repo with id "+repoItemData.id+" is NOT within favourites list. A star icon will be removed")
            imageFavourite.visibility = View.INVISIBLE
        }
        dataBinding.setVariable(BR.repoItemData, repoItemData)
        dataBinding.executePendingBindings()
        repoName.setText(repoItemData.name)

        itemView.onClick {
            val bundle = bundleOf(
                "id" to repoItemData.id,
                "starCount" to repoItemData.stargazers_count,
                "openIssueCount" to repoItemData.open_issues_count,
                "ownerName" to repoItemData.owner.login,
                "avatar_url" to repoItemData.owner.avatar_url,
                "repoName" to repoItemData.name
            )

            itemView.findNavController()
                .navigate(R.id.action_homeFragment_to_repoDetailFragment, bundle)
        }
    }
}
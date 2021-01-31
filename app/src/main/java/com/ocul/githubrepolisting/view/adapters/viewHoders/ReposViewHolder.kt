package com.ocul.githubrepolisting.view.adapters.viewHoders

import androidx.core.os.bundleOf
import androidx.databinding.ViewDataBinding
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.ocul.githubrepolisting.BR
import com.ocul.githubrepolisting.R
import com.ocul.githubrepolisting.model.RepoItem
import com.ocul.githubrepolisting.view.fragments.home.HomeFragmentViewModel
import kotlinx.android.synthetic.main.list_item_repo.view.*
import org.jetbrains.anko.sdk27.coroutines.onClick

class ReposViewHolder constructor(
    private val dataBinding: ViewDataBinding,
) : RecyclerView.ViewHolder(dataBinding.root) {

    val repoName = itemView.textViewRepoName
    fun setup(repoItemData: RepoItem) {
        dataBinding.setVariable(BR._all, repoItemData)
        dataBinding.executePendingBindings()
        repoName.setText(repoItemData.name)

        itemView.onClick {
            val bundle = bundleOf(
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
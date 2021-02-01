package com.ocul.githubrepolisting.view.fragments.repoDetail

import android.app.Application
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.ocul.githubrepolisting.model.Owner
import com.ocul.githubrepolisting.model.RepoItem
import com.ocul.githubrepolisting.repository.PrefRepository
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_repo_detail.view.*

class RepoDetailFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val LOG_TAG="OCULCAN - RepoDetailFragmentViewModel"
        private val prefRepository:PrefRepository= PrefRepository(application)

    private lateinit var selectedRepo: RepoItem
    fun setup(arguments: Bundle, view: View, toolbar: Toolbar?){
        Log.d(LOG_TAG,"Executing setup function.")
        val id = arguments.let { RepoDetailFragmentArgs.fromBundle(it).id }
        val starCount = arguments.let { RepoDetailFragmentArgs.fromBundle(it).starCount }
        val openIssueCount = arguments.let { RepoDetailFragmentArgs.fromBundle(it).openIssueCount }
        val ownerName = arguments.let { RepoDetailFragmentArgs.fromBundle(it).ownerName }
        val avatar_url = arguments.let { RepoDetailFragmentArgs.fromBundle(it).avatarUrl }
        val repoName = arguments.let { RepoDetailFragmentArgs.fromBundle(it).repoName }
        selectedRepo = RepoItem(id,repoName, Owner(ownerName, avatar_url), starCount, openIssueCount)

        view.textViewOwner.setText(selectedRepo.owner.login)
        view.textViewOpenIssueCount.setText(selectedRepo.open_issues_count.toString())
        view.textViewStarCount.setText(selectedRepo.stargazers_count.toString())
        toolbar?.setTitle(selectedRepo.name)
        Picasso.get().load(selectedRepo.owner.avatar_url).into(view.imageViewAvatar);

    }

    fun changeRepoSavedStatus() {
        Log.d(LOG_TAG,"Executing changeRepoSavedStatus.")
        prefRepository.handleFavouriteId(selectedRepo.id.toString())
        prefRepository.getFavouriteIds()?.forEach { Log.d(LOG_TAG,it) }
    }


}
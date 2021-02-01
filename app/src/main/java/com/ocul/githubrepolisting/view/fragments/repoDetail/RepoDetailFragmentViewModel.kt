package com.ocul.githubrepolisting.view.fragments.repoDetail

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModel
import com.ocul.githubrepolisting.model.Owner
import com.ocul.githubrepolisting.model.RepoItem
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_repo_detail.view.*

class RepoDetailFragmentViewModel : ViewModel() {
    private val LOG_TAG="OCULCAN - RepoDetailFragmentViewModel"

    fun setup(arguments: Bundle, view: View, toolbar: Toolbar?){
        Log.d(LOG_TAG,"Executing setup function.")
        val starCount = arguments.let { RepoDetailFragmentArgs.fromBundle(it).starCount }
        val openIssueCount = arguments.let { RepoDetailFragmentArgs.fromBundle(it).openIssueCount }
        val ownerName = arguments.let { RepoDetailFragmentArgs.fromBundle(it).ownerName }
        val avatar_url = arguments.let { RepoDetailFragmentArgs.fromBundle(it).avatarUrl }
        val repoName = arguments.let { RepoDetailFragmentArgs.fromBundle(it).repoName }
        val selectedRepo = RepoItem(repoName, Owner(ownerName, avatar_url), starCount, openIssueCount)

        view.textViewOwner.setText(selectedRepo.owner.login)
        view.textViewOpenIssueCount.setText(selectedRepo.open_issues_count.toString())
        view.textViewStarCount.setText(selectedRepo.stargazers_count.toString())
        toolbar?.setTitle(selectedRepo.name)
        Picasso.get().load(selectedRepo.owner.avatar_url).into(view.imageViewAvatar);
    }
}
package com.ocul.githubrepolisting.view.fragments.repoDetail

import androidx.lifecycle.ViewModel
import com.ocul.githubrepolisting.BR
import com.ocul.githubrepolisting.databinding.FragmentRepoDetailBinding
import com.ocul.githubrepolisting.model.RepoItem

class RepoDetailFragmentViewModel : ViewModel() {
    private val LOG_TAG="OCULCAN - RepoDetailFragmentViewModel"

    fun setup(repoItem:RepoItem, binding: FragmentRepoDetailBinding){
        binding.setVariable(BR._all,repoItem)
    }
}
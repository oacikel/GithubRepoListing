package com.ocul.githubrepolisting.view.fragments.repoDetail

import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.ocul.githubrepolisting.BR
import com.ocul.githubrepolisting.R
import com.ocul.githubrepolisting.databinding.FragmentRepoDetailBinding
import com.ocul.githubrepolisting.databinding.ListItemRepoBinding
import com.ocul.githubrepolisting.model.Owner
import com.ocul.githubrepolisting.model.RepoItem
import com.ocul.githubrepolisting.view.fragments.home.HomeFragmentViewModel
import kotlinx.android.synthetic.main.fragment_repo_detail.*
import org.jetbrains.anko.sdk27.coroutines.onClick


class RepoDetailFragment : Fragment() {
    private lateinit var viewModel: RepoDetailFragmentViewModel
    private lateinit var selectedRepo: RepoItem
    private lateinit var binding: FragmentRepoDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            FragmentRepoDetailBinding.inflate(inflater, container, false).apply {
                viewModel =
                    ViewModelProviders.of(this@RepoDetailFragment)
                        .get(RepoDetailFragmentViewModel::class.java)
                setLifecycleOwner(viewLifecycleOwner)
            }

        return inflater.inflate(R.layout.fragment_repo_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

        val starCount = arguments?.let { RepoDetailFragmentArgs.fromBundle(it).starCount }
        val openIssueCount = arguments?.let { RepoDetailFragmentArgs.fromBundle(it).openIssueCount }
        val ownerName = arguments?.let { RepoDetailFragmentArgs.fromBundle(it).ownerName }
        val avatar_url = arguments?.let { RepoDetailFragmentArgs.fromBundle(it).avatarUrl }
        val repoName = arguments?.let { RepoDetailFragmentArgs.fromBundle(it).repoName }
        if (repoName != null && starCount != null && openIssueCount != null && ownerName != null && avatar_url != null) {
            selectedRepo =
                RepoItem(repoName, Owner(ownerName, avatar_url), starCount, openIssueCount)
        }
            binding.setVariable(BR.selectedRepo, selectedRepo)
    }
}
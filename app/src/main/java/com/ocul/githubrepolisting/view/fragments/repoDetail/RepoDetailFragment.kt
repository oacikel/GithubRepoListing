package com.ocul.githubrepolisting.view.fragments.repoDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.ocul.githubrepolisting.R
import com.ocul.githubrepolisting.databinding.FragmentRepoDetailBinding
import com.ocul.githubrepolisting.model.RepoItem
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_repo_detail.*


class RepoDetailFragment : Fragment() {
    private lateinit var viewModel: RepoDetailFragmentViewModel
    private lateinit var selectedRepos: RepoItem
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
        arguments?.let {
            viewModel.setup(it, view, activity?.toolbar)
        }
    }
}
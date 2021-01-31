package com.ocul.githubrepolisting.view.fragments.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ocul.githubrepolisting.databinding.FragmentHomeBinding
import com.ocul.githubrepolisting.util.Constants.Companion.DEBUG
import com.ocul.githubrepolisting.view.adapters.ReposAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import java.lang.Exception


class HomeFragment : Fragment() {
    private lateinit var viewDataBinding: FragmentHomeBinding
    private lateinit var adapter: ReposAdapter
    private lateinit var viewModel: HomeFragmentViewModel
    private val LOG_TAG = "OCULCAN - HomeFragment"


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        try {
            viewDataBinding = FragmentHomeBinding.inflate(inflater, container, false).apply {
                viewModel =
                    ViewModelProviders.of(this@HomeFragment).get(HomeFragmentViewModel::class.java)
                setLifecycleOwner(viewLifecycleOwner)
            }
        } catch (e: Exception) {
            Log.d(LOG_TAG, e.message)
        }
        return viewDataBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            setupAdapter()
            setupObservers()
            buttonSubmit.onClick {
                viewModel.fetchRepos(textInputUserName?.text.toString())
            }
        } catch (e: Exception) {
            Log.d(LOG_TAG, e.message)
        }
    }

    private fun setupObservers() {
        viewModel.liveDataRepos.observe(viewLifecycleOwner, Observer {
            adapter.updateRepoList(it)
            Log.d(LOG_TAG, "Updating Repo List")
        })
        viewDataBinding.viewmodel?.liveDataRepos?.observe(viewLifecycleOwner, Observer {
            adapter.updateRepoList(it)
            Log.d(LOG_TAG, "Updating Repo List")
        })
    }


    private fun setupAdapter() {

        adapter = ReposAdapter()
        val layoutManager = LinearLayoutManager(activity)
        recyclerViewReposList.layoutManager = layoutManager
        recyclerViewReposList.addItemDecoration(
            DividerItemDecoration(
                activity,
                layoutManager.orientation
            )
        )
        recyclerViewReposList.adapter = adapter
    }
}
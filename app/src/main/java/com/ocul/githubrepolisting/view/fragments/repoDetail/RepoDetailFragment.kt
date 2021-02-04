package com.ocul.githubrepolisting.view.fragments.repoDetail

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.ocul.githubrepolisting.R
import com.ocul.githubrepolisting.databinding.FragmentRepoDetailBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.fragment_repo_detail.*
import org.jetbrains.anko.appcompat.v7.coroutines.onMenuItemClick


class RepoDetailFragment : Fragment() {
    private val LOG_TAG = "OCULCAN - RepoDetailFragment"
    private lateinit var viewModel: RepoDetailFragmentViewModel
    private lateinit var binding: FragmentRepoDetailBinding
    private lateinit var menu:Menu
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        binding=FragmentRepoDetailBinding.inflate(inflater,container,false).apply {
            viewModel=ViewModelProvider(this@RepoDetailFragment)
                .get(RepoDetailFragmentViewModel::class.java)
            setLifecycleOwner(viewLifecycleOwner)
        }

        val view =binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            viewModel.setupFragment(it, view, (parentFragment?.activity as AppCompatActivity).toolbar,binding)
        }
        Log.d(LOG_TAG,"Menu has visible items (before item click): "+activity?.toolbar?.menu?.hasVisibleItems())

        activity?.toolbar?.onMenuItemClick {
            Log.d(LOG_TAG,"Menu has visible items: "+activity?.toolbar?.menu?.hasVisibleItems())
            when (it?.itemId) {
                R.id.action_favorite -> {
                    viewModel.changeRepoSavedStatus()
                    viewModel.changeMenuIcon(it)
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        this.menu =menu
        arguments?.let {
            viewModel.setupMenuItems(menu,this)
        }
    }
}
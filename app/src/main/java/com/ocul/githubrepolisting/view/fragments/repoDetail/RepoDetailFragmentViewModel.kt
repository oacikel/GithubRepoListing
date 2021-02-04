package com.ocul.githubrepolisting.view.fragments.repoDetail

import android.app.Application
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.AndroidViewModel
import com.ocul.githubrepolisting.R
import com.ocul.githubrepolisting.databinding.FragmentRepoDetailBinding
import com.ocul.githubrepolisting.model.Owner
import com.ocul.githubrepolisting.model.RepoItem
import com.ocul.githubrepolisting.repository.PrefRepository
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_repo_detail.view.*
import kotlin.coroutines.coroutineContext

class RepoDetailFragmentViewModel(application: Application) : AndroidViewModel(application) {
    private val LOG_TAG = "OCULCAN - RepoDetailFragmentViewModel"
    private val prefRepository: PrefRepository = PrefRepository(application)
    private lateinit var drawbleFavourited: Drawable
    private lateinit var drawbleNotFavourited: Drawable


    private lateinit var selectedRepo: RepoItem
    fun setupMenuItems(menu: Menu, repoDetailFragment: RepoDetailFragment) {
        val view: View? =repoDetailFragment.view
        drawbleFavourited = view?.context!!.resources.getDrawable(R.drawable.icon_favorite,view.context!!.theme)
        drawbleNotFavourited = view?.context!!.resources.getDrawable(R.drawable.icon_favourite_empty,view.context!!.theme)
        if (prefRepository.getFavouriteIds()?.contains(selectedRepo.id.toString()) == true) {
            Log.d(LOG_TAG, "Changing logo to full star.")
           menu.findItem(R.id.action_favorite )?.icon=drawbleFavourited
        } else {
            Log.d(LOG_TAG, "Changing logo to empty star.")
            menu.findItem(R.id.action_favorite )?.icon=drawbleNotFavourited
        }
    }
    fun setupFragment(arguments: Bundle, view: View, toolbar: Toolbar?,binding: FragmentRepoDetailBinding) {
        Log.d(LOG_TAG, "Executing setup function.")
        val id = arguments.let { RepoDetailFragmentArgs.fromBundle(it).id }
        val starCount = arguments.let { RepoDetailFragmentArgs.fromBundle(it).starCount }
        val openIssueCount = arguments.let { RepoDetailFragmentArgs.fromBundle(it).openIssueCount }
        val ownerName = arguments.let { RepoDetailFragmentArgs.fromBundle(it).ownerName }
        val avatar_url = arguments.let { RepoDetailFragmentArgs.fromBundle(it).avatarUrl }
        val repoName = arguments.let { RepoDetailFragmentArgs.fromBundle(it).repoName }
        selectedRepo =
            RepoItem(id, repoName, Owner(ownerName, avatar_url), starCount, openIssueCount)
        binding.selectedRepo=selectedRepo
        toolbar?.setTitle(selectedRepo.name)
        Picasso.get().load(selectedRepo.owner.avatar_url).into(view.imageViewAvatar);
    }

    fun changeRepoSavedStatus() {
        Log.d(LOG_TAG, "Executing changeRepoSavedStatus.")
        prefRepository.handleFavouriteId(selectedRepo.id.toString())
        prefRepository.getFavouriteIds()?.forEach { Log.d(LOG_TAG, it) }
    }

    fun changeMenuIcon(item: MenuItem) {
        when (item.icon) {
            drawbleFavourited -> {
                item.icon=drawbleNotFavourited
            }
            drawbleNotFavourited -> {
                item.icon=drawbleFavourited
            }
        }
    }


}
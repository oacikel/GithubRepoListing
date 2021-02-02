package com.ocul.githubrepolisting.view.fragments.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ocul.githubrepolisting.model.RepoItem
import com.ocul.githubrepolisting.repository.Repository

class HomeFragmentViewModel : ViewModel() {
    private val LOG_TAG="OCULCAN - HomeFragmentViewModel"
    val liveDataRepos = MutableLiveData<List<RepoItem>>()
    val empty = MutableLiveData<Boolean>().apply { value = false }

    val loading = MutableLiveData<Boolean>().apply { value = false }

    fun fetchRepos(userName:String) {
        loading.value = true
        Log.d(LOG_TAG, "Lodaing")
        Repository.getInstance().getRepoList(userName) { isSuccess, response ->
            loading.value = false
            Log.d(LOG_TAG, "Loding complete")
            if (isSuccess && response?.size!=0) {
                liveDataRepos.value = response
                Log.d(LOG_TAG, "Retrieved a list of repos. Count: "+liveDataRepos.value?.size)
                empty.value = false
            } else {
                empty.value = true
                Log.d(LOG_TAG, "List is empty")
            }
        }
    }
}
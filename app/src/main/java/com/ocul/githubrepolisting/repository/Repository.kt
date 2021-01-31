package com.ocul.githubrepolisting.repository
import android.util.Log
import com.ocul.githubrepolisting.model.RepoItem
import com.ocul.githubrepolisting.model.api.Client
import retrofit2.Call
import retrofit2.Callback

class Repository {
    private val LOG_TAG = "OCULCAN - Repository"
    companion object {
        private var INSTANCE: Repository? = null
        fun getInstance() = INSTANCE
            ?: Repository().also {
                INSTANCE = it
            }
    }


    fun getRepoList(username:String,onResult: (isSuccess: Boolean, response: List<RepoItem>?) -> Unit) {
        Log.i(LOG_TAG,"Receiving response...")
        Client.instance.getRepos(username).enqueue(object : Callback<List<RepoItem>> {

            override fun onResponse(
                call: Call<List<RepoItem>>,
                response: retrofit2.Response<List<RepoItem>>
            ) {
                Log.i(LOG_TAG,"Received response...")
                if (response != null && response.isSuccessful){
                    Log.d(LOG_TAG,"Response received successfully.")
                    onResult(true, response.body()!!)
                }
                else{
                    Log.e(LOG_TAG,"Error receiving response.")
                    onResult(false, null)
                }
            }

            override fun onFailure(call: Call<List<RepoItem>>, t: Throwable) {
                Log.e(LOG_TAG,"Error receiving response: "+t.message)
                onResult(false, null)
            }

        })
    }
}
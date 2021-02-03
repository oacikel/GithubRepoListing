package com.ocul.githubrepolisting.repository

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.ocul.githubrepolisting.util.Constants.Companion.PREFERENCE_NAME
import com.ocul.githubrepolisting.util.Constants.Companion.PREF_FAVOURITE_REPOS
import com.ocul.githubrepolisting.util.Constants.Companion.PREF_LOGGED_IN
import com.ocul.githubrepolisting.util.Constants.Companion.PREF_MINIMUM_APP_VERSION
import com.ocul.githubrepolisting.util.Constants.Companion.PREF_SHARE_MESSAGE

class PrefRepository(val context: Context) {

    private val LOG_TAG="OCULCAN - Prefrepo"
    private val pref: SharedPreferences =
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)
    private val editor = pref.edit()
    private val gson = Gson()

    private fun String.put(long: Long) {
        editor.putLong(this, long)
        editor.commit()
    }

    private fun String.put(int: Int) {
        editor.putInt(this, int)
        editor.commit()
    }

    private fun String.put(string: String) {
        editor.putString(this, string)
        editor.commit()
    }

    private fun String.put(set: Set<String>) {
        editor.clear()
        editor.putStringSet(this, set)
        editor.commit()
    }

    private fun String.put(boolean: Boolean) {
        editor.putBoolean(this, boolean)
        editor.commit()
    }

    private fun String.getLong() = pref.getLong(this, 0)

    private fun String.getInt() = pref.getInt(this, 0)

    private fun String.getString() = pref.getString(this, "")!!

    private fun String.getBoolean() = pref.getBoolean(this, false)

    private fun String.getStringSet() = pref.getStringSet(this, null)

    fun setLoggedIn(isLoggedIn: Boolean) {
        PREF_LOGGED_IN.put(isLoggedIn)
    }

    fun setLoggedIn() = PREF_LOGGED_IN.getBoolean()

    fun setShareMsg(msg: String) {
        PREF_SHARE_MESSAGE.put(msg)
    }

    fun getShareMsg() = PREF_SHARE_MESSAGE.getString()

    fun setMinimumAppVersion(version: Long) {
        PREF_MINIMUM_APP_VERSION.put(version)
    }

    fun getMinimumAppVersion() = PREF_MINIMUM_APP_VERSION.getLong()

    private fun addNewIdSet(set: Set<String>) {
        PREF_FAVOURITE_REPOS.put(set)
    }

    fun getFavouriteIds() = PREF_FAVOURITE_REPOS.getStringSet()

    fun handleFavouriteId(id: String) {
        //first retrieve existing list
        val set: MutableSet<String>
        if (getFavouriteIds() == null) {
            set = mutableSetOf("")
        } else {
            set = getFavouriteIds() as MutableSet<String>
        }
        //check if current list contains the id
        if (set.contains(id)) {
            Log.d(LOG_TAG,"Removing id "+id+" from favourites.")
            //remove the id if it does
            set.removeIf { it == id }
        } else {
            set.add(id)
        }
        set.remove("")
        Log.d(LOG_TAG,"Set contents: "+set.toString())
        PREF_FAVOURITE_REPOS.put(set)
    }

    fun clearData() {
        editor.clear()
        editor.commit()
    }

}
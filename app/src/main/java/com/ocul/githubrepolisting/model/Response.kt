package com.ocul.githubrepolisting.model

import java.io.Serializable


data class RepoItem(
    val name: String,
    val owner: Owner,
    val stargazers_count: Int,
    val open_issues_count: Int,
)

data class Owner(
    val login: String,
    val avatar_url: String,
)
package com.datingapp.app.data

sealed class HomeItem {
    data class Banner(val images: List<Int>) : HomeItem()
    data class SectionHeader(val title: String, val count: Int, val type: SectionType) : HomeItem()
    data class MatchList(val matches: List<Match>) : HomeItem()
    data class LargeBanner(val imageRes: Int) : HomeItem()
}

enum class SectionType { ALL_MATCHES, NEW_MATCHES }

data class Match(
    val name: String,
    val age: Int,
    val height: Int,
    val profileImage: Int // drawable resource
)


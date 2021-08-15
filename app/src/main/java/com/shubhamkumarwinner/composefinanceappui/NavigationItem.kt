package com.shubhamkumarwinner.composefinanceappui

sealed class NavigationItem(var route: String, var icon: Int, var title: String) {
    object Main : NavigationItem("main", R.drawable.ic_home, "Main")
    object Cards : NavigationItem("cards", R.drawable.ic_card, "Cards")
    object Support : NavigationItem("support", R.drawable.ic_support, "Support")
    object More : NavigationItem("more", R.drawable.ic_more, "More")
}

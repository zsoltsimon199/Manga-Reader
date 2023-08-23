package com.zs.mangareader.core.utils

import com.zs.mangareader.R

sealed class BottomNavItems(var icon: Int,var title : String, var route: String) {

    data object LIBRARY: BottomNavItems(icon = R.drawable.ic_library, title = "Library", route =  Routes.LIBRARY )
    data object UPDATES: BottomNavItems(icon = R.drawable.ic_updates, title = "Updates", route =  Routes.UPDATES )
    data object HISTORY: BottomNavItems(icon = R.drawable.ic_history, title = "History", route =  Routes.HISTORY )
    data object BROWSE: BottomNavItems(icon = R.drawable.ic_browse, title = "Browse", route =  Routes.BROWSE )
    data object MORE: BottomNavItems(icon = R.drawable.ic_more, title = "More", route =  Routes.MORE )

}

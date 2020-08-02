package com.dmitrymalkovich.android.popularmoviesapp.screens

import android.view.View
import com.agoda.kakao.common.views.KView
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KTextView
import com.dmitrymalkovich.android.popularmoviesapp.R
import org.hamcrest.Matcher

object MainScreen : Screen<MainScreen>() {
    val actionMenu = KView { withId(R.id.menu_sort_by) }
    val recyclerMainScreen: KRecyclerView = KRecyclerView({
        withId(R.id.movie_list)
    }, itemTypeBuilder = {
        itemType(::Item)
    })

    class Item(parent: Matcher<View>) : KRecyclerItem<Item>(parent) {
        val title = KTextView(parent) { withId(R.id.title) }
    }
}

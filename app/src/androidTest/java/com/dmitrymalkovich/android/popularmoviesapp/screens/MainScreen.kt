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
    val titleApp = KTextView { withText("Popular Movies") }
    val actionMenu = KView { withContentDescription(R.string.sort_by) }
    val mostPopular = KView { withText(R.string.most_popular) }
    val topRated = KView { withText(R.string.top_rated) }
    val favorites = KView { withText(R.string.favorites) }

    val recyclerMainScreen: KRecyclerView = KRecyclerView({
        withId(R.id.movie_list)
    }, itemTypeBuilder = {
        itemType(::Item)
    })

    class Item(parent: Matcher<View>) : KRecyclerItem<Item>(parent)
}

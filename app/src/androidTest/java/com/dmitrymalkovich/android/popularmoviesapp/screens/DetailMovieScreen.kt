package com.dmitrymalkovich.android.popularmoviesapp.screens

import android.view.View
import com.agoda.kakao.common.views.KView
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import com.dmitrymalkovich.android.popularmoviesapp.R
import org.hamcrest.Matcher

class DetailMovieScreen : Screen<DetailMovieScreen>() {
    val favoriteBotton = KButton { withId(R.id.button_mark_as_favorite) }
    val removeFavoriteBotton = KButton { withId(R.id.button_mark_as_favorite) }
    val watchButton = KButton { withId(R.id.button_watch_trailer) }
    val trailerThumbnail = KView { withId(R.id.trailer_thumbnail) }

    val recyclerTrailer: KRecyclerView = KRecyclerView({
        withId(R.id.trailer_list)
    }, itemTypeBuilder = {
        itemType(::Item)
    })

    class Item(parent: Matcher<View>) : KRecyclerItem<Item>(parent) {
        val title = KTextView(parent) { withId(R.id.title) }
    }
}
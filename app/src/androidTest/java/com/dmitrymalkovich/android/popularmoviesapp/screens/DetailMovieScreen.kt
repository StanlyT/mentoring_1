package com.dmitrymalkovich.android.popularmoviesapp.screens

import android.view.View
import com.agoda.kakao.common.views.KView
import com.agoda.kakao.image.KImageView
import com.agoda.kakao.recycler.KRecyclerItem
import com.agoda.kakao.recycler.KRecyclerView
import com.agoda.kakao.screen.Screen
import com.agoda.kakao.text.KButton
import com.agoda.kakao.text.KTextView
import com.dmitrymalkovich.android.popularmoviesapp.R
import org.hamcrest.Matcher

object DetailMovieScreen : Screen<DetailMovieScreen>() {
    val favoriteBotton = KButton { withId(R.id.button_mark_as_favorite) }
    val upButton = KButton { withContentDescription("Перейти вверх") }
    val removeFavoriteButton = KButton { withId(R.id.button_remove_from_favorites) }
    val watchTrailerButton = KButton { withId(R.id.button_watch_trailer) }
    val trailerThumbnail = KView { withId(R.id.trailer_thumbnail) }
    val movieBackDrop = KImageView { withId(R.id.movie_backdrop) }
    val moviePoster = KImageView { withId(R.id.movie_poster) }
    val shareWithButton = KImageView { withContentDescription("Поделиться с помощью") }
    val movieTitle = KTextView { withId(R.id.movie_title) }
    val movieRating = KView { withId(R.id.movie_rating) }
    val movieUserRating = KTextView { withId(R.id.movie_user_rating) }
    val dateRelease = KTextView { withId(R.id.movie_release_date) }
    val movieOverview = KTextView { withId(R.id.movie_overview) }


    val trailerRecycler: KRecyclerView = KRecyclerView({
        withId(R.id.trailer_list)
    }, itemTypeBuilder = {
        itemType(MainScreen::Item)
    })


    val recyclerTrailer: KRecyclerView = KRecyclerView({
        withId(R.id.trailer_list)
    }, itemTypeBuilder = {
        itemType(::Item)
    })

    class Item(parent: Matcher<View>) : KRecyclerItem<Item>(parent) {
        val title = KTextView(parent) { withId(R.id.title) }
    }
}
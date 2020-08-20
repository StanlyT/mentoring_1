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
    val favoriteButton = KButton { withId(R.id.button_mark_as_favorite) }

    //val upButton = KButton { withContentDescription("Перейти вверх") }
    val upButton = KButton { withContentDescription("Navigate up") }
    val removeFavoriteButton = KButton { withId(R.id.button_remove_from_favorites) }
    val watchTrailerButton = KButton { withId(R.id.button_watch_trailer) }
    val trailerThumbnail = KView { withId(R.id.trailer_thumbnail) }
    val movieBackDrop = KImageView { withId(R.id.movie_backdrop) }

    //val moviePoster = KImageView { withId(R.id.movie_poster) }
    val moviePoster = KImageView { withId(R.id.movie_poster) }
    val shareWithButton = KImageView { withContentDescription("Share with") }
    val movieTitle = KTextView { withId(R.id.movie_title) }
    val movieRating = KView { withId(R.id.movie_rating) }
    val movieUserRating = KTextView { withId(R.id.movie_user_rating) }
    val dateRelease = KTextView { withId(R.id.movie_release_date) }
    val movieOverview = KTextView { withId(R.id.movie_overview) }

    val trailerRecycler: KRecyclerView = KRecyclerView({
        withId(R.id.trailer_list)
    }, itemTypeBuilder = {
        itemType(::TrailerItem)
    })

    class TrailerItem(parent: Matcher<View>) : KRecyclerItem<TrailerItem>(parent)

    fun isScreenDisplayed() {
        movieBackDrop {
            isDisplayed()
        }
        favoriteButton {
            isDisplayed()
        }
        shareWithButton {
            isDisplayed()
        }
        movieRating {
            isDisplayed()
        }
        moviePoster {
            isDisplayed()
        }
        movieTitle {
            isDisplayed()
        }
        watchTrailerButton {
            isDisplayed()
        }
        movieUserRating {
            isDisplayed()
        }
        dateRelease {
            isDisplayed()
        }
        movieOverview {
            isDisplayed()
        }
        trailerRecycler {
            isDisplayed()
        }
        upButton {
            isDisplayed()
            click()
        }
    }
}
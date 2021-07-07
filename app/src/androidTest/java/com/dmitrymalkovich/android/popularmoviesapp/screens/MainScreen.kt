package com.dmitrymalkovich.android.popularmoviesapp.screens

import android.view.View
import com.agoda.kakao.common.views.KView
import com.agoda.kakao.image.KImageView
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
    val appbar = KView { withId(R.id.app_bar) }
    val emptyStateConnection = KTextView { withId(R.id.empty_state_container) }
    val textEmptyView = KTextView { withText("Possible internet connection is missing") }
    val emptyImage = KImageView { withDrawable(R.drawable.ic_movie_creation_black_124dp) }

    val recyclerMainScreen: KRecyclerView = KRecyclerView({
        withId(R.id.movie_list)
    }, itemTypeBuilder = {
        itemType(::MainItem)
    })

    class MainItem(parent: Matcher<View>) : KRecyclerItem<MainItem>(parent)

    fun isScreenDisplayed() {
        appbar {
            isDisplayed()
        }
        titleApp {
            isDisplayed()
        }
        recyclerMainScreen {
            isDisplayed()
        }
        actionMenu {
            isDisplayed()
        }
    }

    fun isActionMenuItemDisplayed(){
        mostPopular {
            isDisplayed()
        }
        topRated {
            isDisplayed()
        }
        favorites {
            isDisplayed()
        }
    }
}

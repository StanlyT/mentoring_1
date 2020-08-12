package com.dmitrymalkovich.android.popularmoviesapp


import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.dmitrymalkovich.android.popularmoviesapp.screens.DetailMovieScreen
import com.dmitrymalkovich.android.popularmoviesapp.screens.MainScreen
import com.dmitrymalkovich.android.popularmoviesapp.screens.MainScreen.Item
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class AppScreensTest : TestCase() {

    @Rule
    @JvmField
    var rule = ActivityTestRule(MovieListActivity::class.java, true)

    @Test
    fun actionMenuTest() {
        MainScreen {
            titleApp {
                isDisplayed()
            }
            actionMenu {
                isDisplayed()
                click()
            }
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

    @Test
    fun mainScreenTest() {
        MainScreen {
            recyclerMainScreen {
                isDisplayed()
            }
        }
    }

    @Test
    fun thumbnailTest() {
        MainScreen {
            recyclerMainScreen {
                scrollTo(0)
                childAt<Item>(0) {
                    isDisplayed()
                    click()
                    detailMovieScreenTest()
                }
                scrollTo(7)
                childAt<Item>(5) {
                    isDisplayed()
                    click()
                    detailMovieScreenTest()
                }
            }
        }
    }

    private fun detailMovieScreenTest() {
        DetailMovieScreen {
            movieBackDrop {
                isDisplayed()
            }
            favoriteBotton {
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
}





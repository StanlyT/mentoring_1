package com.dmitrymalkovich.android.popularmoviesapp


import androidx.appcompat.widget.ShareActionProvider
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import com.agoda.kakao.intent.KIntent
import com.dmitrymalkovich.android.popularmoviesapp.screens.DetailMovieScreen
import com.dmitrymalkovich.android.popularmoviesapp.screens.MainScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class AppScreensTest : TestCase() {

    private val uiDevice = UiDevice.getInstance(getInstrumentation())

    @Before
    fun wakeUpDevice() {
        uiDevice.wakeUp()
    }

    @Rule
    @JvmField
    var rule = IntentsTestRule(MovieListActivity::class.java, true)

    @Test
    fun mainScreenTest() {
        MainScreen {
            isScreenDisplayed()

            recyclerMainScreen {
                firstChild<MainScreen.MainItem> {
                    isDisplayed()
                    click()
                    detailMovieScreenTest()
                }
                swipeUp()
                scrollTo(5)
                childAt<MainScreen.MainItem>(5) {
                    isDisplayed()
                    click()
                    detailMovieScreenTest()
                }
            }
        }
    }

    private fun detailMovieScreenTest() {
        DetailMovieScreen {
            isScreenDisplayed()
        }
    }

    @Test
    fun testFirstChildAndBack() { // пункт 2.1
        MainScreen {
            recyclerMainScreen {
                firstChild<MainScreen.MainItem> {
                    click()
                    DetailMovieScreen {
                        upButton {
                            click()
                        }
                    }
                }
            }
        }
    }

    @Test
    fun testIsFavoriteButtonClickable() { // пункт 2.2
        MainScreen {
            recyclerMainScreen {
                childAt<MainScreen.MainItem>(3) {
                    click()
                    DetailMovieScreen {
                        favoriteButton {
                            click()
                        }
                        removeFavoriteButton {
                            isDisplayed()
                            click()
                        }
                    }
                }
            }
        }
    }

    @Test
    fun testWatchTrailer() {  // пункт 2.3
        MainScreen {
            recyclerMainScreen {
                swipeUp()
                scrollTo(6)
                childAt<MainScreen.MainItem>(6) {
                    click()
                    DetailMovieScreen {
                        watchTrailerButton {
                            click()
                            KIntent {
                                hasAction("http://www.youtube.com/watch?v=")
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    fun testShareVariants() { // пункт 2.4
        MainScreen {
            recyclerMainScreen {
                swipeUp()
                scrollToEnd()
                lastChild<MainScreen.MainItem> {
                    click()

                    DetailMovieScreen {
                        shareWithButton {
                            click()

                            KIntent {
                                hasComponent(ShareActionProvider::class.java.name)
                            }
                        }
                    }
                }
            }
        }
    }

    @Test
    fun testAddToFavorites() { // пункт 2.5
        var title = ""
        MainScreen {

            recyclerMainScreen {
                swipeUp()
                scrollTo(5)
                childAt<MainScreen.MainItem>(5) {
                    click()
                    DetailMovieScreen {
                        title = movieTitle.toString()

                        favoriteButton {
                            click()
                        }
                        removeFavoriteButton {
                            isDisplayed()
                        }
                    }
                    DetailMovieScreen {
                        upButton {
                            click()
                        }
                    }
                }
                swipeDown()
            }
            actionMenu {
                click()
            }
            favorites {
                click()
            }
            recyclerMainScreen {
                firstChild<MainScreen.MainItem> {
                    click()
                    DetailMovieScreen {
                        title == movieTitle.toString()
                        removeFavoriteButton {
                            click()
                        }
                        favoriteButton {
                            isDisplayed()
                        }
                        upButton {
                            click()
                        }
                    }
                }
                getSize() == 0
            }
        }
    }

    @Test
    fun testOffline() {  // пункт 2.5
        toggleAirMode()

        with(rule) {
            finishActivity()
            launchActivity(null)
        }

        MainScreen {
            titleApp {
                isDisplayed()
            }
            emptyImage {
                isDisplayed()
            }
            emptyStateConnection {
                isDisplayed()
            }
            textEmptyView {
                isDisplayed()
            }
        }
        toggleAirMode()
    }

    private fun toggleAirMode() {
        uiDevice.openQuickSettings()
        uiDevice.findObject(By.desc("Airplane mode")).click()
        uiDevice.pressBack()
        uiDevice.pressBack()
        uiDevice.pressBack()
    }

    @Test
    fun testSavedFavOffline() {
        // пункт 2.5
        var title = ""
        MainScreen {

            recyclerMainScreen {
                firstChild<MainScreen.MainItem> {
                    click()
                    DetailMovieScreen {
                        title = movieTitle.toString()

                        favoriteButton {
                            click()
                        }
                    }
                }
            }
        }
        toggleAirMode()

        with(rule) {
            finishActivity()
            launchActivity(null)
        }

        MainScreen {
            actionMenu {
                click()
            }
            favorites {
                click()
            }
            recyclerMainScreen {
                firstChild<MainScreen.MainItem> {
                    click()
                    DetailMovieScreen {
                        title == movieTitle.toString()
                        removeFavoriteButton {
                            click()
                        }
                        upButton {
                            click()
                        }
                    }
                }
                getSize() == 0
            }
        }
        toggleAirMode()
    }
}





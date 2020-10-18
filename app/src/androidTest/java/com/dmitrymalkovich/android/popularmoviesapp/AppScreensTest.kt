package com.dmitrymalkovich.android.popularmoviesapp


//import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiSelector
import com.dmitrymalkovich.android.popularmoviesapp.screens.DetailMovieScreen
import com.dmitrymalkovich.android.popularmoviesapp.screens.MainScreen
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.After
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
        device.network.enable()
//        with(rule) {
//            launchActivity(null)
//        }
    }

    @After
    fun closeApp() {
        with(rule) {
            finishActivity()
        }
    }

    @Rule
    @JvmField
    var rule = ActivityTestRule(MovieListActivity::class.java, true)

    @Test
    fun should_show_main_screen_and_detail_screen_after_pressed_some_items() {
        MainScreen {

            isScreenDisplayed()

            actionMenu {
                click()
            }

            isActionMenuItemDisplayed()

            mostPopular {
                click()
            }

            recyclerMainScreen {
                firstChild<MainScreen.MainItem> {
                    isDisplayed()
                    click()
                }
            }
        }

        testDisplayDetailMovieScreenAndReturnBack()

        MainScreen {
            recyclerMainScreen {
               // swipeUp()
                scrollTo(5)
                childAt<MainScreen.MainItem>(5) {
                    isDisplayed()
                    click()
                }
            }
        }
return
        testDisplayDetailMovieScreenAndReturnBack()

    }

    private fun testDisplayDetailMovieScreenAndReturnBack() {
        DetailMovieScreen {
            isScreenDisplayed()
            upButton {
                click()
            }
        }
    }

    @Test
    fun should_show_previous_screen_if_up_button_was_pressed() { // пункт 2.1
        device.network.disable()
        MainScreen {
            recyclerMainScreen {
                firstChild<MainScreen.MainItem> {
                    click()
                }
            }
        }

        testDisplayDetailMovieScreenAndReturnBack()

        MainScreen {
            isScreenDisplayed()
        }
    }

    @Test
    fun add_to_favorite_button_should_change_its_state_on_click() { // пункт 2.2
        MainScreen {
            recyclerMainScreen {
                childAt<MainScreen.MainItem>(3) {
                    click()
                }
            }
        }
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

    @Test
    fun should_display_trailer_on_youtube() {  // пункт 2.3
        val trailerImage = uiDevice.findObject(UiSelector().resourceId("com.google.android.youtube:id/player_overlays"))
        MainScreen {
            recyclerMainScreen {
                //swipeUp()

                scrollTo(6)
                childAt<MainScreen.MainItem>(6) {
                    click()
                }
            }
        }
        DetailMovieScreen {
            watchTrailerButton {
                click()
            }
        }
    }

    @Test
    fun should_display_chooser_listview() { // пункт 2.4
        val appChooser = uiDevice.findObject(UiSelector().descriptionContains("Choose an app"))
        MainScreen {
            recyclerMainScreen {
               swipeUp()
                scrollToEnd()
                lastChild<MainScreen.MainItem> {
                    click()
                }
            }
        }
        DetailMovieScreen {
            shareWithButton {
                click()
                appChooser.exists()
            }
        }
    }

    @Test
    fun should_add_movie_to_favorites_and_then_remove_it_from_there() { // пункт 2.5
        var title = ""
        MainScreen {
            recyclerMainScreen {
               // swipeUp()
                scrollTo(5)
                childAt<MainScreen.MainItem>(5) {
                    click()
                }
            }
        }
        DetailMovieScreen {
            title = movieTitle.toString()

            favoriteButton {
                click()
            }
            removeFavoriteButton {
                isDisplayed()
            }
            upButton {
                click()
            }
        }
        MainScreen {
            recyclerMainScreen {
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
                }
            }
        }
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
        MainScreen {
            recyclerMainScreen {
                getSize() == 0
            }
        }
    }
}





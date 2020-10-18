package com.dmitrymalkovich.android.popularmoviesapp

//import androidx.test.espresso.intent.rule.IntentsTestRule
//import androidx.test.espresso.intent.rule.IntentsTestRule
//import org.junit.rules.TestRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
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

class OfflineAppScreensTest : TestCase() {

    private val uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    @Before
    fun wakeUpDevice() {
        uiDevice.wakeUp()
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
    fun should_display_offline_mode_screen() {  // пункт 2.5
        enableAirMode()

        restartApp()

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
        disableAirMode()
    }

    @Test
    fun should_display_favorite_list_of_movies_in_offline_mode() {
        // пункт 2.5
        var title = ""
        MainScreen {
            recyclerMainScreen {
                firstChild<MainScreen.MainItem> {
                    click()
                }
            }
        }

        DetailMovieScreen {
            title = movieTitle.toString()
            favoriteButton {
                click()
            }
        }

        enableAirMode()

        restartApp()

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
                }
            }
        }
        DetailMovieScreen {
            title == movieTitle.toString()
            removeFavoriteButton {
                click()
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

        disableAirMode()
    }

    private fun restartApp() {
        with(rule) {
            finishActivity()
            launchActivity(null)
        }
    }

    private fun enableAirMode() {
        uiDevice.openQuickSettings()

        val airmodeButton = uiDevice.findObject(By.desc("Airplane mode"))
        if (!airmodeButton.isEnabled)
            airmodeButton.click()

        repeat(3) {
            uiDevice.pressBack()
        }
    }

    private fun disableAirMode() {
        uiDevice.openQuickSettings()

        val airmodeButton = uiDevice.findObject(By.desc("Airplane mode"))
        if (airmodeButton.isEnabled)
            airmodeButton.click()

        repeat(3) {
            uiDevice.pressBack()
        }
    }
}
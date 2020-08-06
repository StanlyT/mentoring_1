package com.dmitrymalkovich.android.popularmoviesapp


import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.dmitrymalkovich.android.popularmoviesapp.screens.MainScreen
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
    fun mainScreenTest() {
        MainScreen {
            recyclerMainScreen.isDisplayed()
        }
    }
}
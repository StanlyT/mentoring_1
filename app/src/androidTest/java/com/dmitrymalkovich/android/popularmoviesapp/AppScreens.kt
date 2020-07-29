package com.dmitrymalkovich.android.popularmoviesapp


import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import org.junit.Test
import org.junit.runner.RunWith
import com.dmitrymalkovich.android.popularmoviesapp.screens.MainScreen


@RunWith(AndroidJUnit4::class)
@LargeTest
class AppScreens : TestCase(){

    @Test
    fun mainScreenTest(){
        val mainScreen = MainScreen()
        mainScreen{
            recyclerMainScreen{
                isVisible()

        }
            actionMenu{
            isClickable()}
        }

    }
}
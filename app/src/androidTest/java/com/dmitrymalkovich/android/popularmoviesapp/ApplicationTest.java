package com.dmitrymalkovich.android.popularmoviesapp;

import android.app.Activity;
import androidx.test.rule.ActivityTestRule;
//import android.support.*

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ActivityTestRule<Activity> {
    public ApplicationTest() {
        super(Activity.class);
    }
}
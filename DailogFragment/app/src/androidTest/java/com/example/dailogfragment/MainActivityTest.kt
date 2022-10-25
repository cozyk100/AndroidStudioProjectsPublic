package com.example.dailogfragment

import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

     private lateinit var decorView: View

    @get:Rule
    var activityScenario  = activityScenarioRule<MainActivity>()

    @Before
    fun setup() {
        activityScenario.getScenario().onActivity(object : ActivityScenario.ActivityAction<MainActivity> {
            override fun perform(activity: MainActivity) {
                decorView = activity.getWindow().getDecorView()
            }
        })
    }

    @Test
    fun basicStartTest() {
        onView(withId(R.id.basic)).perform(click())
        onView(withText("Start Game?")).check(matches(isDisplayed()))
        onView(withText("start")).check(matches(isDisplayed()))
        onView(withText("cancel")).check(matches(isDisplayed()))
        onView(withText("N/A")).check(matches(isDisplayed()))

        onView(withText("start")).perform(click())
        onView(withText("Start Game?")).check(doesNotExist())

        // TODO Toastのassert、何故かここでしばらく待ちになる。
        // androidx.test.espresso.NoMatchingRootException
        onView(withText("start押しましたね"))
            .inRoot(withDecorView(not(decorView)))
            .check(matches(isDisplayed()));
    }
}
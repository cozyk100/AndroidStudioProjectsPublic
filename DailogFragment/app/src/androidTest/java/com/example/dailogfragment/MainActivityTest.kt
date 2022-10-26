package com.example.dailogfragment

import android.view.View
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
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

    /**
     * BasicDialogFragment Startボタン
     */
    @Test
    fun basicStartTest() {
        onView(withId(R.id.basic)).perform(click())
        onView(withText("Start Game?")).check(matches(isDisplayed()))
        onView(withText("start")).check(matches(isDisplayed()))
        onView(withText("cancel")).check(matches(isDisplayed()))
        onView(withText("N/A")).check(matches(isDisplayed()))

        onView(withText("start")).perform(click())
        onView(withText("Start Game?")).check(doesNotExist())

        // TODO Toastのassertはまだうまく機能しないらしい・・・
        // https://stackoverflow.com/questions/67771360/android-espresso-toast-message-assertions-not-working-with-sdk-30
//        onView(withText("start押しましたね"))
//            .inRoot(withDecorView(not(decorView)))
//            .check(matches(isDisplayed()));
    }

    /**
     * BasicDialogFragment Cancelボタン
     */
    @Test
    fun basicCancelTest() {
        onView(withId(R.id.basic)).perform(click())
        onView(withText("Start Game?")).check(matches(isDisplayed()))
        onView(withText("start")).check(matches(isDisplayed()))
        onView(withText("cancel")).check(matches(isDisplayed()))
        onView(withText("N/A")).check(matches(isDisplayed()))

        onView(withText("cancel")).perform(click())
        onView(withText("Start Game?")).check(doesNotExist())
    }

    /**
     * BasicDialogFragment N/Aボタン
     */
    @Test
    fun basicNATest() {
        onView(withId(R.id.basic)).perform(click())
        onView(withText("Start Game?")).check(matches(isDisplayed()))
        onView(withText("start")).check(matches(isDisplayed()))
        onView(withText("cancel")).check(matches(isDisplayed()))
        onView(withText("N/A")).check(matches(isDisplayed()))

        onView(withText("N/A")).perform(click())
        onView(withText("Start Game?")).check(doesNotExist())
    }

    /**
     * CheckBoxDialogFragment
     */
    @Test
    fun checkBoxTest() {
        onView(withId(R.id.checkbox)).perform(click())
        onView(withText("トッピング選んでください")).check(matches(isDisplayed()))
        onView(withText("OK")).check(matches(isDisplayed()))
        onView(withText("Cancel")).check(matches(isDisplayed()))

        onView(withText("オニオン")).perform(click())
        onView(withText("レタス")).perform(click())
        onView(withText("トマト")).perform(click())
        onView(withText("OK")).perform(click())
        onView(withText("トッピング選んでください")).check(doesNotExist())
        onView(withId(R.id.textView)).check(matches(withText("オニオン\r\nレタス\r\nトマト\r\n")))
    }

    /**
     * ListDialogFragment 赤
     */
    @Test
    fun listRedTest() {
        onView(withId(R.id.list)).perform(click())
        onView(withText("色を選んでください")).check(matches(isDisplayed()))

        onView(withText("赤")).perform(click())
        onView(withText("色を選んでください")).check(doesNotExist())
        onView(withId(R.id.textView)).check(matches(withText("赤を押しましたね")))
    }

    /**
     * ListDialogFragment 青
     */
    @Test
    fun listBlueTest() {
        onView(withId(R.id.list)).perform(click())
        onView(withText("色を選んでください")).check(matches(isDisplayed()))

        onView(withText("青")).perform(click())
        onView(withText("色を選んでください")).check(doesNotExist())
        onView(withId(R.id.textView)).check(matches(withText("青を押しましたね")))
    }

    /**
     * ListDialogFragment 黄
     */
    @Test
    fun listYellowTest() {
        onView(withId(R.id.list)).perform(click())
        onView(withText("色を選んでください")).check(matches(isDisplayed()))

        onView(withText("黄")).perform(click())
        onView(withText("色を選んでください")).check(doesNotExist())
        onView(withId(R.id.textView)).check(matches(withText("黄を押しましたね")))
    }

    /**
     * CustomLayoutDialogFragment
     */
    @Test
    fun customTest() {
        onView(withId(R.id.custom)).perform(click())
        onView(withId(R.id.username)).check(matches(withHint("ユーザネーム")))
        onView(withId(R.id.password)).check(matches(withHint("パスワード")))

        onView(withId(R.id.username)).perform(ViewActions.replaceText("ほげほげ"))
        onView(withId(R.id.password)).perform(ViewActions.replaceText("ぱすぱす"))
        onView(withText("LOGIN")).perform(click())
        onView(withId(R.id.textView)).check(matches(withText("ほげほげ\n\rぱすぱす")))
    }
}
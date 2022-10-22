package com.example.viewbindigfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * ActivityとFragmentのテスト
 *
 * https://dena.github.io/codelabs/android-ui-tests-espresso
 * https://developer.android.com/guide/components/activities/testing
 * https://developer.android.com/training/basics/fragments/testing
 */
@RunWith(AndroidJUnit4::class)
@LargeTest
class ViewBindingFragmentTest {

    /**
     * Activityをテストする場合、必要
     * 各テストの開始時にActivityScenario.launchを、テスト終了時にActivityScenario.closeを自動的に呼びます。
     * activityScenarioで色々操作できる
     */
    @get:Rule
    var activityScenario  = activityScenarioRule<MainActivity>()

    /**
     * MainActivityのテスト
     */
    @Test
    fun mainActivityTest() {
        onView(withId(R.id.button)).check((matches(withText("フラグメントへ"))))
        onView(withId(R.id.editText)).perform(typeText("hoge"))
        onView(withId(R.id.button)).perform(click())
        onView(withId(R.id.FrameLayout)).check(matches(isDisplayed()))
    }

    /**
     * Fragmentのテスト
     */
    @Test
    fun fragmentTest() {
        // Fragmentの引数
        val fragmentArgs = Bundle().apply {
            putString("PARAM", "Hello World!")
        }

        // Fragmentのfactory、指定しなければデフォルトのfactoryが使われる
        // デフォルトのfactoryでもFragmentに引数は渡るので、敢えてfactoryを自作する意味がわからない・・・
        val factory = object : FragmentFactory() {
            override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
                if (className == SampleFragment::class.java.name) {
                    // コンストラクタに引数を渡して生成・・・ここをどうにかしたい場合のみ、factoryを自作するんだろうな・・・
                    return SampleFragment.newInstance("Hello World!")
                }
                return super.instantiate(classLoader, className)
            }
        }

        // グラフィカルフラグメントの場合は、launchFragmentInContainer
        // 非グラフィカルフラグメントの場合は、launchFragment
        // factoryはデフォルト(=null)でもFragmentに引数は渡る。(SampleFragment.newInstanceに渡っているみたいだ。)
        // Fragmentへの引数はfactoryの引数よりも、fragmentArgsの方が優先され、上書きされる

//        val scenario = launchFragmentInContainer<SampleFragment>(
//            fragmentArgs=fragmentArgs, factory=factory)

        // scenarioで色々操作ができる
        val scenario = launchFragmentInContainer<SampleFragment>(
            fragmentArgs=fragmentArgs)

        // テスト実行
        onView(withId(R.id.button)).perform(click())
        onView(withId(R.id.textView3)).check(matches(withText("Hello World!")))
    }
}
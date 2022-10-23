package com.example.dailogfragment

import android.content.Context
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.testing.launchFragment
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import io.mockk.every
import io.mockk.junit4.MockKRule
import io.mockk.spyk
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class BasicDialogFragmentTest {

    @get:Rule
    val mockkRule = MockKRule(this)
    /** factory */
    val factory = object : FragmentFactory() {
        override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
            if (className == BasicDialogFragment::class.java.name) {
                val fragment = spyk<BasicDialogFragment>()
                every { fragment.onAttach(ofType<Context>()) } returns Unit
                Log.d("BasicDialogFragmentTest", "Mockを返す")
                return fragment
            }
            return super.instantiate(classLoader, className)
        }
    }

    /** startボタン */
    @Test
    fun startTest() {
        with(launchFragment<BasicDialogFragment>(factory=factory)) {
            onFragment { fragment ->
                assertNotNull(fragment.dialog)
                assertTrue(fragment.requireDialog().isShowing)
                fragment.dismiss()
                fragment.parentFragmentManager.executePendingTransactions()
                assertNull(fragment.dialog)
            }

            onView(withText("start")).check(doesNotExist())
        }
    }

    /** cancelボタン */
    @Test
    fun cancelTest() {
        with(launchFragment<BasicDialogFragment>(factory=factory)) {
            onFragment { fragment ->
                assertNotNull(fragment.dialog)
                assertTrue(fragment.requireDialog().isShowing)
                fragment.dismiss()
                fragment.parentFragmentManager.executePendingTransactions()
                assertNull(fragment.dialog)
            }

            onView(withText("cancel")).check(doesNotExist())
        }
    }

    /** N/Aボタン */
    @Test
    fun naTest() {
        with(launchFragment<BasicDialogFragment>(factory=factory)) {
            onFragment { fragment ->
                assertNotNull(fragment.dialog)
                assertTrue(fragment.requireDialog().isShowing)
                fragment.dismiss()
                fragment.parentFragmentManager.executePendingTransactions()
                assertNull(fragment.dialog)
            }

            onView(withText("N/A")).check(doesNotExist())
        }
    }
}
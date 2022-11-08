package com.example.robolectric

import android.widget.Button
import android.widget.TextView
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.LooperMode

/**
 * Robolectricのテストケース
 * https://robolectric.org/
 * https://meetup-jp.toast.com/1824
 */
@RunWith(AndroidJUnit4::class)
@LooperMode(LooperMode.Mode.PAUSED)
//@Config(shadows = [ MyShadowTextView::class ]) // カスタムshadowクラスの場合。classでも、メソッドでもイケる
class MainActivityActivityScenarioRuleTest {

    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()

    /**
     * activityScenarioRuleを使うやりかた。こっちを推奨しているページもある
     */
    @Test
    fun buttonTestNew() {
        activityScenarioRule.scenario.onActivity { activity ->
            val button = activity.findViewById<Button>(R.id.button)
            val textView = activity.findViewById<TextView>(R.id.textView)
            val textView2 = activity.findViewById<TextView>(R.id.textView2)

            assertThat(textView.text.toString()).isEqualTo("Hello World!")
            assertThat(textView2.text.toString()).isEqualTo("TextView")
            button.performClick()
            assertThat(textView.text.toString()).isEqualTo("Ohh robolectric!!")
            assertThat(textView2.text.toString()).isEqualTo("local test")
        }
    }
}
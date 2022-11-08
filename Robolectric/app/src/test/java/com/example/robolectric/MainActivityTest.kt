package com.example.robolectric

import android.widget.Button
import android.widget.TextView
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

/**
 * Robolectricのテストケース
 * https://robolectric.org/
 * https://meetup-jp.toast.com/1824
 */
@RunWith(RobolectricTestRunner::class)
//@Config(shadows = [ MyShadowTextView::class ]) // カスタムshadowクラスの場合。classでも、メソッドでもイケる
class MainActivityTest {

    /**
     * 古い書き方
     */
    @Test
    fun buttonTestOld() {
        // FIXME deprecatedになってる・・・^^;
        // これだと、ActivityのLifeCycleを制御できない
        val activity = Robolectric.setupActivity(MainActivity::class.java)
        val button = activity.findViewById<Button>(R.id.button)
        val textView = activity.findViewById<TextView>(R.id.textView)
        val textView2 = activity.findViewById<TextView>(R.id.textView2)

        assertThat(textView.text.toString()).isEqualTo("Hello World!")
        assertThat(textView2.text.toString()).isEqualTo("TextView")
        button.performClick()
        assertThat(textView.text.toString()).isEqualTo("Ohh robolectric!!")
        assertThat(textView2.text.toString()).isEqualTo("local test")
    }

    /**
     * 新しい書き方
     */
    @Test
    fun buttonTestNew() {
        Robolectric.buildActivity(MainActivity::class.java).use { controller ->
            val activity: MainActivity = controller
                .setup()
                // .pause() // ActivityのLifeCycleを制御できる
                .get()

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
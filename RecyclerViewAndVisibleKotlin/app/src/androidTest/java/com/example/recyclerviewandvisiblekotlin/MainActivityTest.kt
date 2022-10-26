package com.example.recyclerviewandvisiblekotlin

import android.view.InputDevice
import android.view.MotionEvent
import android.view.View
import androidx.annotation.IdRes
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.GeneralClickAction
import androidx.test.espresso.action.GeneralLocation
import androidx.test.espresso.action.Press
import androidx.test.espresso.action.Tap
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var scenarioRule = activityScenarioRule<MainActivity>()

    private val scenario: ActivityScenario<MainActivity>
        get() = scenarioRule.scenario

    @Test

    fun recyclerViewBasicTest() {
        // スクロールだけ(position指定、最終)
        onView(withId(R.id.recyclerView))
            .perform(scrollToPosition<MyAdapter.ViewHolder>(29))

        // スクロールだけ(position指定、先頭)
        onView(withId(R.id.recyclerView))
            .perform(scrollToPosition<MyAdapter.ViewHolder>(0))

        // スクロール(view指定)
        onView(withId(R.id.recyclerView))
            .perform(scrollTo<MyAdapter.ViewHolder>(hasDescendant(getAt(withText("ああああ"), 0))))

        // スクロール(ViewHolder指定)
        onView(withId(R.id.recyclerView))
            .perform(scrollToHolder<MyAdapter.ViewHolder>(isInTheMiddle()))

        // スクロールしてアクション(position指定)
        onView(withId(R.id.recyclerView))
            .perform(actionOnItemAtPosition<MyAdapter.ViewHolder>(0, click()))

        // スクロールしてアクション(view指定)
//        onView(withId(R.id.recyclerView))
//            .perform(actionOnItem<MyAdapter.ViewHolder>(hasDescendant(getAt(withText("ああああ"), 0)), click()))

        // スクロールしてアクション(ViewHolder指定)
        onView(withId(R.id.recyclerView))
            .perform(actionOnHolderItem<MyAdapter.ViewHolder>(isInTheMiddle(), click()))
    }

    /**
     * RecyclerViewの各列の値を取ってきてassertする例
     */
    @Test
    fun recyclerViewGetItemTest() {
        // 1行目
        onView(allOf(
                isDescendantOfA(withItemViewAtPosition(withId(R.id.recyclerView), 0)),
                withId(R.id.list1))) // RecyclerViewのitem(LinerLauout)の1列目
            .check(matches(withText("ああああ")))
            .check(matches(isDisplayed()))

        onView(allOf(
                isDescendantOfA(withItemViewAtPosition(withId(R.id.recyclerView), 0)),
                withId(R.id.list2))) // RecyclerViewのitem(LinerLauout)の2列目
            .check(matches(withText("いいいい")))
            .check(matches(isDisplayed()))

        onView(allOf(
                isDescendantOfA(withItemViewAtPosition(withId(R.id.recyclerView), 0)),
                withId(R.id.list3))) // RecyclerViewのitem(LinerLauout)の3列目
            .check(matches(withText("うううう")))
            .check(matches(isDisplayed()))

        // 最終行に移動
        onView(withId(R.id.recyclerView))
            .perform(scrollToPosition<MyAdapter.ViewHolder>(29))

        // 最終行
        onView(allOf(
                isDescendantOfA(withItemViewAtPosition(withId(R.id.recyclerView), 29)),
                withId(R.id.list1)))
            .check(matches(withText("いいいい")))
            .check(matches(isDisplayed()))

        onView(allOf(
                isDescendantOfA(withItemViewAtPosition(withId(R.id.recyclerView), 29)),
                withId(R.id.list2)))
            .check(matches(withText("うううう")))
            .check(matches(isDisplayed()))

        onView(allOf(
                isDescendantOfA(withItemViewAtPosition(withId(R.id.recyclerView), 29)),
                withId(R.id.list3)))
            .check(matches(withText("ああああ")))
            .check(matches(isDisplayed()))
    }

    /**
     * カスタムMatcher
     * ViewがRecyclerViewの子供の中にいるか？
     * https://dena.github.io/codelabs/android-ui-tests-espresso/#8
     */
    fun withItemViewAtPosition(recyclerView: Matcher<View>, position: Int): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            // 操作の対象にしたいViewがマッチするような条件をこのメソッドに記述する
            // 戻り値がtrueのときにマッチしたとみなされる
            override fun matchesSafely(view: View): Boolean {
                // matchesSafleyに渡されてきた引数 view (アイテムビュー)が
                // 以下の条件を満たすかどうかチェックする
                //
                // 条件①:
                //     そのアイテムビューがrecyclerViewにマッチするRecyclerViewに所属していること
                //
                // アイテムビューの親は必ずRecyclerViewのはず
                val parent = view.parent
                if (parent !is RecyclerView || !recyclerView.matches(parent)) {
                    // RecyclerView以外のときは早期リターンをする
                    return false
                }

                // 次に以下の条件を満たすかどうかチェックする
                // 条件②: そのアイテムビューの位置番号がposition番目であること
                // 親RecyclerViewから取得した位置番号positionのアイテムビューと比較する
                val viewHolder = parent.findViewHolderForAdapterPosition(position)
                // そのViewHolderのアイテムビューと、matchesSafleyに渡されてきた引数 viewが
                // 一致していることをチェックする
                return viewHolder != null && viewHolder.itemView == view
            }

            override fun describeTo(description: Description) {
                description.appendText("with error: ")
                recyclerView.describeTo(description)
            }
        }
    }

    /**
     * カスタムMatcher
     * RecyclerViewの中間
     */
    fun isInTheMiddle(): Matcher<MyAdapter.ViewHolder?> {
        return object : TypeSafeMatcher<MyAdapter.ViewHolder>() {
            override fun matchesSafely(viewHolder: MyAdapter.ViewHolder): Boolean {
                return viewHolder.isInTheMidele
            }

            override fun describeTo(description: Description) {
                description.appendText("item in the middle")
            }
        }
    }

    /**
     * カスタムMatcher
     * 複数matchしたviewからN番目のviewに絞る
     * https://qiita.com/mechamogera/items/eda88ce016a6cb63d61f
     */
    fun getAt(parentMatcher: Matcher<View>, index: Int): Matcher<View> {
        return NthMatcher(parentMatcher, index)
    }

    private class NthMatcher<T> constructor(
        private val mParentMatcher: Matcher<T>,
        private val mNum: Int
    ) :
        TypeSafeMatcher<T>() {
        private var mCount = 0
        override fun matchesSafely(item: T): Boolean {
            return if (mParentMatcher.matches(item)) mCount++ == mNum else false
        }

        override fun describeTo(description: Description) {
            description.appendText("with $mNum-th: $mParentMatcher")
        }
    }

    /**
     * カスタムAction(参考)
     * https://dena.github.io/codelabs/android-ui-tests-espresso/#7
     */
    fun clickDescendantViewWithId(@IdRes id: Int): ViewAction {

        return object : ViewAction {

            // アクションを実行するViewをフィルターする
            override fun getConstraints(): Matcher<View> {
                // 指定したIDを子孫に持つViewに対して有効
                // 今回の場合は、R.id.checkBoxを子孫に持つView = アイテムビューに対して有効
                return hasDescendant(withId(id))
            }

            override fun getDescription(): String {
                return String.format(
                    "performing Click Action with id matching: %d", id)
            }

            // どのようなアクションを実行するかを記述する
            override fun perform(uiController: UiController, view: View) {

                //ViewActions#click()の内部実装を引用
                val action = GeneralClickAction(
                    Tap.SINGLE,
                    GeneralLocation.VISIBLE_CENTER,
                    Press.FINGER,
                    InputDevice.SOURCE_UNKNOWN,
                    MotionEvent.BUTTON_PRIMARY)

                // viewの実体はアイテムビュー
                // アイテムビューに対してfindViewByIdで子孫のViewを検索
                val target = view.findViewById<View>(id)

                // 子孫のViewにたいして、clickを実行する
                action.perform(uiController, target)
            }
        }
    }
}
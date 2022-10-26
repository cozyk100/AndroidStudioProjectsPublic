package com.example.dailogfragment

import android.os.IBinder

import android.view.WindowManager
import androidx.test.espresso.Root
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher


class ToastMatcher : TypeSafeMatcher<Root?>() {
    override fun describeTo(description: Description) {
        description.appendText("is toast")
    }

    // TODO Toastのassertはまだうまく機能しないらしい・・・
    // https://stackoverflow.com/questions/67771360/android-espresso-toast-message-assertions-not-working-with-sdk-30
    override fun matchesSafely(root: Root?): Boolean {
        val type: Int = root!!.getWindowLayoutParams().get().type
        if (type == WindowManager.LayoutParams.TYPE_TOAST) {
            val windowToken: IBinder = root.getDecorView().getWindowToken()
            val appToken: IBinder = root.getDecorView().getApplicationWindowToken()
            if (windowToken === appToken) {
                return true
            }
        }
        return false
    }
}
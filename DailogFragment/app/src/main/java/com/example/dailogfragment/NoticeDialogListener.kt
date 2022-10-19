package com.example.dailogfragment

import androidx.fragment.app.DialogFragment

/**
 * DialogFragmentのアクションをActivityに通知するためのinterface
 */
interface NoticeDialogListener {
    /**
     * OK(Positive)が押された場合
     */
    fun onDialogPositiveClick(dialog: DialogFragment)

    /**
     * Cancel(Negative)が押された場合
     */
    fun onDialogNegativeClick(dialog: DialogFragment)

    /**
     * どちらでもない(Neutral)が押された場合
     */
    fun onDialogNeutralClick(dialog: DialogFragment)
}
package com.example.setting

import android.os.Bundle
import android.text.InputType
import androidx.preference.EditTextPreference
import androidx.preference.PreferenceFragmentCompat

/**
 * 設定画面のFragment
 */
class SettingsFragment : PreferenceFragmentCompat() {
    /**
     * このフラグメントの設定を提供するためにonCreate(Bundle)中に呼び出されます。
     * サブクラスは、 setPreferenceScreen(PreferenceScreen)を直接呼び出すか、
     * addPreferencesFromResource(int)などのヘルパー メソッドを介して呼び出す必要があります。
     * @param[savedInstanceState] フラグメントが以前に保存された状態から再作成されている場合、これはその状態です。
     * @param[rootKey] null でない場合、この設定フラグメントは、このキーを使用してPreferenceScreenをルートにする必要があります。
     */
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)

        //  入力を数値のみに限定する
        val numberPreference = findPreference<EditTextPreference>("numberPreference")
        numberPreference?.setOnBindEditTextListener { editText ->
            editText.inputType = InputType.TYPE_CLASS_NUMBER
        }
    }
}
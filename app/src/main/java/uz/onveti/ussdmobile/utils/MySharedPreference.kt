package uz.onveti.ussdmobile.utils

import android.annotation.SuppressLint
import android.content.ContentProviderOperation
import android.content.Context
import android.content.SharedPreferences

object MySharedPreference {

    private const val NAME = "ussd"
    private const val MODE = Context.MODE_PRIVATE
    private var sharedPreferences: SharedPreferences? = null
    private var editor: SharedPreferences.Editor? = null

    @SuppressLint("CommitPrefEdits")
    fun getInstance(context: Context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences(NAME, MODE)
            editor = sharedPreferences!!.edit()
        }
    }

    fun getLanguage(): String {
        return sharedPreferences?.getString("language", "uz")!!
    }

    fun setLanguage(language: String) {
        editor?.putString("language", language)?.apply()
    }

    fun getBoolean(): Boolean {
        return sharedPreferences?.getBoolean("existaence", false)!!
    }

    fun setBoolean(existaence: Boolean) {
        editor?.putBoolean("existaence", existaence)?.apply()
    }
}
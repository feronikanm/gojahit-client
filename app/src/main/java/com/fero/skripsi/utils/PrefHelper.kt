package com.fero.skripsi.utils

import android.content.Context
import android.content.SharedPreferences

class PrefHelper(context: Context?) {

    companion object {
        val PREF_IS_LOGIN_PELANGGAN = "PREF_IS_LOGIN_PELANGGAN"
        val PREF_EMAIL_PELANGGAN = "PREF_EMAIL_PELANGGAN"
        val PREF_PASSWORD_PELANGGAN = "PREF_PASSWORD_PELANGGAN"
        val PREF_ID_PELANGGAN = "PREF_ID_PELANGGAN"
        val PREF_NAMA_PELANGGAN = "PREF_NAMA_PELANGGAN"

        val PREF_IS_LOGIN_PENJAHIT = "PREF_IS_LOGIN_PENJAHIT"
        val PREF_EMAIL_PENJAHIT = "PREF_EMAIL_PENJAHIT"
        val PREF_PASSWORD_PENJAHIT = "PREF_PASSWORD_PENJAHIT"
        val PREF_ID_PENJAHIT = "PREF_ID_PENJAHIT"
        val PREF_NAMA_PENJAHIT = "PREF_NAMA_PENJAHIT"
    }

    private val PREFS_NAME = "PrefHelpSkipsi"
    private var sharedPref: SharedPreferences
    val editor: SharedPreferences.Editor

    init {
        sharedPref = context!!.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        editor = sharedPref.edit()
    }

    fun put(key: String, value: String) {
        editor.putString(key, value)
            .apply()
    }

    fun put(key: String, value: Int) {
        editor.putInt(key, value)
            .apply()
    }

    fun put(key: String, value: Boolean) {
        editor.putBoolean(key, value)
            .apply()
    }

    fun getBoolean(key: String): Boolean {
        return sharedPref.getBoolean(key, false)
    }

    fun getString(key: String): String? {
        return sharedPref.getString(key, null)
    }

    fun clear() {
        editor.clear()
            .apply()
    }

}
package com.fero.skripsi.utils

import android.content.Context
import android.content.SharedPreferences

class PrefHelper(context: Context?) {

    companion object {
        val PREF_IS_LOGIN_PELANGGAN = "PREF_IS_LOGIN_PELANGGAN"
        val PREF_ID_PELANGGAN = "PREF_ID_PELANGGAN"
        val PREF_EMAIL_PELANGGAN = "PREF_EMAIL_PELANGGAN"
        val PREF_PASSWORD_PELANGGAN = "PREF_PASSWORD_PELANGGAN"
        val PREF_NAMA_PELANGGAN = "PREF_NAMA_PELANGGAN"
        val PREF_TELP_PELANGGAN = "PREF_TELP_PELANGGAN"
        val PREF_LATITUDE_PELANGGAN = "PREF_LATITUDE_PELANGGAN"
        val PREF_LONGITUDE_PELANGGAN = "PREF_LONGITUDE_PELANGGAN"
        val PREF_ALAMAT_PELANGGAN = "PREF_ALAMAT_PELANGGAN"
        val PREF_JK_PELANGGAN = "PREF_JK_PELANGGAN"
        val PREF_FOTO_PELANGGAN = "PREF_FOTO_PELANGGAN"

        val PREF_IS_LOGIN_PENJAHIT = "PREF_IS_LOGIN_PENJAHIT"
        val PREF_ID_PENJAHIT = "PREF_ID_PENJAHIT"
        val PREF_EMAIL_PENJAHIT = "PREF_EMAIL_PENJAHIT"
        val PREF_PASSWORD_PENJAHIT = "PREF_PASSWORD_PENJAHIT"
        val PREF_NAMA_PENJAHIT = "PREF_NAMA_PENJAHIT"
        val PREF_NAMA_TOKO_PENJAHIT = "PREF_NAMA_TOKO_PENJAHIT"
        val PREF_KET_PENJAHIT = "PREF_KET_PENJAHIT"
        val PREF_TELP_PENJAHIT = "PREF_TELP_PENJAHIT"
        val PREF_LATITUDE_PENJAHIT = "PREF_LATITUDE_PENJAHIT"
        val PREF_LONGITUDE_PENJAHIT = "PREF_LONGITUDE_PENJAHIT"
        val PREF_ALAMAT_PENJAHIT = "PREF_ALAMAT_PENJAHIT"
        val PREF_HARI_BUKA_PENJAHIT = "PREF_HARI_BUKA_PENJAHIT"
        val PREF_JAM_BUKA_PENJAHIT = "PREF_JAM_BUKA_PENJAHIT"
        val PREF_JAM_TUTUP_PENJAHIT = "PREF_JAM_TUTUP_PENJAHIT"
        val PREF_JANGKAUAN_PENJAHIT = "PREF_JANGKAUAN_PENJAHIT"
        val PREF_SPESIFIKASI_PENJAHIT = "PREF_SPESIFIKASI_PENJAHIT"
        val PREF_FOTO_PENJAHIT = "PREF_FOTO_PENJAHIT"
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
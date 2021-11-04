package com.fero.skripsi.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pelanggan(
    val id_pelanggan: Int?,
    val nama_pelanggan: String?,
    val email_pelanggan: String?,
    val password_pelanggan: String?,
    val alamat_pelanggan: String?,
    val jk_pelanggan: String?,
    val latitude_pelanggan: String?,
    val longitude_pelanggan: String?,
    val telp_pelanggan: String?,
    val foto_pelanggan: String?
): Parcelable
package com.fero.skripsi.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pelanggan(
    var id_pelanggan: Int?,
    var nama_pelanggan: String?,
    var email_pelanggan: String?,
    var password_pelanggan: String?,
    var alamat_pelanggan: String?,
    var jk_pelanggan: String?,
    var latitude_pelanggan: String?,
    var longitude_pelanggan: String?,
    var telp_pelanggan: String?,
    var foto_pelanggan: String?
): Parcelable
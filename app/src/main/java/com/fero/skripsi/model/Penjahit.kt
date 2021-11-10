package com.fero.skripsi.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Penjahit(
    val id_penjahit: Int?,
    val nama_penjahit: String?,
    val email_penjahit: String?,
    val password_penjahit: String?,
    val alamat_penjahit: String?,
    val foto_penjahit: String?,
    val nama_toko: String?,
    val telp_penjahit: String?,
    @SerializedName("keterangan_toko") var keterangan_toko: String? = null,
    @SerializedName("hari_buka") var hari_buka: String? = null,
    @SerializedName("jam_buka") var jam_buka: String? = null,
    @SerializedName("jam_tutup") var jam_tutup: String? = null,
    val jangkauan_kategori_penjahit: String?,
    val latitude_penjahit: String?,
    val longitude_penjahit: String?,
    val spesifikasi_penjahit: String?
) : Parcelable
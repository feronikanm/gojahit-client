package com.fero.skripsi.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Nilai(
    val alamat_penjahit: String? = null,
    val email_penjahit: String? = null,
    val foto_penjahit: String? = null,
    val hari_buka: String? = null,
    val id_nilai: Int? = 0,
    val id_penjahit: Int? = 0,
    val jam_buka: String? = null,
    val jam_tutup: String? = null,
    val jangkauan_kategori_penjahit: String? = null,
    val keterangan_toko: String? = null,
    val latitude_penjahit: String? = null,
    val longitude_penjahit: String? = null,
    val nama_penjahit: String? = null,
    val nama_toko: String? = null,
    val nilai_akhir: Double?,
    val password_penjahit: String? = null,
    val spesifikasi_penjahit: String? = null,
    val telp_penjahit: String? = null
) :Parcelable
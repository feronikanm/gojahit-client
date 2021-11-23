package com.fero.skripsi.model

import android.content.Intent
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailKategori(
    val id_detail_kategori: Int?,
    val id_penjahit: Int?,
    val id_kategori: Int?,
    val keterangan_kategori: String?,
    val bahan_jahit: String?,
    val harga_bahan: String,
    val ongkos_penjahit: String,
    val perkiraan_lama_waktu_pengerjaan: String?
) : Parcelable
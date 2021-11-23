package com.fero.skripsi.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pesanan(
    val id_pelanggan: Int,
    val id_penjahit: Int,
    val id_pesanan: Int,
    val lama_waktu_pengerjaan: String,
    val status_pesanan: String,
    val tanggal_pesanan: String,
    val tanggal_pesanan_selesai: String
) : Parcelable
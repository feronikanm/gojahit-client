package com.fero.skripsi.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pesanan(
    val id_pesanan: Int?,
    val id_pelanggan: Int?,
    val id_penjahit: Int?,
    val id_detail_kategori: Int?,
    val tanggal_pesanan: String?,
    val tanggal_pesanan_selesai: String?,
    val lama_waktu_pengerjaan: String?,
    val catatan_pesanan: String?,
    val desain_jahitan: String?,
    val bahan_jahit: String?,
    val asal_bahan: String?,
    val panjang_bahan: Int?,
    val lebar_bahan: Int?,
    val status_bahan: String?,
    val harga_bahan: Int?,
    val ongkos_penjahit: Int?,
    val jumlah_jahitan: Int?,
    val biaya_jahitan: Int?,
    val total_biaya: Int?,
    val status_pesanan: String?,
) : Parcelable
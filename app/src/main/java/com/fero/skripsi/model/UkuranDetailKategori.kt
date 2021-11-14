package com.fero.skripsi.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UkuranDetailKategori(
    val id_ukuran_detail_kategori: Int?,
    val id_detail_kategori: Int?,
    val id_kategori: Int?,
    val id_penjahit: Int?,
    val id_ukuran: Int?,
    val keterangan_kategori: String?,
    val bahan_jahit: String?,
    val gambar_ukuran: String?,
    val harga_bahan: String?,
    val gambar_kategori: String?,
    val nama_kategori: String?,
    val nama_ukuran: String?,
    val ongkos_penjahit: String?,
    val perkiraan_lama_waktu_pengerjaan: String?
) : Parcelable
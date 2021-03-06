package com.fero.skripsi.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailKategoriPenjahit(
    val id_detail_kategori: Int? = 0,
    val id_penjahit: Int? = 0,
    val id_kategori: Int? = 0,
    val alamat_penjahit: String? = "",
    val bahan_jahit: String? = "",
    val email_penjahit: String? = "",
    val foto_penjahit: String? = "",
    val gambar_kategori: String? = "",
    val harga_bahan: String? = "",
    val hari_buka: String? = "",
    val jam_buka: String? = "",
    val jam_tutup: String? = "",
    val keterangan_toko: String? = "",
    val jangkauan_kategori_penjahit: String? = "",
    val spesifikasi_penjahit: String? = "",
    val perkiraan_lama_waktu_pengerjaan: String? = "",
    val keterangan_kategori: String? = "",
    val latitude_penjahit: String? = "",
    val longitude_penjahit: String? = "",
    val nama_kategori: String? = "",
    val nama_penjahit: String? = "",
    val nama_toko: String? = "",
    val ongkos_penjahit: String? = "",
    val password_penjahit: String? = "",
    val telp_penjahit: String? = ""
) : Parcelable
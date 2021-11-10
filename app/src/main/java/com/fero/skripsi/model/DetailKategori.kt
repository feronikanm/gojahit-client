package com.fero.skripsi.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DetailKategori(
    val id_detail_kategori: Int?,
    val id_penjahit: Int?,
    val id_kategori: Int?,
    val alamat_penjahit: String?,
    val bahan_jahit: String?,
    val email_penjahit: String?,
    val foto_penjahit: String?,
    val gambar_kategori: String?,
    val harga_bahan: Int?,
    val hari_buka: String?,
    val jam_buka: String?,
    val jam_tutup: String?,
    val keterangan_toko: String?,
    val jangkauan_kategori_penjahit: String?,
    val spesifikasi_penjahit: String?,
    val perkiraan_lama_waktu_pengerjaan: String?,
    val keterangan_kategori: String?,
    val latitude_penjahit: String?,
    val longitude_penjahit: String?,
    val nama_kategori: String?,
    val nama_penjahit: String?,
    val nama_toko: String?,
    val ongkos_penjahit: Int?,
    val password_penjahit: String?,
    val telp_penjahit: String?
) : Parcelable
package com.fero.skripsi.model

data class DetailPesanan(
    val asal_bahan: String,
    val bahan_jahit: String,
    val biaya_jahitan: Int,
    val catatan_pesanan: String,
    val desain_jahitan: Any,
    val harga_bahan: Int,
    val id_detail_pesanan: Int,
    val id_pesanan: Int,
    val jumlah_jahitan: Int,
    val kategori: String,
    val lebar_bahan: Int,
    val ongkos_penjahit: Int,
    val panjang_bahan: Int,
    val status_bahan: String,
    val total_biaya: Int
)
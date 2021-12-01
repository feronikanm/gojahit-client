package com.fero.skripsi.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UkuranDetailPesanan(
    val id_ukuran_detail_pesanan: Int?,
    val id_pesanan: Int?,
    val id_ukuran: Int?,
    val nama_ukuran: String?,
    val gambar_ukuran: String?,
    val ukuran_pesanan: Int?
) : Parcelable
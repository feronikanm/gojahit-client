package com.fero.skripsi.data.source.remote

import com.fero.skripsi.model.*
import com.fero.skripsi.utils.Constant.URL_DETAIL_KATEGORI_DELETE
import com.fero.skripsi.utils.Constant.URL_DETAIL_KATEGORI_GET_BY_KATEGORI
import com.fero.skripsi.utils.Constant.URL_DETAIL_KATEGORI_GET_BY_PENJAHIT
import com.fero.skripsi.utils.Constant.URL_DETAIL_KATEGORI_INSERT
import com.fero.skripsi.utils.Constant.URL_DETAIL_KATEGORI_UPDATE
import com.fero.skripsi.utils.Constant.URL_KATEGORI_GET
import com.fero.skripsi.utils.Constant.URL_PELANGGAN_GET
import com.fero.skripsi.utils.Constant.URL_PELANGGAN_INSERT
import com.fero.skripsi.utils.Constant.URL_PELANGGAN_UPDATE
import com.fero.skripsi.utils.Constant.URL_PENJAHIT_GET
import com.fero.skripsi.utils.Constant.URL_PENJAHIT_GET_BY_NILAI
import com.fero.skripsi.utils.Constant.URL_PENJAHIT_INSERT
import com.fero.skripsi.utils.Constant.URL_PENJAHIT_UPDATE
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*


interface ApiService {

    @GET(URL_PENJAHIT_GET_BY_NILAI)
    fun getDataPenjahit() : Observable<List<DetailKategoriNilai>>

    @GET(URL_KATEGORI_GET)
    fun getDataKategori() : Observable<List<DetailKategoriNilai>>

    @GET(URL_PELANGGAN_GET)
    fun getPelanggan(): Call<List<Pelanggan>>

    @GET(URL_PENJAHIT_GET)
    fun getPenjahit(): Call<List<Penjahit>>

    @FormUrlEncoded
    @POST(URL_PELANGGAN_INSERT)
    fun insertDataPelanggan(
        @Field("namaPelanggan") nama_pelanggan: String,
        @Field("emailPelanggan") email_pelanggan: String,
        @Field("passwordPelanggan") password_pelanggan: String,
        @Field("telpPelanggan") telp_pelanggan: String,
        @Field("latitudePelanggan") latitude_pelanggan: String,
        @Field("longitudePelanggan") longitude_pelanggan: String,
        @Field("alamatPelanggan") alamat_pelanggan: String,
        @Field("jkPelanggan") jk_pelanggan: String,
        @Field("foto_pelanggan") foto_pelanggan: String,

        ): Call<Success<Pelanggan>>


    @FormUrlEncoded
    @POST(URL_PELANGGAN_UPDATE)
    fun updateDataPelanggan(
        @Path("id_pelanggan") id_pelanggan_path: Int,
        @Field("namaPelanggan") nama_pelanggan: String,
        @Field("emailPelanggan") email_pelanggan: String,
        @Field("passwordPelanggan") password_pelanggan: String,
        @Field("telpPelanggan") telp_pelanggan: String,
        @Field("latitudePelanggan") latitude_pelanggan: String,
        @Field("longitudePelanggan") longitude_pelanggan: String,
        @Field("alamatPelanggan") alamat_pelanggan: String,
        @Field("jkPelanggan") jk_pelanggan: String,
        @Field("foto_pelanggan") foto_pelanggan: String,

        ): Call<Success<Pelanggan>>


    @FormUrlEncoded
    @POST(URL_PENJAHIT_INSERT)
    fun insertDataPenjahit(
        @Field("namaPenjahit") nama_penjahit: String,
        @Field("emailPenjahit") email_penjahit: String,
        @Field("passwordPenjahit") password_penjahit: String,
        @Field("telpPenjahit") telp_penjahit: String,
        @Field("namaToko") nama_toko: String,
        @Field("keteranganToko") keterangan_toko: String,
        @Field("latitudePenjahit") latitude_penjahit: String,
        @Field("longitudePenjahit") longitude_penjahit: String,
        @Field("alamatPenjahit") alamat_penjahit: String,
        @Field("spesifikasiPenjahit") spesifikasi_penjahit: String,
        @Field("jangkauanKategoriPenjahit") jangkauan_kategori_penjahit: String,
        @Field("hariBuka") hari_buka: String,
        @Field("jamBuka") jam_buka: Any,
        @Field("jamTutup") jam_tutup: String,
        @Field("foto_penjahit") foto_penjahit: String,

        ): Call<Success<Penjahit>>


    @FormUrlEncoded
    @POST(URL_PENJAHIT_UPDATE)
    fun updateDataPenjahit(
        @Path("id_penjahit") id_penjahit_path: Int,
        @Field("namaPenjahit") nama_penjahit: String,
        @Field("emailPenjahit") email_penjahit: String,
        @Field("passwordPenjahit") password_penjahit: String,
        @Field("telpPenjahit") telp_penjahit: String,
        @Field("namaToko") nama_toko: String,
        @Field("keteranganToko") keterangan_toko: String,
        @Field("latitudePenjahit") latitude_penjahit: String,
        @Field("longitudePenjahit") longitude_penjahit: String,
        @Field("alamatPenjahit") alamat_penjahit: String,
        @Field("spesifikasiPenjahit") spesifikasi_penjahit: String,
        @Field("jangkauanKategoriPenjahit") jangkauan_kategori_penjahit: String,
        @Field("hariBuka") hari_buka: String,
        @Field("jamBuka") jam_buka: Any,
        @Field("jamTutup") jam_tutup: String,
        @Field("foto_penjahit") foto_penjahit: String,

        ): Call<Success<Penjahit>>


    @GET(URL_DETAIL_KATEGORI_GET_BY_PENJAHIT)
    fun getDetailKategori(
        @Path("id_penjahit") id_penjahit_path: Int
    ): Observable<List<ListDetailKategori>>


    @FormUrlEncoded
    @POST(URL_DETAIL_KATEGORI_INSERT)
    fun insertDataDetailKategori(
        @Field("idPenjahit") id_penjahit: Int,
        @Field("idKategori") id_kategori: Int,
        @Field("keteranganKategori") keterangan_kategori: String,
        @Field("bahanJahit") bahan_jahit: String,
        @Field("hargaBahan") harga_bahan: String,
        @Field("ongkosPenjahit") ongkos_penjahit: String,
        @Field("perkiraanLamaWaktuPengerjaan") perkiraan_lama_waktu_pengerjaan: String,

        ): Call<Success<DetailKategori>>


    @POST(URL_DETAIL_KATEGORI_DELETE)
    fun deleteDataDetailKategori(
        @Path("id_detail_kategori") id_detail_kategori_path: Int
    ): Call<Success<ListDetailKategori>>


    @FormUrlEncoded
    @POST(URL_DETAIL_KATEGORI_UPDATE)
    fun updateDataDetailKategori(
        @Path("id_detail_kategori") id_detail_kategori_path: Int,
        @Field("idPenjahit") id_penjahit: Int,
        @Field("idKategori") id_kategori: Int,
        @Field("keteranganKategori") keterangan_kategori: String,
        @Field("bahanJahit") bahan_jahit: String,
        @Field("hargaBahan") harga_bahan: String?,
        @Field("ongkosPenjahit") ongkos_penjahit: String?,
        @Field("perkiraanLamaWaktuPengerjaan") perkiraan_lama_waktu_pengerjaan: String,

        ): Call<Success<ListDetailKategori>>


    @GET(URL_DETAIL_KATEGORI_GET_BY_KATEGORI)
    fun getDataPenjahitByKategori(
        @Path("id_kategori") id_kategori_path: Int
    ): Observable<List<DetailKategoriNilai>>



//    @GET(URL_DETAIL_PESANAN_GET)
//    fun getDetailPesanan(): Call<List<DetailPesanan>>
//
//    @FormUrlEncoded
//    @POST(ApiEndPoint.URL_DETAIL_PESANAN_INSERT)
//    fun registerDetailPesanan(
//        @Field("idPesanan") id_pesanan: Int,
//        @Field("catatanPesanan") catatan_pesanan: String,
//        @Field("kategori") kategori: String,
//        @Field("bahanJahit") bahan_jahit: String,
//        @Field("asalBahan") asal_bahan: String,
//        @Field("panjangBahan") panjang_bahan: Int,
//        @Field("lebarBahan") lebar_bahan: Int,
//        @Field("statusBahan") status_bahan: String,
//        @Field("hargaBahan") harga_bahan: Int,
//        @Field("ongkosPenjahit") ongkos_penjahit: Int,
//        @Field("jumlahJahitan") jumlah_jahitan: Int,
//        @Field("biayaJahitan") biaya_jahitan: Int,
//        @Field("totalBiaya") total_biaya: Int,
//
//        ): Call<Success<DetailPesanan>>
//
//    @GET(URL_PESANAN_GET)
//    fun getPesanan(): Call<List<Pesanan>>
//
//    @FormUrlEncoded
//    @POST(ApiEndPoint.URL_PESANAN_INSERT)
//    fun registerPesanan(
//        @Field("idPelanggan") id_pelanggan: Int,
//        @Field("idPenjahit") id_penjahit: Int,
//        @Field("tanggalPesanan") tanggal_pesanan: String,
//        @Field("tanggalPesananSelesai") tanggal_pesanan_selesai: String,
//        @Field("lamaWaktuPengerjaan") lama_waktu_pengerjaan: String,
//        @Field("statusPesanan") status_pesanan: String,
//
//        ): Call<Success<Pesanan>>
//
//    @GET(URL_RATING_GET)
//    fun getRating(): Call<List<Rating>>
//
//    @FormUrlEncoded
//    @POST(ApiEndPoint.URL_RATING_INSERT)
//    fun registerRating(
//        @Field("idPenjahit") id_penjahit: Int,
//        @Field("idKriteria") id_kriteria: Int,
//        @Field("ratingPenjahit") rating_penjahit: Int,
//
//        ): Call<Success<Rating>>
//
//
//    @GET(URL_UKURAN_DETAIL_KATEGORI_GET)
//    fun getUkuranDetailKategori(): Call<List<UkuranDetailKategori>>
//
//    @FormUrlEncoded
//    @POST(ApiEndPoint.URL_UKURAN_DETAIL_KATEGORI_INSERT)
//    fun registerUkuranDetailKategori(
//        @Field("idDetailKategori") id_detail_kategori: Int,
//        @Field("idUkuran") id_ukuran: Int,
//
//        ): Call<Success<UkuranDetailKategori>>
//
//
//    @GET(URL_UKURAN_DETAIL_PESANAN_GET)
//    fun getUkuranDetailPesanan(): Call<List<UkuranDetailPesanan>>
//
//    @FormUrlEncoded
//    @POST(ApiEndPoint.URL_UKURAN_DETAIL_PESANAN_INSERT)
//    fun registerUkuranDetailPesanan(
//        @Field("idDetailPesanan") id_detail_pesanan: Int,
//        @Field("namaUkuran") nama_ukuran: String,
//        @Field("ukuranPesanan") ukuran_pesanan: Int,
//
//        ): Call<Success<UkuranDetailPesanan>>

}
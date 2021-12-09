package com.fero.skripsi.data.source.remote

import com.fero.skripsi.model.*
import com.fero.skripsi.utils.Constant.URL_DETAIL_KATEGORI_DELETE
import com.fero.skripsi.utils.Constant.URL_DETAIL_KATEGORI_GET_BY_KATEGORI
import com.fero.skripsi.utils.Constant.URL_DETAIL_KATEGORI_GET_BY_PENJAHIT
import com.fero.skripsi.utils.Constant.URL_DETAIL_KATEGORI_INSERT
import com.fero.skripsi.utils.Constant.URL_DETAIL_KATEGORI_UPDATE
import com.fero.skripsi.utils.Constant.URL_KATEGORI_GET
import com.fero.skripsi.utils.Constant.URL_PELANGGAN_GET
import com.fero.skripsi.utils.Constant.URL_PELANGGAN_GET_BY_ID
import com.fero.skripsi.utils.Constant.URL_PELANGGAN_INSERT
import com.fero.skripsi.utils.Constant.URL_PELANGGAN_UPDATE
import com.fero.skripsi.utils.Constant.URL_PENJAHIT_GET
import com.fero.skripsi.utils.Constant.URL_PENJAHIT_GET_BY_ID
import com.fero.skripsi.utils.Constant.URL_PENJAHIT_GET_BY_NILAI
import com.fero.skripsi.utils.Constant.URL_PENJAHIT_INSERT
import com.fero.skripsi.utils.Constant.URL_PENJAHIT_UPDATE
import com.fero.skripsi.utils.Constant.URL_PESANAN_DELETE
import com.fero.skripsi.utils.Constant.URL_PESANAN_GET_BY_ID
import com.fero.skripsi.utils.Constant.URL_PESANAN_GET_BY_PELANGGAN
import com.fero.skripsi.utils.Constant.URL_PESANAN_GET_BY_PENJAHIT
import com.fero.skripsi.utils.Constant.URL_PESANAN_INSERT
import com.fero.skripsi.utils.Constant.URL_PESANAN_UPDATE
import com.fero.skripsi.utils.Constant.URL_RATING_INSERT
import com.fero.skripsi.utils.Constant.URL_UKURAN_DETAIL_KATEGORI_DELETE
import com.fero.skripsi.utils.Constant.URL_UKURAN_DETAIL_KATEGORI_GET_BY_DETAIL_KATEGORI
import com.fero.skripsi.utils.Constant.URL_UKURAN_DETAIL_KATEGORI_INSERT
import com.fero.skripsi.utils.Constant.URL_UKURAN_DETAIL_PESANAN_DELETE
import com.fero.skripsi.utils.Constant.URL_UKURAN_DETAIL_PESANAN_GET_BY_PESANAN
import com.fero.skripsi.utils.Constant.URL_UKURAN_DETAIL_PESANAN_INSERT
import com.fero.skripsi.utils.Constant.URL_UKURAN_DETAIL_PESANAN_UPDATE
import com.fero.skripsi.utils.Constant.URL_UKURAN_GET
import io.reactivex.Observable
import retrofit2.Call
import retrofit2.http.*


interface ApiService {

    @GET(URL_PENJAHIT_GET_BY_NILAI)
    fun getDataPenjahitNilai() : Observable<List<DetailKategoriNilai>>

    @GET(URL_PENJAHIT_GET)
    fun getDataPenjahit() : Observable<List<DetailKategoriNilai>>

    @GET(URL_KATEGORI_GET)
    fun getDataKategori() : Observable<List<DetailKategoriNilai>>

    @GET(URL_PELANGGAN_GET)
    fun getPelanggan(): Call<List<Pelanggan>>

    @GET(URL_PENJAHIT_GET)
    fun getPenjahit(): Call<List<Penjahit>>

    @GET(URL_PELANGGAN_GET_BY_ID)
    fun getDataPelangganById(
        @Path("id_pelanggan") id_pelanggan_path: Int
    ): Call<Pelanggan>

    @GET(URL_PENJAHIT_GET_BY_ID)
    fun getDataPenjahitById(
        @Path("id_penjahit") id_penjahit_path: Int
    ): Call<Penjahit>

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
    ): Observable<List<DetailKategoriPenjahit>>


    @GET(URL_DETAIL_KATEGORI_GET_BY_PENJAHIT)
    fun getDetailKategoriInPelanggan(
        @Path("id_penjahit") id_penjahit_path: Int
    ): Observable<List<DetailKategoriNilai>>


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
    ): Call<Success<Int>>


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

        ): Call<Success<DetailKategori>>


    @GET(URL_DETAIL_KATEGORI_GET_BY_KATEGORI)
    fun getDataPenjahitByKategori(
        @Path("id_kategori") id_kategori_path: Int
    ): Observable<List<DetailKategoriNilai>>


    @GET(URL_UKURAN_GET)
    fun getDataUkuran() : Observable<List<UkuranDetailKategori>>


    @GET(URL_UKURAN_DETAIL_KATEGORI_GET_BY_DETAIL_KATEGORI)
    fun getUkuranByDetailKategori(
        @Path("id_detail_kategori") id_detail_kategori_path: Int
    ): Observable<List<UkuranDetailKategori>>


    @FormUrlEncoded
    @POST(URL_UKURAN_DETAIL_KATEGORI_INSERT)
    fun insertDataUkuranDetailKategori(
        @Field("idDetailKategori") id_detail_kategori: Int,
        @Field("idUkuran") id_ukuran: Int,

        ): Call<Success<UkuranDetailKategori>>


    @POST(URL_UKURAN_DETAIL_KATEGORI_DELETE)
    fun deleteDataUkuranDetailKategori(
        @Path("id_ukuran_detail_kategori") id_ukuran_detail_kategori_path: Int
    ): Call<ResponseDeleteSuccess>


    @FormUrlEncoded
    @POST(URL_RATING_INSERT)
    fun insertDataRating(
        @Field("idPenjahit") id_penjahit: Int?,
        @Field("kriteria1") kriteria_1: Int?,
        @Field("kriteria2") kriteria_2: Int?,
        @Field("kriteria3") kriteria_3: Int?,
        @Field("kriteria4") kriteria_4: Int?,

        ): Call<Success<Rating>>


//    @GET(URL_PESANAN_GET_BY_ID)
//    fun getDataPesananById(
//        @Path("id_pesanan") id_pesanan_path: Int
//    ): Observable<Pesanan>

    @GET(URL_PESANAN_GET_BY_ID)
    fun getDataPesananById(
        @Path("id_pesanan") id_pesanan_path: Int
    ): Call<Pesanan>


    @GET(URL_PESANAN_GET_BY_PELANGGAN)
    fun getDataPesananByPelanggan(
        @Path("id_pelanggan") id_pelanggan_path: Int
    ): Observable<List<Pesanan>>


    @GET(URL_PESANAN_GET_BY_PENJAHIT)
    fun getDataPesananByPenjahit(
        @Path("id_penjahit") id_penjahit_path: Int
    ): Observable<List<Pesanan>>


    @FormUrlEncoded
    @POST(URL_PESANAN_INSERT)
    fun insertDataPesanan(
        @Field("idPelanggan") id_pelanggan: Int?,
        @Field("idPenjahit") id_penjahit: Int?,
        @Field("idDetailKategori") id_detail_kategori: Int?,
        @Field("tanggalPesanan") tanggal_pesanan: String?,
        @Field("tanggalPesananSelesai") tanggal_pesanan_selesai: String?,
        @Field("lamaWaktuPengerjaan") lama_waktu_pengerjaan: String?,
        @Field("catatanPesanan") catatan_pesanan: String?,
        @Field("desainJahitan") desain_jahitan: String?,
        @Field("bahanJahit") bahan_jahit: String?,
        @Field("asalBahan") asal_bahan: String?,
        @Field("panjangBahan") panjang_bahan: String?,
        @Field("lebarBahan") lebar_bahan: String?,
        @Field("statusBahan") status_bahan: String?,
        @Field("hargaBahan") harga_bahan: Int?,
        @Field("ongkosPenjahit") ongkos_penjahit: Int?,
        @Field("jumlahJahitan") jumlah_jahitan: Int?,
        @Field("biayaJahitan") biaya_jahitan: Int?,
        @Field("totalBiaya") total_biaya: Int?,
        @Field("statusPesanan") status_pesanan: String?,

        ): Call<Success<Pesanan>>


    @FormUrlEncoded
    @POST(URL_PESANAN_UPDATE)
    fun updateDataPesanan(
        @Path("id_pesanan") id_pesanan_path: Int?,
        @Field("idPelanggan") id_pelanggan: Int?,
        @Field("idPenjahit") id_penjahit: Int?,
        @Field("idDetailKategori") id_detail_kategori: Int?,
        @Field("tanggalPesanan") tanggal_pesanan: String?,
        @Field("tanggalPesananSelesai") tanggal_pesanan_selesai: String?,
        @Field("lamaWaktuPengerjaan") lama_waktu_pengerjaan: String?,
        @Field("catatanPesanan") catatan_pesanan: String?,
        @Field("desainJahitan") desain_jahitan: String?,
        @Field("bahanJahit") bahan_jahit: String?,
        @Field("asalBahan") asal_bahan: String?,
        @Field("panjangBahan") panjang_bahan: String?,
        @Field("lebarBahan") lebar_bahan: String?,
        @Field("statusBahan") status_bahan: String?,
        @Field("hargaBahan") harga_bahan: Int?,
        @Field("ongkosPenjahit") ongkos_penjahit: Int?,
        @Field("jumlahJahitan") jumlah_jahitan: Int?,
        @Field("biayaJahitan") biaya_jahitan: Int?,
        @Field("totalBiaya") total_biaya: Int?,
        @Field("statusPesanan") status_pesanan: String?,

        ): Call<Success<Pesanan>>


    @POST(URL_PESANAN_DELETE)
    fun deleteDataPesanan(
        @Path("id_pesanan") id_pesanan_path: Int
    ): Call<Success<Pesanan>>


    @GET(URL_UKURAN_DETAIL_PESANAN_GET_BY_PESANAN)
    fun getDataUkuranByPesanan(
        @Path("id_pesanan") id_pesanan_path: Int
    ): Observable<List<UkuranDetailPesanan>>


    @GET(URL_UKURAN_DETAIL_KATEGORI_GET_BY_DETAIL_KATEGORI)
    fun getDataUkuranPesananByDetailKategori(
        @Path("id_detail_kategori") id_detail_kategori_path: Int
    ): Observable<List<UkuranDetailPesanan>>


    @FormUrlEncoded
    @POST(URL_UKURAN_DETAIL_PESANAN_INSERT)
    fun insertDataUkuranDetailPesanan(
        @Field("idPesanan") id_pesanan: Int?,
        @Field("idUkuran") id_ukuran: Int?,
        @Field("ukuranPesanan") ukuran_pesanan: Int?,

        ): Call<Success<UkuranDetailPesanan>>


    @FormUrlEncoded
    @POST(URL_UKURAN_DETAIL_PESANAN_UPDATE)
    fun updateDataUkuranDetailPesanan(
        @Path("id_ukuran_detail_pesanan") id_ukuran_detail_pesanan_path: Int?,
        @Field("idPesanan") id_pesanan: Int?,
        @Field("idUkuran") id_ukuran: Int?,
        @Field("ukuranPesanan") ukuran_pesanan: Int?,

        ): Call<Success<UkuranDetailPesanan>>


    @POST(URL_UKURAN_DETAIL_PESANAN_DELETE)
    fun deleteDataUkuranDetailPesanan(
        @Path("id_ukuran_detail_pesanan") id_ukuran_detail_pesanan_path: Int
    ): Call<Success<UkuranDetailPesanan>>
}
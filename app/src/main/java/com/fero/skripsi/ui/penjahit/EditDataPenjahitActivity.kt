package com.fero.skripsi.ui.penjahit

import android.Manifest
import android.app.Activity
import android.app.TimePickerDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.fero.skripsi.R
import com.fero.skripsi.databinding.ActivityEditDataPenjahitBinding
import com.fero.skripsi.model.Penjahit
import com.fero.skripsi.ui.main.HomePelangganActivity
import com.fero.skripsi.ui.main.HomePenjahitActivity
import com.fero.skripsi.ui.pelanggan.AuthPelangganViewModel
import com.fero.skripsi.utils.PrefHelper
import com.fero.skripsi.utils.PrefHelper.Companion.PREF_ID_PENJAHIT
import com.fero.skripsi.utils.PrefHelper.Companion.PREF_NAMA_PENJAHIT
import com.fero.skripsi.utils.ViewModelFactory
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_edit_data_penjahit.*
import java.text.SimpleDateFormat
import java.util.*

class EditDataPenjahitActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditDataPenjahitBinding
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var prefHelper: PrefHelper

    companion object {
        //image pick code
        private val IMAGE_PICK_CODE = 1000

        //Permission code
        private val PERMISSION_CODE = 1001

        const val EXTRA_DATA_PENJAHIT = "EXTRA_DATA_PENJAHIT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditDataPenjahitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Edit Data Penjahit"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        prefHelper = PrefHelper(this)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[AuthPenjahitViewModel::class.java]

        viewModel.apply {
            dataPenjahit.observe(this@EditDataPenjahitActivity, {
                prefHelper.clear()
                finish()
            })

            messageSuccess.observe(this@EditDataPenjahitActivity, {
                Toast.makeText(this@EditDataPenjahitActivity, it, Toast.LENGTH_SHORT).show()
            })

            showProgress.observe(this@EditDataPenjahitActivity, {
                if (it) {
                    // show progress
                } else {
                    // hide progress
                }
            })

            messageFailed.observe(this@EditDataPenjahitActivity, {
                Toast.makeText(this@EditDataPenjahitActivity, it, Toast.LENGTH_SHORT).show()
            })
        }

        val extraData: Penjahit? = intent.extras?.getParcelable(EXTRA_DATA_PENJAHIT)

        binding.tvNamaPenjahit.text = extraData!!.nama_penjahit
        binding.etNama.setText(extraData.nama_penjahit)
        binding.etNamaToko.setText(extraData.nama_toko)
        binding.etTelepon.setText(extraData.telp_penjahit)
        binding.etAlamatToko.setText(extraData.alamat_penjahit)
        binding.etLatitude.setText(extraData.latitude_penjahit)
        binding.etLongitude.setText(extraData.longitude_penjahit)
        binding.etKet.setText(extraData.keterangan_toko)
        binding.tvJamBuka.text = extraData.jam_buka
        binding.tvJamTutup.text = extraData.jam_tutup

        initLocationProviderClient()
        binding.btnGetLoc.setOnClickListener {
            getUserLocation()
        }

        binding.btnJamBuka.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                binding.tvJamBuka.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(
                this,
                timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true
            ).show()
        }

        binding.btnJamTutup.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                binding.tvJamTutup.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(
                this,
                timeSetListener,
                cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE),
                true
            ).show()
        }

        binding.btnPickImage.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED
                ) {
                    //permission denied
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    //show popup to request runtime permission
                    requestPermissions(permissions, PERMISSION_CODE)
                } else {
                    //permission already granted
                    pickImageFromGallery()
                }
            } else {
                //system OS is < Marshmallow
                pickImageFromGallery()
            }
        }

        binding.btnCancelDataPenjahit.setOnClickListener {
            val moveIntent = Intent(this, HomePenjahitActivity::class.java)
            startActivity(moveIntent)
        }

        binding.btnSimpanDataPenjahit.setOnClickListener {

            val namaPenjahit = binding.etNama.text.toString().trim()
            val teleponPenjahit = binding.etTelepon.text.toString().trim()
            val namaToko = binding.etTelepon.text.toString().trim()
            val alamatPenjahit = binding.etAlamatToko.text.toString().trim()
            val lat = binding.etLatitude.text.toString().trim()
            val long = binding.etLongitude.text.toString().trim()
            val keterangan = binding.etKet.text.toString().trim()
            val jamBuka = binding.tvJamBuka.text.toString()
            val jamTutup = binding.tvJamTutup.text.toString()

            var jangkauan: String? = ""
            if (binding.cbJang1.isChecked) {
                jangkauan += "Anak-anak, "
            }
            if (binding.cbJang2.isChecked) {
                jangkauan += "Ramaja, Dewasa"
            }


            val dataPenjahit = Penjahit(
                extraData.id_penjahit,
                namaPenjahit,
                extraData.email_penjahit,
                extraData.password_penjahit,
                alamatPenjahit,
                "",
                namaToko,
                keterangan,
                teleponPenjahit,
                getHariBuka(),
                jamBuka,
                jamTutup,
                jangkauan,
                lat,
                long,
                getSpesifikasi(),
            )


            Log.d("Data Penjahit : ", dataPenjahit.toString())

            prefHelper.put(PREF_ID_PENJAHIT, extraData.id_penjahit!!)
            prefHelper.put(PREF_NAMA_PENJAHIT, namaPenjahit)

            viewModel.updatePenjahit(dataPenjahit)
//            Toast.makeText(this, "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show()
//            val moveIntent = Intent(this, HomePenjahitActivity::class.java)
//            startActivity(moveIntent)
        }

    }

    private fun getUserLocation() {

        val geocoder = Geocoder(this, Locale.getDefault())
        var addresses: List<Address>

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                1
            )
            return
        } else {
            fusedLocationProviderClient.lastLocation.addOnSuccessListener { location: Location? ->

                var lat = location?.latitude.toString()
                var long = location?.longitude.toString()

                binding.etLatitude.setText(lat)
                binding.etLongitude.setText(long)

                binding.tvLatitude.text = "Latitude : " + location?.latitude
                binding.tvLongitude.text = "Longitude : " + location?.longitude

                addresses = geocoder.getFromLocation(location!!.latitude, location.longitude, 1)
                val address: String = addresses[0].getAddressLine(0)

                binding.etAlamatToko.setText(address)
            }
        }
    }

    private fun initLocationProviderClient() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
    }

    private fun pickImageFromGallery() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    //handle requested permission result
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //permission from popup granted
                    pickImageFromGallery()
                } else {
                    //permission from popup denied
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    //handle result of picked image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            binding.imageView.setImageURI(data?.data)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun getHariBuka(): String {
        var hariBuka = ""
        if (binding.cbHari1.isChecked) {
            hariBuka += "Senin, "
        }
        if (binding.cbHari2.isChecked) {
            hariBuka += "Selasa, "
        }
        if (binding.cbHari3.isChecked) {
            hariBuka += "Rabu, "
        }
        if (binding.cbHari4.isChecked) {
            hariBuka += "Kamis, "
        }
        if (binding.cbHari5.isChecked) {
            hariBuka += "Jumat, "
        }
        if (binding.cbHari6.isChecked) {
            hariBuka += "Sabtu, "
        }
        if (binding.cbHari7.isChecked) {
            hariBuka += "Minggu, "
        }
        return hariBuka
    }

    fun getSpesifikasi(): String {
        var spesifikasi = ""
        if (binding.cbSpek1.isChecked) {
            spesifikasi += "Laki-laki, "
        }
        if (binding.cbSpek2.isChecked) {
            spesifikasi += "Perempuan"
        }
        return spesifikasi
    }


}
package com.fero.skripsi.ui.pelanggan.auth

import android.Manifest
import android.app.Activity
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
import com.fero.skripsi.databinding.ActivityEditDataPelangganBinding
import com.fero.skripsi.model.Pelanggan
import com.fero.skripsi.ui.pelanggan.auth.viewmodel.AuthPelangganViewModel
import com.fero.skripsi.utils.PrefHelper
import com.fero.skripsi.utils.PrefHelper.Companion.PREF_ID_PELANGGAN
import com.fero.skripsi.utils.PrefHelper.Companion.PREF_NAMA_PELANGGAN
import com.fero.skripsi.utils.ViewModelFactory
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.android.synthetic.main.activity_edit_data_pelanggan.*
import java.util.*

class EditDataPelangganActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditDataPelangganBinding
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var prefHelper: PrefHelper

    companion object {
        //image pick code
        private val IMAGE_PICK_CODE = 1000
        //Permission code
        private val PERMISSION_CODE = 1001

        const val EXTRA_DATA_PELANGGAN = "EXTRA_DATA_PELANGGAN"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditDataPelangganBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Edit Data Pelanggan"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        prefHelper = PrefHelper(this)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[AuthPelangganViewModel::class.java]

        viewModel.apply {
            dataPelanggan.observe(this@EditDataPelangganActivity, {
                prefHelper.clear()
//                val moveIntent = Intent(this@EditDataPelangganActivity, HomePelangganActivity::class.java)
//                intent.putExtra("EXTRA_LOGIN_PELANGGAN", it)
//                startActivity(moveIntent)
                finish()
            })

            messageSuccess.observe(this@EditDataPelangganActivity, {
                Toast.makeText(this@EditDataPelangganActivity, it, Toast.LENGTH_SHORT).show()
            })

            showProgress.observe(this@EditDataPelangganActivity, {
                if (it) {
                    // show progress
                } else {
                    // hide progress
                }
            })

            messageFailed.observe(this@EditDataPelangganActivity, {
                Toast.makeText(this@EditDataPelangganActivity, it, Toast.LENGTH_SHORT).show()
            })
        }

        val extraData: Pelanggan? = intent.extras?.getParcelable(EXTRA_DATA_PELANGGAN)

        binding.tvNamaPelanggan.text = extraData!!.nama_pelanggan
        binding.etNama.setText(extraData.nama_pelanggan)
        binding.etTelepon.setText(extraData.telp_pelanggan)
        binding.etAlamat.setText(extraData.alamat_pelanggan)
        binding.etLatitude.setText(extraData.latitude_pelanggan)
        binding.etLongitude.setText(extraData.longitude_pelanggan)

        initLocationProviderClient()
        binding.btnGetLoc.setOnClickListener {
            getUserLocation()
        }

        binding.btnPickImage.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED) {
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

        binding.btnCancelDataPelanggan.setOnClickListener {
            finish()
        }

        binding.btnSimpanDataPelanggan.setOnClickListener {

            val namaPelanggan = binding.etNama.text.toString().trim()
            val teleponPelanggan = binding.etTelepon.text.toString().trim()
            val alamatPelanggan = binding.etAlamat.text.toString().trim()
            val lat = binding.etLatitude.text.toString().trim()
            val long = binding.etLongitude.text.toString().trim()

            var value = "laki-laki"
            when (binding.rgJk.checkedRadioButtonId) {
                R.id.rb_jk_1 -> value = "laki-laki"
                R.id.rb_jk_2 -> value = "perempuan"
            }
            val jkPelanggan = value

            val dataPelangan = Pelanggan(
                extraData.id_pelanggan,
                namaPelanggan,
                extraData.email_pelanggan,
                extraData.password_pelanggan,
                alamatPelanggan,
                jkPelanggan,
                lat,
                long,
                teleponPelanggan,
                "",
            )

            Log.d("Data Pelanggan", dataPelangan.toString())

            prefHelper.put(PREF_ID_PELANGGAN, extraData.id_pelanggan!!)
            prefHelper.put(PREF_NAMA_PELANGGAN, namaPelanggan)

            viewModel.updatePelanggan(dataPelangan)
        }
    }

    private fun getUserLocation() {

        val geocoder = Geocoder(this, Locale.getDefault())
        var addresses:List<Address>

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
            return
        } else {
            fusedLocationProviderClient.lastLocation.addOnSuccessListener { location: Location? ->

                var lat = location?.latitude.toString()
                var long = location?.longitude.toString()

                binding.etLatitude.setText(lat)
                binding.etLongitude.setText(long)

                binding.tvLatitude.text = "Latitude : " + location?.latitude
                binding.tvLongitude.text = "Longitude : " + location?.longitude

                addresses = geocoder.getFromLocation(location!!.latitude,location.longitude,1)
                val address: String = addresses[0].getAddressLine(0)

                binding.etAlamat.setText(address)

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

    override fun onResume() {
        super.onResume()

    }

}
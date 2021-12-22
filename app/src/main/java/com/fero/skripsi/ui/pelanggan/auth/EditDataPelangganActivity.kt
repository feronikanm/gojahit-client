package com.fero.skripsi.ui.pelanggan.auth

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.lifecycle.ViewModelProvider
import com.fero.skripsi.R
import com.fero.skripsi.databinding.ActivityEditDataPelangganBinding
import com.fero.skripsi.model.Pelanggan
import com.fero.skripsi.ui.main.HomePelangganActivity
import com.fero.skripsi.ui.main.PilihUserActivity
import com.fero.skripsi.ui.pelanggan.auth.viewmodel.AuthPelangganViewModel
import com.fero.skripsi.utils.PrefHelper
import com.fero.skripsi.utils.PrefHelper.Companion.PREF_ALAMAT_PELANGGAN
import com.fero.skripsi.utils.PrefHelper.Companion.PREF_FOTO_PELANGGAN
import com.fero.skripsi.utils.PrefHelper.Companion.PREF_ID_PELANGGAN
import com.fero.skripsi.utils.PrefHelper.Companion.PREF_JK_PELANGGAN
import com.fero.skripsi.utils.PrefHelper.Companion.PREF_LATITUDE_PELANGGAN
import com.fero.skripsi.utils.PrefHelper.Companion.PREF_LONGITUDE_PELANGGAN
import com.fero.skripsi.utils.PrefHelper.Companion.PREF_NAMA_PELANGGAN
import com.fero.skripsi.utils.PrefHelper.Companion.PREF_TELP_PELANGGAN
import com.fero.skripsi.utils.ViewModelFactory
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import kotlinx.android.synthetic.main.activity_edit_data_pelanggan.*
import java.io.File
import java.io.IOException
import java.lang.IndexOutOfBoundsException
import java.util.*

class EditDataPelangganActivity : AppCompatActivity(), OnMapReadyCallback, LocationListener, GoogleMap.OnCameraMoveListener, GoogleMap.OnCameraMoveStartedListener, GoogleMap.OnCameraIdleListener  {

    private lateinit var binding: ActivityEditDataPelangganBinding
    private lateinit var currentLocation : Location
    private var mMap: GoogleMap? = null
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    val REQUEST_CODE = 101
    lateinit var prefHelper: PrefHelper

    private val setupDataPelanggan = Pelanggan(null, "", "", "", "", "", "", "", "", "")

    companion object {
        const val EXTRA_DATA_PELANGGAN = "EXTRA_DATA_PELANGGAN"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditDataPelangganBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        fetchLocation()

        prefHelper = PrefHelper(this)

        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[AuthPelangganViewModel::class.java]

        val extraData: Pelanggan? = intent.extras?.getParcelable(EXTRA_DATA_PELANGGAN)

        viewModel.apply {
            dataPelangganVM.observe(this@EditDataPelangganActivity,{
                binding.tvNamaPelanggan.text = it!!.nama_pelanggan
                binding.etNama.setText(it.nama_pelanggan)
                binding.etTelepon.setText(it!!.telp_pelanggan)
                binding.etAlamat.setText(it.alamat_pelanggan)
                binding.tvAlamat.setText(it.alamat_pelanggan)
                binding.tvLatitude.setText(it.latitude_pelanggan)
                binding.tvLongitude.setText(it.longitude_pelanggan)

            })
            viewModel.getDataPelangganById(extraData!!)


            dataPelanggan.observe(this@EditDataPelangganActivity, {
                val move = Intent(this@EditDataPelangganActivity, HomePelangganActivity::class.java)
                move.putExtra("EXTRA_LOGIN_PELANGGAN", extraData)
                startActivity(move)
//                finish()
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



//        binding.tvNamaPelanggan.text = extraData!!.nama_pelanggan
//        binding.etNama.setText(extraData.nama_pelanggan)
//        binding.etTelepon.setText(extraData.telp_pelanggan)
//        binding.etAlamat.setText(extraData.alamat_pelanggan)
//        binding.tvAlamat.setText(extraData.alamat_pelanggan)
//        binding.tvLatitude.setText(extraData.latitude_pelanggan)
//        binding.tvLongitude.setText(extraData.longitude_pelanggan)

        binding.tvPilihAlamat.visibility = View.GONE
        binding.googleMap.visibility = View.GONE

        binding.btnGetMaps.setOnClickListener {
            binding.tvPilihAlamat.visibility = View.VISIBLE
            binding.googleMap.visibility = View.VISIBLE
            binding.tvPerbaruiAlamat.visibility = View.GONE
            binding.btnGetMaps.visibility = View.GONE
//            val moveIntent = Intent(this, MapsPelangganActivity::class.java)
//            startActivity(moveIntent)
        }


        binding.btnCancelDataPelanggan.setOnClickListener {
            finish()
        }

        binding.btnSimpanDataPelanggan.setOnClickListener {

            val namaPelanggan = binding.etNama.text.toString().trim()
            val teleponPelanggan = binding.etTelepon.text.toString().trim()
            val alamatPelanggan = binding.etAlamat.text.toString().trim()
            val lat = binding.tvLatitude.text.toString().trim()
            val long = binding.tvLongitude.text.toString().trim()

            var value = "laki-laki"
            when (binding.rgJk.checkedRadioButtonId) {
                R.id.rb_jk_1 -> value = "laki-laki"
                R.id.rb_jk_2 -> value = "perempuan"
            }
            val jkPelanggan = value

            setupDataPelanggan.id_pelanggan = extraData!!.id_pelanggan
            setupDataPelanggan.nama_pelanggan = namaPelanggan
            setupDataPelanggan.email_pelanggan = extraData.email_pelanggan
            setupDataPelanggan.password_pelanggan = extraData.password_pelanggan
            setupDataPelanggan.jk_pelanggan = jkPelanggan
            setupDataPelanggan.telp_pelanggan = teleponPelanggan
            setupDataPelanggan.alamat_pelanggan = alamatPelanggan
            setupDataPelanggan.latitude_pelanggan = lat
            setupDataPelanggan.longitude_pelanggan = long


//            val dataPelangan = Pelanggan(
//                extraData.id_pelanggan,
//                namaPelanggan,
//                extraData.email_pelanggan,
//                extraData.password_pelanggan,
//                alamatPelanggan,
//                jkPelanggan,
//                lat,
//                long,
//                teleponPelanggan,
//                "",
//            )

            Log.d("Data Pelanggan", setupDataPelanggan.toString())

            prefHelper.put(PREF_ID_PELANGGAN, extraData.id_pelanggan!!)
            prefHelper.put(PREF_NAMA_PELANGGAN, namaPelanggan)

            viewModel.updatePelanggan(setupDataPelanggan)
        }
    }

    private fun fetchLocation() {

        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE)
            return
        }

        val task = fusedLocationProviderClient.lastLocation
        task.addOnSuccessListener { location ->
            if (location != null){
                currentLocation = location
                val supportMapFragment = (supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment)
                supportMapFragment.getMapAsync(this)
            }
        }

    }
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        getMyLocation()
    }

    private fun getMyLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        mMap!!.isMyLocationEnabled = true

        mMap!!.uiSettings.isZoomControlsEnabled = true
        mMap!!.setOnCameraMoveListener(this)
        mMap!!.setOnCameraMoveStartedListener(this)
        mMap!!.setOnCameraIdleListener(this)

        mMap!!.setOnMyLocationChangeListener { location ->
            currentLocation = location
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            REQUEST_CODE -> {
                if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    fetchLocation()
                }
            }
        }
    }

    override fun onLocationChanged(location: Location) {
        val geocoder = Geocoder(this, Locale.getDefault())
        var addresses : List<Address>? = null
        try {
            addresses = geocoder.getFromLocation(location.latitude, location.longitude, 1)
        }catch (e: IOException){
            e.printStackTrace()
        }
        setAddress(addresses!![0])
    }

    private fun setAddress(addresses: Address) {
        if (addresses != null){
            if (addresses.getAddressLine(0) != null){
                binding.tvAlamat.setText(addresses.getAddressLine(0))
                binding.etAlamat.visibility = View.VISIBLE
                binding.etAlamat.setText(addresses.getAddressLine(0))
            }
            if (addresses.getAddressLine(1) != null){
                binding.tvAlamat.setText(binding.tvAlamat.text.toString() + addresses.getAddressLine(1))
                binding.etAlamat.visibility = View.VISIBLE
                binding.etAlamat.setText(binding.etAlamat.text.toString() + addresses.getAddressLine(1))
            }
        }
    }

    override fun onCameraMove() {
    }

    override fun onCameraMoveStarted(p0: Int) {
    }

    override fun onCameraIdle() {
        var addresses: List<Address>? = null
        val geocoder = Geocoder(this, Locale.getDefault())
        try {
            addresses = geocoder.getFromLocation( mMap!!.getCameraPosition().target.latitude, mMap!!.getCameraPosition().target.longitude, 1)

            val lat = mMap!!.cameraPosition.target.latitude
            val lng = mMap!!.cameraPosition.target.longitude

            binding.tvLatitude.text = lat.toString()
            binding.tvLongitude.text = lng.toString()

            setAddress(addresses!![0])
        }catch (e: IndexOutOfBoundsException){
            e.printStackTrace()
        }catch (e: IOException){
            e.printStackTrace()
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
}
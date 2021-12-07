package com.fero.skripsi.ui.pelanggan.auth

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Address
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.fero.skripsi.R
import com.fero.skripsi.databinding.ActivityMapsPelangganBinding
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationListener
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import java.io.IOException
import java.lang.Exception
import java.lang.IndexOutOfBoundsException
import java.util.*

class MapsPelangganActivity : AppCompatActivity(), OnMapReadyCallback, LocationListener, GoogleMap.OnCameraMoveListener, GoogleMap.OnCameraMoveStartedListener, GoogleMap.OnCameraIdleListener  {

    private lateinit var binding: ActivityMapsPelangganBinding
    private lateinit var currentLocation : Location
    private var mMap: GoogleMap? = null
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    val REQUEST_CODE = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsPelangganBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        fetchLocation()


    }

    private fun fetchLocation() {

        if(ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), REQUEST_CODE)
//            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION), REQUEST_CODE)
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
            val latlng = LatLng(currentLocation.latitude, currentLocation.longitude)
//            val markerOptions = MarkerOptions().position(latlng).title("Saya disini")
//                .snippet(getAddress(currentLocation.latitude, currentLocation.longitude))
//            mMap!!.addMarker(markerOptions)
//            mMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 19f))
//            mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(latlng, 18f))

//            binding.tvLatitude.text = "latitude : " + currentLocation.latitude
//            binding.tvLongitude.text = "longitude : " + currentLocation.longitude

        }

    }

    private fun getAddress(latitude: Double, longitude: Double): String? {
        val geoCoder = Geocoder(this, Locale.getDefault())
        val addresses = geoCoder.getFromLocation(latitude, longitude, 1)
        return addresses[0].getAddressLine(0).toString()
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
                binding.etAlamat.setText(addresses.getAddressLine(0))

            }
            if (addresses.getAddressLine(1) != null){
                binding.tvAlamat.setText(binding.tvAlamat.text.toString() + addresses.getAddressLine(1))
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
//            val latlng = LatLng(lat, lng)
//
//            mMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(latlng, 19f))


            binding.tvLatitude.text = "latitude : " + lat
            binding.tvLongitude.text = "longitude : " + lng

            setAddress(addresses!![0])
        }catch (e: IndexOutOfBoundsException){
            e.printStackTrace()
        }catch (e: IOException){
            e.printStackTrace()
        }
    }
}
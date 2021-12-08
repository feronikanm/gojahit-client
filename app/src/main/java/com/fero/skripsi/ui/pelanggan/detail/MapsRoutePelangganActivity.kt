package com.fero.skripsi.ui.pelanggan.detail

import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.location.Geocoder
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import com.fero.skripsi.R
import com.fero.skripsi.databinding.ActivityMapsRoutePelangganBinding
import com.fero.skripsi.model.DetailKategoriNilai
import com.fero.skripsi.model.Pelanggan
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
import java.util.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory





class MapsRoutePelangganActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var binding: ActivityMapsRoutePelangganBinding
    private lateinit var currentLocation : Location
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private var mMap: GoogleMap? = null
    val REQUEST_CODE = 103
    companion object {
        const val EXTRA_DATA_PENJAHIT = "EXTRA_DATA_PENJAHIT"
        const val EXTRA_DATA_PELANGGAN = "EXTRA_DATA_PELANGGAN"
    }

    private val extraDataNilai: DetailKategoriNilai? by lazy {
        intent.getParcelableExtra(EXTRA_DATA_PENJAHIT)
    }

    private val extraDataPelanggan: Pelanggan? by lazy {
        intent.getParcelableExtra(EXTRA_DATA_PELANGGAN)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapsRoutePelangganBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvIdPelanggan.text = "Nama : " + extraDataPelanggan!!.nama_pelanggan + ", Id Pelanggan : " + extraDataPelanggan!!.id_pelanggan
        binding.tvIdPenjahit.text = "Nama : " + extraDataNilai!!.nama_penjahit + ", Id Pelanggan : " + extraDataNilai!!.id_penjahit

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        fetchLocation()

    }

    private fun fetchLocation() {

        if(ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
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

        mMap!!.setOnMyLocationChangeListener { location ->
            currentLocation = location
            val latlng = LatLng(location.latitude, location.longitude)
//            val markerOptions = MarkerOptions().position(latlng).title("Saya disini")
//                .snippet(getAddress(currentLocation.latitude, currentLocation.longitude))
//            mMap!!.addMarker(markerOptions)

            val pelangganLat = extraDataPelanggan?.latitude_pelanggan
            val pelangganLng = extraDataPelanggan?.longitude_pelanggan

            Log.d("pelangganLat : ", pelangganLat.toString())
            Log.d("pelangganLng : ", pelangganLng.toString())

            val pelangganLatDouble : Double = pelangganLat!!.toDouble()
            val pelangganLngDouble : Double = pelangganLng!!.toDouble()

            Log.d("pelangganLatDouble : ", pelangganLatDouble.toString())
            Log.d("pelangganLngDouble : ", pelangganLngDouble.toString())

            val pelangganLatLng = LatLng(pelangganLatDouble, pelangganLngDouble)
            Log.d("pelangganLatLng : ", pelangganLatLng.toString())

            val icon = BitmapDescriptorFactory.fromResource(R.drawable.ic_map)

            val markerOptions = MarkerOptions().position(pelangganLatLng)
                .title("Lokasi Saya")
                .snippet(getAddress(pelangganLatDouble, pelangganLngDouble))
//                .icon(icon)
            mMap!!.addMarker(markerOptions)


            val penjahitLat = extraDataNilai?.latitude_penjahit
            val penjahitLng = extraDataNilai?.longitude_penjahit

            Log.d("penjahitLat : ", penjahitLat.toString())
            Log.d("penjahitLng : ", penjahitLng.toString())

            val penjahitLatDouble : Double = penjahitLat!!.toDouble()
            val penjahitLngDouble : Double = penjahitLng!!.toDouble()

            Log.d("penjahitLatDouble : ", penjahitLatDouble.toString())
            Log.d("penjahitLngDouble : ", penjahitLngDouble.toString())

            val penjahitLatLng = LatLng(penjahitLatDouble, penjahitLngDouble)
            Log.d("penjahitLatLng : ", penjahitLatLng.toString())


            val markerPenjahit = MarkerOptions().position(penjahitLatLng).title("Lokasi Penjahit")
                .snippet(getAddress(penjahitLatDouble, penjahitLngDouble))
            mMap!!.addMarker(markerPenjahit)

            mMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(pelangganLatLng, 15f))

            mMap!!.addPolyline(
                PolylineOptions().add(penjahitLatLng, pelangganLatLng)
                    .width // below line is use to specify the width of poly line.
                        (10f) // below line is use to add color to our poly line.
                    .color(Color.BLUE) // below line is to make our poly line geodesic.
                    .geodesic(true)
            )
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

}
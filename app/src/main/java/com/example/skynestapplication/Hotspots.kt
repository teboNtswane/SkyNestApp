package com.example.skynestapplication

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.media.MediaPlayer.OnCompletionListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import java.io.IOException

class Hotspots : AppCompatActivity(), OnMapReadyCallback {

    private var  mMap: GoogleMap? = null

    lateinit var mapView: MapView

    private val MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey"

    private val DEFAULT_ZOOM = 15f

    private  var fusedLocationClient: FusedLocationProviderClient? = null

    lateinit var foundAddress: TextView

    lateinit var kiloM: Button

    lateinit var miles: Button

    var end_latitude = 0.0

    var end_longitude = 0.0

    private var origin: MarkerOptions? = null

    private var destination: MarkerOptions? = null

    var latitude = 0.0

    var longitude = 0.0

    override fun onMapReady(googleMap: GoogleMap) {

       mapView.onResume()
        mMap = googleMap

        askPermissionLocation()

        if(ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) != PackageManager.PERMISSION_GRANTED) {
            return
        }
        mMap!!.setMyLocationEnabled(true)

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotspots)

        mapView = findViewById<MapView>(R.id.my_map)

        foundAddress = findViewById<TextView>(R.id.tv_display)

        kiloM = findViewById<Button>(R.id.btn_find)

        miles = findViewById<Button>(R.id.btn_miles)


        askPermissionLocation()

        var mapViewBundle: Bundle? = null
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY)
        }

        mapView.onCreate(mapViewBundle)
        mapView.getMapAsync(this)

        kiloM.setOnClickListener {
            searchAreaKM()
        }

        miles.setOnClickListener {
            searchAreaMi()
        }

    }


    private fun searchAreaMi() {
        val searchLocation = findViewById<View>(R.id.et_location) as EditText
        val theLocation = searchLocation.text.toString()
        var  addressList: List<Address>? = null
        val markerOptions = MarkerOptions()

        Log.d("location = ", theLocation)
        if(theLocation != "") {
            val geocoder = Geocoder(applicationContext)
            try {
                addressList = geocoder.getFromLocationName(theLocation, 5)
            } catch (e: IOException) {
                e.printStackTrace()
            }

            if (addressList != null) {
                for (a in addressList.indices) {

                    val myAddress = addressList[a]
                    val latLng = LatLng(myAddress.latitude, myAddress.longitude)
                    val markOpt = MarkerOptions()
                    val results = FloatArray(10)


                    markerOptions.position(latLng)
                    mMap!!.addMarker(markerOptions)
                    end_latitude = myAddress.latitude
                    end_longitude = myAddress.longitude
                    mMap!!.animateCamera(CameraUpdateFactory.newLatLng(latLng))

                    markOpt.title("Distance")
                    Location.distanceBetween(
                        latitude,
                        longitude,
                        end_latitude,
                        end_longitude,
                        results
                    )

                    val str = String.format("%.1f", results[0]/ (1000 * 0.6213721192))

                    //This sets the marker to draw route between the two points
                    origin = MarkerOptions().position(LatLng(latitude,longitude))
                        .title("You").snippet("origin")
                    destination = MarkerOptions().position(LatLng(end_latitude, end_longitude))
                        .title(searchLocation.text.toString())
                        .snippet("Distance = $str miles")
                    destination?.let { node -> mMap!!.addMarker(node) }
                    origin?.let { node1 -> mMap!!.addMarker(node1) }

                   // mMap!!.addMarker(destination)
                    //mMap!!.addMarker(origin!!)

                    Toast.makeText(this@Hotspots, "Distance = $str miles", Toast.LENGTH_SHORT).show()

                    foundAddress.setText("Distance = $str miles")

                }
            }
        }
    }

    private fun searchAreaKM() {
        val searchLocation = findViewById<View>(R.id.et_location) as EditText
        val theLocation = searchLocation.text.toString()
        var  addressList: List<Address>? = null
        val markerOptions = MarkerOptions()

        Log.d("location = ", theLocation)
        if(theLocation != "") {
            val geocoder = Geocoder(applicationContext)
            try {
                addressList = geocoder.getFromLocationName(theLocation, 5)
            } catch (e: IOException) {
                e.printStackTrace()
            }

            if (addressList != null) {
                for (a in addressList.indices) {

                    val myAddress = addressList[a]
                    val latLng = LatLng(myAddress.latitude, myAddress.longitude)
                    val markOpt = MarkerOptions()
                    val results = FloatArray(10)


                    markerOptions.position(latLng)
                    mMap!!.addMarker(markerOptions)
                    end_latitude = myAddress.latitude
                    end_longitude = myAddress.longitude
                    mMap!!.animateCamera(CameraUpdateFactory.newLatLng(latLng))

                    markOpt.title("Distance")
                    Location.distanceBetween(
                        latitude,
                        longitude,
                        end_latitude,
                        end_longitude,
                        results
                    )

                    val str = String.format("%.1f", results[0]/ 1000)

                    //This sets the marker to draw route between the two points
                    origin = MarkerOptions().position(LatLng(latitude,longitude))
                        .title("You").snippet("origin")
                    destination = MarkerOptions().position(LatLng(end_latitude, end_longitude))
                        .title(searchLocation.text.toString())
                        .snippet("Distance = $str KM")


                    destination?.let { node -> mMap!!.addMarker(node) }
                    origin?.let { node -> mMap!!.addMarker(node) }

                    //mMap!!.addMarker(destination)
                    //mMap!!.addMarker(origin ?)

                    Toast.makeText(this@Hotspots, "Distance = $str KM", Toast.LENGTH_SHORT).show()

                    foundAddress!!.setText("Distance = $str KM")

                }
            }
        }
    }

    public override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        askPermissionLocation()

        var mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY)
        if (mapViewBundle == null) {
            mapViewBundle = Bundle()
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle)
        }

        mapView.onSaveInstanceState(mapViewBundle)
    }


    private fun askPermissionLocation() {

        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 100)
            return
        }

        getCurrentLocation()

    }

    @SuppressLint("MissingPermission")
    private fun getCurrentLocation() {

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this@Hotspots)

        try{
            val location =  fusedLocationClient!!.getLastLocation()

            location.addOnCompleteListener(object : OnCompleteListener<Location> {
                override fun onComplete(loc: Task<Location>) {
                    if (loc.isSuccessful) {

                        val currentLocation = loc.result as Location?
                        if (currentLocation != null) {
                            moveCamera(
                                LatLng(currentLocation.latitude, currentLocation.longitude),
                                DEFAULT_ZOOM
                            )

                            latitude = currentLocation.latitude
                            longitude = currentLocation.longitude

                        }
                    } else {
                        askPermissionLocation()

                    }
                }
            })
        } catch (se: Exception) {
            Log.e("TAG", "Security Exception")
        }

    }

    private fun placeMarkerOnMap(currentLatLong: LatLng) {
        val markerOptions = MarkerOptions().position(currentLatLong)
        markerOptions.title("$currentLatLong")
        mMap!!.addMarker((markerOptions))

    }


    private fun moveCamera(latLng: LatLng, zoom: Float) {
        mMap!!.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom))
    }
}
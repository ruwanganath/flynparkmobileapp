package com.example.flynparkmelb.ui.searchparking


import android.Manifest
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.android.volley.*
import com.android.volley.toolbox.BasicNetwork
import com.android.volley.toolbox.DiskBasedCache
import com.android.volley.toolbox.HurlStack
import com.android.volley.toolbox.StringRequest
import com.example.flynparkmelb.GpsTracker
import com.example.flynparkmelb.R
import com.example.flynparkmelb.databinding.FragmentSearchparkingBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions

import com.sothree.slidinguppanel.SlidingUpPanelLayout
import org.json.JSONArray
import org.json.JSONObject


class SearchParkingFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private lateinit var searchParkingViewModel: SearchParkingViewModel
    private var _binding: FragmentSearchparkingBinding? = null
    private lateinit var mMap: GoogleMap
    private val LOCATION_PERMISSION_REQUEST = 1
    private var gpsTracker: GpsTracker? = null
    private var jsonArray :JSONArray? =null

    //private val queue = newRequestQueue(this.requireContext())
    // This property is only valid between onC reateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        searchParkingViewModel =
            ViewModelProvider(this).get(SearchParkingViewModel::class.java)

        _binding = FragmentSearchparkingBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //val textView: TextView = binding.textSearchparking
        // searchParkingViewModel.text.observe(viewLifecycleOwner, Observer {
        //      textView.text = it
        //  })

       /* val appnetwork = BasicNetwork(HurlStack())
        val appcache = DiskBasedCache(cacheDir, 1024 * 1024) // 1MB cap
        requestQueue = RequestQueue(appcache, appnetwork).apply {
            start()
        }*/

        val mapFragment = this.childFragmentManager
           .findFragmentById(R.id.parkingMap) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getLocationAccess() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.isMyLocationEnabled = true
        }
        else
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST)
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        if (requestCode == LOCATION_PERMISSION_REQUEST) {
            if (grantResults.contains(PackageManager.PERMISSION_GRANTED)) {
                if (ActivityCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        requireContext(),
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return
                }
                mMap.isMyLocationEnabled = true
            }
            else {
                Toast.makeText(requireContext(), "User has not granted location access permission", Toast.LENGTH_LONG).show()
            }
        }
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        getLocationAccess()
        getLocationMarkers( )
    }

    fun getLocationMarkers( ) {

        lateinit var markerMyLoc: Marker

        gpsTracker = GpsTracker(requireContext())
        if (gpsTracker!!.canGetLocation()) {
            val latitude = gpsTracker!!.getLatitude()
            val longitude = gpsTracker!!.getLongitude()
            val MYLOC = LatLng(latitude, longitude)

            markerMyLoc = mMap.addMarker(
                MarkerOptions()
                    .position(MYLOC)
                    .title("My Current Location")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN))
            )
            markerMyLoc.tag = 0

           getParkingDataAndSetMarkers(latitude,longitude)
        } else {
            gpsTracker!!.showSettingsAlert()
        }
    }

    fun getParkingDataAndSetMarkers(Lat:Double,Lon:Double){
        val url = "https://flynpark.us-south.cf.appdomain.cloud/generateparkingdata?userLatitude=${Lat}&userLongitude=${Lon}"

        // Instantiate the cache
        val cache = DiskBasedCache(context?.cacheDir, 1024 * 1024) // 1MB cap

        // Set up the network to use HttpURLConnection as the HTTP client.
        val network = BasicNetwork(HurlStack())

        // Instantiate the RequestQueue with the cache and network. Start the queue.
        val requestQueue = RequestQueue(cache, network).apply {
            start()
        }



        // Formulate the request and handle the response.
        val stringRequest = StringRequest(Request.Method.GET, url,
            Response.Listener<String> { response ->

                var bitmapDescriptorFactory : Float
                jsonArray = JSONArray(response)
                var parkingType : String

                for (i in 0 until jsonArray!!.length()) {
                    val jsonObj = JSONObject(jsonArray!!.getString(i))
                    val Lat = jsonObj.getString("lat").toDouble()
                    val Lon = jsonObj.getString("lon").toDouble()

                    if(jsonObj.getString("type") == "on"){
                        if(i==0){
                            val zoomTo = LatLng(Lat, Lon)
                            mMap.animateCamera(
                                CameraUpdateFactory.newLatLngZoom(zoomTo, 15f),
                                2000,
                                null
                            )
                            bitmapDescriptorFactory = BitmapDescriptorFactory.HUE_RED
                            parkingType = "Optimum Spot - On Street Parking"
                        } else{
                            bitmapDescriptorFactory = BitmapDescriptorFactory.HUE_GREEN
                            parkingType = "On Street Parking"
                        }

                    }else {
                        if(i==0){
                            val zoomTo = LatLng(Lat, Lon)
                            mMap.animateCamera(
                                CameraUpdateFactory.newLatLngZoom(zoomTo, 15f),
                                2000,
                                null
                            )
                            bitmapDescriptorFactory = BitmapDescriptorFactory.HUE_RED
                            parkingType = "Optimum Spot - Off Street Parking"
                        } else{
                            bitmapDescriptorFactory = BitmapDescriptorFactory.HUE_BLUE
                            parkingType = "Off Street Parking"
                        }

                    }

                    val parkingSpot = LatLng(Lat, Lon)
                    lateinit var markerParking: Marker


                    markerParking = mMap.addMarker(
                        MarkerOptions()
                            .position(parkingSpot)
                            .title(parkingType)
                            .icon(BitmapDescriptorFactory.defaultMarker(bitmapDescriptorFactory))
                    )
                    markerParking.tag = jsonObj.getString("bay").toString()
                    mMap.setOnMarkerClickListener(this)
                }
            },
            { error ->
                // Handle error
                Log.d("Error.Response", "ERROR: %s".format(error.toString()));
            })

        stringRequest.setRetryPolicy(object : RetryPolicy {
            override fun getCurrentTimeout(): Int {
                return 50000
            }
            override fun getCurrentRetryCount(): Int {
                return 50000
            }
            @Throws(VolleyError::class)
            override fun retry(error: VolleyError) {
            }
        })
        // Add the request to the RequestQueue.
        requestQueue.add(stringRequest)
    }

    override fun onMarkerClick(marker: Marker): Boolean {
        val txtViewBayId: TextView = binding.bayId
        val txtViewRate: TextView = binding.rate
        val txtViewType: TextView = binding.type
        val txtViewinfoDesc1: TextView = binding.desc1
        val txtViewinfoDesc2: TextView = binding.desc2

        for (i in 0 until jsonArray!!.length()) {
            val jsonObj = JSONObject(jsonArray!!.getString(i))
            if(marker.tag.toString().equals(jsonObj.getString("bay")))
            {
                txtViewBayId.text = "Bay ID - "+jsonObj.getString("bay").toString()
                txtViewRate.text = jsonObj.getString("rate").toString()
                txtViewinfoDesc1.text = jsonObj.getString("desc1").toString()
                txtViewinfoDesc2.text = jsonObj.getString("desc2").toString()
                if(jsonObj.getString("type").toString().equals("on")){
                    txtViewType.text = "On-Street"
                    if(i==0){
                        txtViewType.setBackgroundColor(Color.parseColor("#FF0000"))
                    }else{
                        txtViewType.setBackgroundColor(Color.parseColor("#00F701"))
                    }
                }else{
                    txtViewType.text = "Off-Street"
                    if(i==0){
                       txtViewType.setBackgroundColor(Color.parseColor("#FF0000"))
                    }else{
                       txtViewType.setBackgroundColor(Color.parseColor("#0031E7"))
                    }
                }

            }
        }

        return false
    }
}


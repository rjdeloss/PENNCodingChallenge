package com.example.penncodingchallenge

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private val LOCATION_PERMISSION_REQUEST_CODE = 1

    private fun checkPermissions() {
        if (!hasLocationPermissions()) {
            requestLocationPermission()
            return
        }
    }

    private fun requestLocationPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
            LOCATION_PERMISSION_REQUEST_CODE
        )
    }

    private fun hasLocationPermissions(): Boolean {
        return hasPermission(android.Manifest.permission.ACCESS_FINE_LOCATION) &&
                hasPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION)
    }

    private fun hasPermission(permission: String): Boolean {
        val result = ActivityCompat.checkSelfPermission(this,permission);

        return result == PackageManager.PERMISSION_GRANTED;
    }

    private lateinit var locationClient: FusedLocationProviderClient

    @SuppressLint("MissingPermission")
    private fun initUpdates(viewModel: MainViewModel) {
        locationClient = LocationServices.getFusedLocationProviderClient(this)

        locationClient.requestLocationUpdates(
            createLocationRequest(),
            { location -> viewModel.update(location.latitude, location.longitude)},
            Looper.getMainLooper()
        )
    }

    private fun createLocationRequest(): LocationRequest {
        return LocationRequest.Builder(1000).build()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        checkPermissions()
        val viewModel = MainViewModel()
        val modifier: Modifier = Modifier

        initUpdates(viewModel)

        setContent {
            val navController = rememberNavController()
            val scope = rememberCoroutineScope()

            NavHost(navController = navController, startDestination = "launch_screen") {
                composable("launch_screen") {
                    LaunchScreen()
                    LaunchedEffect(Unit) {
                        scope.launch {
                            delay(2000)
                            navController.navigate("main_screen") {
                                popUpTo("launch_screen") { inclusive = true }
                            }
                        }
                    }
                }
                composable("main_screen") {
                    MainScreen(modifier, viewModel)
                }
            }

        }
    }
}
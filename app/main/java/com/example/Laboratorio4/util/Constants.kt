package com.example.Laboratorio4.util

import android.os.Build

object Constants {

    const val USER_SETTINGS = "user_settings"

    const val APP_ENTRY = "app_entry"

    const val API_KEY = "bf2e296a2bd9493eb7b4323da1cf5d0b"

    const val BASE_URL = "https://3jeyt5r7o7.execute-api.us-east-1.amazonaws.com"

    const val NEWS_DATABASE_NAME = "pictures_db"

    val PERMISSIONS = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
        arrayOf(
            android.Manifest.permission.BLUETOOTH_CONNECT,
            android.Manifest.permission.BLUETOOTH_SCAN,
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.BLUETOOTH_ADMIN,
            android.Manifest.permission.BLUETOOTH_ADVERTISE
        )
    } else {
        arrayOf(
            android.Manifest.permission.ACCESS_COARSE_LOCATION,
            android.Manifest.permission.ACCESS_FINE_LOCATION,
            android.Manifest.permission.BLUETOOTH_ADMIN,
            )
    }

}
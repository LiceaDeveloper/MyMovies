package com.liceadev.architectcoders.data

import android.app.Application
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat
import com.liceadev.data.PermissionChecker
import android.Manifest

class AndroidPermissionChecker(private val application: Application) : PermissionChecker {


    override suspend fun check(permission: PermissionChecker.Permission): Boolean =
        ContextCompat.checkSelfPermission(
            application,
            permission.toAndroidId()
        ) == PackageManager.PERMISSION_GRANTED
}

private fun PermissionChecker.Permission.toAndroidId() = when (this) {
    PermissionChecker.Permission.COARSE_LOCATION -> Manifest.permission.ACCESS_COARSE_LOCATION
}
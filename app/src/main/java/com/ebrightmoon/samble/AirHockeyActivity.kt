package com.ebrightmoon.samble

import android.app.ActivityManager
import android.content.Context
import android.content.pm.ConfigurationInfo
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ebrightmoon.opengl.AirHockeyGLSurfaceView

/**
 * Time: 2021/9/29
 * Author:wyy
 * Description:
 */
class AirHockeyActivity : AppCompatActivity() {
    private lateinit var gLView: AirHockeyGLSurfaceView
    private var rendererSet = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityManager: ActivityManager =
            getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        val configurationInfo: ConfigurationInfo = activityManager
            .deviceConfigurationInfo
        val supportsEs2 =
            configurationInfo.reqGlEsVersion >= 0x20000
                    || (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1
                    && (Build.FINGERPRINT.startsWith("generic")
                    || Build.FINGERPRINT.startsWith("unknown")
                    || Build.MODEL.contains("google_sdk")
                    || Build.MODEL.contains("Emulator")
                    || Build.MODEL.contains("Android SDK built for x86")));
        if (supportsEs2) {
            gLView = AirHockeyGLSurfaceView(this)
            rendererSet = true
        } else {
            Toast.makeText(
                this, "This device does not support OpenGL ES 2.0.",
                Toast.LENGTH_LONG
            ).show()
            return
        }
        setContentView(gLView)
    }

    override fun onResume() {
        super.onResume()
        if (rendererSet) {
            gLView.onResume()
        }
    }

    override fun onPause() {
        super.onPause()
        if (rendererSet) {
            gLView.onPause()
        }
    }
}
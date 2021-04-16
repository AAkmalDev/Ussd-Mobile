package uz.onveti.ussdmobile

import android.Manifest
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.karumi.dexter.Dexter
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.single.PermissionListener
import kotlinx.android.synthetic.main.activity_main.*
import uz.onveti.ussdmobile.utils.MySharedPreference
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MySharedPreference.getInstance(this)
        setLocal()
        permission()
        settings_ussd.setOnClickListener {
            startActivity(Intent(this, LanguageActivity::class.java))
            finish()
        }
    }

    private fun permission() {
        Dexter.withContext(this)
            .withPermission(Manifest.permission.CALL_PHONE)
            .withListener(object : PermissionListener {
                override fun onPermissionGranted(p0: PermissionGrantedResponse?) {

                }

                override fun onPermissionDenied(p0: PermissionDeniedResponse?) {

                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: PermissionRequest?,
                    p1: PermissionToken?
                ) {
                    TODO("Not yet implemented")
                }
            }).check()
    }

    private fun setLocal() {
        val local = Locale(MySharedPreference.getLanguage())
        Locale.setDefault(local)
        val config: Configuration = resources.configuration
        config.locale = local
        resources.updateConfiguration(config, resources.displayMetrics)
    }
}
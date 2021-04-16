package uz.onveti.ussdmobile

import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import uz.onveti.ussdmobile.utils.MySharedPreference
import java.util.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        MySharedPreference.getInstance(this)
        val handler = Handler()

        if (MySharedPreference.getBoolean() == true) {
            handler.postDelayed(Runnable {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } as Runnable, 2000)
        } else {
            handler.postDelayed(Runnable {
                startActivity(Intent(this, LanguageActivity::class.java))
                finish()
            } as Runnable, 2000)
            MySharedPreference.setBoolean(true)
        }
        setLocal()
    }
    private fun setLocal() {
        val local = Locale(MySharedPreference.getLanguage())
        Locale.setDefault(local)
        val config: Configuration = resources.configuration
        config.locale = local
        resources.updateConfiguration(config, resources.displayMetrics)
    }
}
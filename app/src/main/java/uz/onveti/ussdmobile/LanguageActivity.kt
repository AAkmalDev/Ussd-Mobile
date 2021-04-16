package uz.onveti.ussdmobile

import android.content.Intent
import android.content.res.Configuration
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_language.*
import uz.onveti.ussdmobile.utils.MySharedPreference
import java.util.*

class LanguageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_language)

        val window = this.window
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.parseColor("#FFFFFF")
            window.navigationBarColor = Color.parseColor("#FFFFFF")
        }
        MySharedPreference.getInstance(this)
        setLocal()
        uzbek_btn.setOnClickListener {
            MySharedPreference.setLanguage("uz")
            setLocal()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        russian_btn.setOnClickListener {
            MySharedPreference.setLanguage("ru")
            setLocal()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        kiril_btn.setOnClickListener {
            MySharedPreference.setLanguage("be")
            setLocal()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }


    }

    private fun setLocal() {
        val local = Locale(MySharedPreference.getLanguage())
        Locale.setDefault(local)
        val config: Configuration = resources.configuration
        config.locale = local
        resources.updateConfiguration(config, resources.displayMetrics)
    }
}
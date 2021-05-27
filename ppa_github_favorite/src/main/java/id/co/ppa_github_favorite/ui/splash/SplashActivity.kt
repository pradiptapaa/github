package id.co.ppa_github.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import id.co.ppa_github_favorite.databinding.LayoutSplashBinding
import id.co.ppa_github_favorite.ui.MainActivity

class SplashActivity : AppCompatActivity() {

    private val binding: LayoutSplashBinding by lazy {
        LayoutSplashBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()
        Handler(Looper.getMainLooper()).postDelayed({
            MainActivity.startActivity(this)
            finish()
        }, 1500)
    }
}
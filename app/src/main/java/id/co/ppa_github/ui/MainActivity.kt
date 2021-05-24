package id.co.ppa_github.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import id.co.ppa_github.R
import id.co.ppa_github.databinding.LayoutBottomNavigationBinding

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val binding: LayoutBottomNavigationBinding by lazy {
        LayoutBottomNavigationBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.bottom_navigation_fcv
        ) as NavHostFragment
        val navController = navHostFragment.navController

        // Setup the bottom navigation view with navController
        binding.bottomNavigationView.setupWithNavController(navController)
    }


}
package com.aayar94.valorantguidestats.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.aayar94.valorguidestats.R
import com.aayar94.valorguidestats.databinding.ActivityMainBinding
import com.google.android.play.core.review.ReviewManagerFactory
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val manager = ReviewManagerFactory.create(applicationContext)
        val requestReviewFlow = manager.requestReviewFlow()
        requestReviewFlow.addOnCompleteListener { request ->
            if (request.isSuccessful) {
                val reviewInfo = request.result
                val flow = manager.launchReviewFlow(this, reviewInfo)
                flow.addOnCompleteListener {
                }
            } else {
                Log.e("App Review Error!!", "Review can't work correctly")
            }
        }

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        binding.bottomNavMenu.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.agentDetailsFragment -> binding.bottomNavMenu.visibility = View.GONE
                R.id.weaponDetailsFragment -> binding.bottomNavMenu.visibility = View.GONE
                R.id.mapDetailsFragment -> binding.bottomNavMenu.visibility = View.GONE
                R.id.seasonsFragment -> binding.bottomNavMenu.visibility = View.GONE
                R.id.statsFragment -> binding.bottomNavMenu.visibility = View.GONE
                R.id.weaponsFragment -> binding.bottomNavMenu.visibility = View.GONE
                R.id.mapsFragment -> binding.bottomNavMenu.visibility = View.GONE
                R.id.userMatchDetailsFragment -> binding.bottomNavMenu.visibility = View.GONE
                else -> binding.bottomNavMenu.visibility = View.VISIBLE
            }
        }


    }


    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.nav_host_fragment).navigateUp() || super.onSupportNavigateUp()
    }
}
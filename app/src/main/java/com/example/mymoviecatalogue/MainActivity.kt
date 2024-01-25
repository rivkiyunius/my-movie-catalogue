package com.example.mymoviecatalogue

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.mymoviecatalogue.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private var viewBinding: ActivityMainBinding? = null
    private val viewModel: MainViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                !viewModel.isReady.value
            }
        }
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding?.root)
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.main_nav) as NavHostFragment
        val controller = navHostFragment.navController
        viewBinding?.bottomNav?.setupWithNavController(controller)
    }

    fun showBottomNav() {
        viewBinding?.bottomNav?.visibility = View.VISIBLE
    }

    fun hideBottomNav() {
        viewBinding?.bottomNav?.visibility = View.GONE
    }
}
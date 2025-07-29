package io.saytheirnames.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.saytheirnames.R
import io.saytheirnames.viewmodel.MainViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    private lateinit var bottomNav: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupNavigation()
        setupObservers()
    }

    private fun setupNavigation() {
        bottomNav = findViewById(R.id.nav_view)
        
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        
        bottomNav.setupWithNavController(navController)
    }

    private fun setupObservers() {
        // Observe any global state changes
        viewModel.isLoading.observe(this) { isLoading ->
            // Handle loading state
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        
        // Modern way to clear Glide cache using coroutines
        lifecycleScope.launch {
            clearGlideCache()
        }
    }

    private suspend fun clearGlideCache() = withContext(Dispatchers.IO) {
        // This method must be called on a background thread
        Glide.get(applicationContext).clearDiskCache()
    }
} 
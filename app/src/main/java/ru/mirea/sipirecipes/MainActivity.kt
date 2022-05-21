package ru.mirea.sipirecipes

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.mirea.sipirecipes.data.repository.RecipeRepository
import ru.mirea.sipirecipes.data.repository.UserRepository
import ru.mirea.sipirecipes.databinding.ActivityMainBinding
import ru.mirea.sipirecipes.ui.viewmodel.MainViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appBarConfiguration: AppBarConfiguration
    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var recipeRepository: RecipeRepository

    @Inject
    lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        // setup up drawer and app bar
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        appBarConfiguration = AppBarConfiguration(navController.graph, binding.mainDrawer)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)

        setObservers()

        setContentView(binding.root)
    }

    private fun setObservers() {
        viewModel.isUserPresent.observe(this) {
            if (it) {
                binding.navView.menu.findItem(R.id.nav_login).isVisible = false
                binding.navView.menu.findItem(R.id.nav_register).isVisible = false
                binding.navView.menu.findItem(R.id.nav_add_recipe).isVisible = true
            } else {
                binding.navView.menu.findItem(R.id.nav_login).isVisible = true
                binding.navView.menu.findItem(R.id.nav_register).isVisible = true
                binding.navView.menu.findItem(R.id.nav_add_recipe).isVisible = false
            }
        }
    }
}
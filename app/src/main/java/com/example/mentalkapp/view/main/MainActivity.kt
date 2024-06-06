package com.example.mentalkapp.view.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mentalkapp.R
import com.example.mentalkapp.databinding.ActivityMainBinding
import com.example.mentalkapp.view.ViewModelFactory
import com.example.mentalkapp.view.login.LoginActivity
import com.example.mentalkapp.view.splash.SplashActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getUserSession()
        showRv()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_logout -> {
                viewModel.clearUserSession()
                viewModel.logoutComplete.observe(this) { isLogoutComplete ->
                    if (isLogoutComplete) {
                        navigateToLogin()
                    }
                }
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun getUserSession() {
        viewModel.getUserSession().observe(this) { user ->
            if (!user.isLogin) {
                val welcomeActivity = Intent(this, SplashActivity::class.java)
                welcomeActivity.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(welcomeActivity)
                finish()
            } else {
                viewModel.isLoading.observe(this) { state ->

                }

                }
            }
        }
    }

    private fun showRv() {
        val layoutManager = LinearLayoutManager(this)
        binding.recycleView.layoutManager = layoutManager
    }

    private fun setStoryList(stories: List<ListStoryItem>?) {
        val adapter = UserAdapter()
        adapter.submitList(stories)
        binding.recycleView.adapter = adapter
    }
}
package com.example.mentalkapp.view.main

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.mentalkapp.R
import com.example.mentalkapp.databinding.ActivityMainBinding
import com.example.mentalkapp.view.ImageData
import com.example.mentalkapp.view.ImageSliderAdapter
import com.example.mentalkapp.view.ViewModelFactory
import com.example.mentalkapp.view.login.LoginActivity
import com.example.mentalkapp.view.splash.SplashActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private var _binding: ActivityMainBinding? = null
    private lateinit var adapter: ImageSliderAdapter
    private val list = ArrayList<ImageData>()
    private lateinit var dots: ArrayList<TextView>
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        list.add(
            ImageData(R.drawable.img_6))
        list.add(
            ImageData(R.drawable.img_7))
        list.add(
            ImageData(R.drawable.img_8))

        adapter = ImageSliderAdapter(list)
        binding.carousel.adapter = adapter
        dots = ArrayList()
        setIndicator()

        binding.carousel.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                selectedDot(position)
                super.onPageSelected(position)
            }
        })

        getUserSession()
    }

    private fun selectedDot(position: Int) {
        for (i in 0 until list.size){
            if (i == position)
                dots[i].setTextColor(ContextCompat.getColor(this, R.color.blue))
            else
                dots[i].setTextColor(ContextCompat.getColor(this, R.color.black))
        }
    }
    private fun setIndicator() {
        for (i in 0 until list.size) {
            dots.add(TextView(this))
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                dots[i].text = Html.fromHtml("&#9679", Html.FROM_HTML_MODE_LEGACY).toString()
            } else {
                dots[i].text = Html.fromHtml("&#9679")
            }
            dots[i].textSize = 18f
            binding.indicator.addView(dots[i])
        }
    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
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
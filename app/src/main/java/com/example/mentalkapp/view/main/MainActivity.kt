package com.example.mentalkapp.view.main

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.mentalkapp.R
import com.example.mentalkapp.data.adapter.NewsAdapter
import com.example.mentalkapp.data.response.ArticlesItem
import com.example.mentalkapp.data.util.ResultState
import com.example.mentalkapp.databinding.ActivityMainBinding
import com.example.mentalkapp.view.ImageData
import com.example.mentalkapp.view.ImageSliderAdapter
import com.example.mentalkapp.view.login.LoginActivity
import com.example.mentalkapp.view.news.NewsActivity
import com.example.mentalkapp.view.overview.OverviewActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private lateinit var adapter: ImageSliderAdapter
    private val viewModel: MainViewModel by viewModels()
    private val list = ArrayList<ImageData>()
    private lateinit var auth: FirebaseAuth
    private lateinit var dots: ArrayList<TextView>
    private lateinit var newsAdapter: NewsAdapter
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvNews.layoutManager = layoutManager

        newsAdapter = NewsAdapter()
        binding.rvNews.adapter = newsAdapter

        binding.button1.setOnClickListener{
            val intent = Intent(this, OverviewActivity::class.java)
            startActivity(intent)
        }

        binding.button2.setOnClickListener {
            val intent = Intent(this, NewsActivity::class.java)
            startActivity(intent)
        }

        viewModel.news.observe(this) { articles ->
            when (articles) {
                is ResultState.Loading -> {
                    binding.loginLoading.visibility = View.VISIBLE
                }
                is ResultState.Success -> {
                    lifecycleScope.launch(Dispatchers.Main) {
                        showRecyclerView()
                        setNewsData(articles.data)
                    }
                }
                is ResultState.Error -> {
                    Log.e("MainActivity", "Error: ${articles.error}")
                    Toast.makeText(this, "Error: ${articles.error}", Toast.LENGTH_SHORT).show()
                }
            }
        }

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

        auth = Firebase.auth
        val firebaseUser = auth.currentUser

        if (firebaseUser == null) {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
            return
        }
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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        Log.d("MainActivity", "Menu item clicked: ${item.itemId}")
        return when (item.itemId) {
            R.id.sign_out_menu -> {
                Log.d("MainActivity", "Sign out menu item selected")
                signOut()
                true
            }
            else -> {
                Log.d("MainActivity", "Other menu item selected")
                super.onOptionsItemSelected(item)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    private fun signOut() {
        lifecycleScope.launch {
            try {
                val credentialManager = CredentialManager.create(this@MainActivity)
                auth.signOut()
                credentialManager.clearCredentialState(ClearCredentialStateRequest())
                // Pastikan operasi di atas berhasil sebelum navigasi
                navigateToLogin()
            } catch (e: Exception) {
                Log.e("MainActivity", "Sign out failed: ${e.message}")
                Toast.makeText(this@MainActivity, "Sign out failed", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun navigateToLogin() {
        val intent = Intent(this@MainActivity, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
    private fun setNewsData(consumer: List<ArticlesItem>) {
    newsAdapter.submitList(consumer)
    }
    private fun showRecyclerView() {
        binding.rvNews.visibility = View.VISIBLE
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
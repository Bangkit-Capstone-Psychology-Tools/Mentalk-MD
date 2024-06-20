package com.example.mentalkapp.view.news

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mentalkapp.data.adapter.NewsAdapter
import com.example.mentalkapp.data.response.ArticlesItem
import com.example.mentalkapp.data.util.ResultState
import com.example.mentalkapp.databinding.ActivityNewsBinding

class NewsActivity : AppCompatActivity() {

    private var _binding: ActivityNewsBinding? = null
    private val viewModel: NewsViewModel by viewModels()
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvNews.layoutManager = layoutManager

        viewModel.news.observe(this) { articles ->
            when (articles) {
                is ResultState.Loading -> {
                    binding.pgNews.visibility = View.VISIBLE
                }

                is ResultState.Success -> {
                    binding.pgNews.visibility = View.GONE
                    showRecyclerView()
                    setNewsData(articles.data)
                }

                is ResultState.Error -> {
                    binding.pgNews.visibility = View.GONE
                    Log.e("MainActivity", "Error: ${articles.error}")
                    Toast.makeText(this, "Error: ${articles.error}", Toast.LENGTH_SHORT).show()
                }
            }
        }

        with(binding) {
            searchViewNews.setupWithSearchBar(searchBarNews)
            searchViewNews.editText
                .setOnEditorActionListener { tvGetNews, actionId, event ->
                    searchBarNews.setText(searchViewNews.text)
                    searchViewNews.hide()
                    viewModel.getNews(searchViewNews.text.toString())
                    false
                }
        }
    }

    private fun setNewsData(consumer: List<ArticlesItem>) {
        val adapter = NewsAdapter()
        adapter.submitList(consumer)
        binding.rvNews.adapter = adapter
    }

    private fun showRecyclerView() {
        binding.rvNews.visibility = View.VISIBLE
    }
}

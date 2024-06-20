package com.example.mentalkapp.data.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mentalkapp.R
import com.example.mentalkapp.data.response.ArticlesItem
import com.example.mentalkapp.databinding.NewsCardBinding
import com.example.mentalkapp.view.news.NewsDetailActivity

class NewsAdapter: ListAdapter<ArticlesItem, NewsAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = NewsCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val articles = getItem(position)
        holder.bind(articles)
    }

    class MyViewHolder(private val binding: NewsCardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(articles: ArticlesItem){
            binding.tittle.text = articles.title
            if (articles.urlToImage != null) {
                Glide.with(binding.newsImage.context)
                    .load(articles.urlToImage)
                    .into(binding.newsImage)
            }

            binding.root.setOnClickListener {
                val intent = Intent(itemView.context, NewsDetailActivity::class.java).apply {
                    putExtra("NEWS_PUBLISHED", articles.publishedAt)
                    putExtra("NEWS_TITTLE", articles.title)
                }
                binding.root.context.startActivity(intent)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ArticlesItem>() {
            override fun areItemsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
                return oldItem == newItem
            }
            override fun areContentsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}
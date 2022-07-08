package com.example.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.example.newsapp.adapters.NewsAdapter
import com.example.newsapp.databinding.ActivityMainBinding
import com.example.newsapp.models.NewsModel
import com.example.newsapp.viewmodels.NewsViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

private const val TAG = "MainActivity"

/**
 * Created by Abanoub on 7/7/2022
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity(), NewsAdapter.AdapterListener {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var adapter: NewsAdapter

    private val newsViewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Call the server to get the data
        newsViewModel.onLoadAllNews()

        onInitListInfo()

        onLoadNewsList()
    }

    private fun onInitListInfo(){
        adapter.setListener(this)
        binding.newsList.setHasFixedSize(true)
        binding.newsList.adapter = adapter
    }

    private fun onLoadNewsList(){
        newsViewModel.newsLiveData.observe(this){
            if (it == null){
                Log.e(TAG, "Data loaded failure...")
            }
            else {
                Log.e(TAG, "Data loaded successfully...${it.result.size}")
                adapter.setList(it.result)
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onItemSelectedListener(model: NewsModel) {
        onDetailScreenOpened(DetailFragment.newInstance(model))
    }

    /**
     * Open detail screen with selected presented data
     */
    private fun onDetailScreenOpened(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(android.R.id.content, fragment, "")
        transaction.addToBackStack("")
        transaction.commit()
    }
}
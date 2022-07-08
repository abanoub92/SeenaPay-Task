package com.example.newsapp.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.example.newsapp.databinding.ItemNewsLayoutBinding
import com.example.newsapp.models.NewsModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

/**
 * [RecyclerView.Adapter] Display list of data with
 * [RecyclerView.ViewHolder] That holds and prepare
 * data to display it on screen
 * Created by Abanoub 7/7/2022
 */
@Singleton
class NewsAdapter
@Inject constructor(
    private val glide: RequestManager,
): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>(){

    private lateinit var binding: ItemNewsLayoutBinding

    private var listener: AdapterListener? = null
    private var list: List<NewsModel> = listOf()

    fun setListener(listener: AdapterListener){
        this.listener = listener
    }

    fun setList(data: List<NewsModel>){
        this.list = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        binding = ItemNewsLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        /// For duplicated items
        holder.setIsRecyclable(false)
        holder.onBindWithData(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class NewsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }

        fun onBindWithData(model: NewsModel){
            glide.load(Uri.parse(model.multimedia[0].url)).into(binding.itemImage).clearOnDetach()

            binding.itemTitle.text = model.title
            binding.itemOwner.text = model.byline
            binding.itemDate.text = formatDate(model.publishedDate)
        }

        private fun formatDate(date: String): String {
            val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
            val d = formatter.parse(date)
            val format = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
            return d!!.let { format.format(it) }
        }

        override fun onClick(v: View?) {
            listener?.onItemSelectedListener(list[adapterPosition])
        }

    }

    interface AdapterListener{
        fun onItemSelectedListener(model: NewsModel)
    }
}
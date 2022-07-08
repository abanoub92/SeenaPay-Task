package com.example.newsapp.ui

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.RequestManager
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentDetailBinding
import com.example.newsapp.models.NewsModel
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

private const val ARG_NEWS_PARAM = "NewsDetail"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 * Created by Abanoub on 8/7/2022.
 */
@AndroidEntryPoint
class DetailFragment : Fragment(), View.OnClickListener {

    private var paramNews: NewsModel? = null
    private lateinit var binding: FragmentDetailBinding

    @Inject
    lateinit var glide: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            paramNews = it.getSerializable(ARG_NEWS_PARAM) as NewsModel?
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(LayoutInflater.from(container?.context), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.detailImage.setOnClickListener(this)
        binding.detailBackBtn.setOnClickListener(this)

        if (paramNews == null)
            return
        else {
            glide.load(Uri.parse(paramNews!!.multimedia[0].url)).into(binding.detailImage)

            binding.detailTitleValue.text = paramNews!!.title
            binding.detailOwnerValue.text = paramNews!!.byline
            binding.detailDescValue.text = paramNews!!.abstract
            binding.detailDateValue.text = formatDate(paramNews!!.publishedDate)
            if (paramNews!!.section.isEmpty()){
                binding.detailSectionLayout.visibility = View.GONE
            }
            else if (paramNews!!.subsection.isEmpty()) {
                binding.detailSectionValue.text = paramNews!!.section
            }
            else {
                binding.detailSectionValue.text = "${paramNews!!.section} -> ${paramNews!!.subsection}"
            }
        }
    }

    private fun formatDate(date: String): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        val d = formatter.parse(date)
        val format = SimpleDateFormat("yyyy/MM/dd", Locale.getDefault())
        return d!!.let { format.format(it) }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param news Parameter 1.
         * @return A new instance of fragment DetailFragment.
         */
        @JvmStatic
        fun newInstance(news: NewsModel) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_NEWS_PARAM, news)
                }
            }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.detailBackBtn -> parentFragmentManager.popBackStack()
            R.id.detailImage -> {
                ImagePreviewDialog.newInstance(paramNews!!.multimedia[0].url
                ).show(parentFragmentManager, "")
            }
        }
    }
}
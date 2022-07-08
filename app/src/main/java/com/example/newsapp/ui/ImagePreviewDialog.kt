package com.example.newsapp.ui

import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.bumptech.glide.RequestManager
import com.example.newsapp.R
import com.example.newsapp.databinding.DialogImagePreviewBinding
import com.github.piasy.biv.view.BigImageView
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


private const val ARG_IMAGE_PARAM = "IMG_News$2022"
/**
 * Created by Abanoub on 8/7/2022.
 */
@AndroidEntryPoint
class ImagePreviewDialog: DialogFragment(), View.OnClickListener {

    private var mParamImage: String? = null
    private lateinit var binding: DialogImagePreviewBinding

    @Inject
    lateinit var glide: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            mParamImage = it.getString(ARG_IMAGE_PARAM)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = DialogImagePreviewBinding.inflate(LayoutInflater.from(context))


        binding.dialogImagePreviewClose.setOnClickListener(this)
        binding.dialogImagePreviewShare.setOnClickListener(this)

        binding.dialogImagePreviewer.setInitScaleType(BigImageView.INIT_SCALE_TYPE_CENTER)
        binding.dialogImagePreviewer.showImage(Uri.parse(mParamImage))

        return AlertDialog.Builder(requireContext())
            .setView(binding.root)
            .create()
    }

    override fun onStart() {
        super.onStart()
        dialog!!.window!!.setGravity(Gravity.CENTER)
        dialog!!.window!!.setLayout(WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment ImagePreviewDialog.
         */
        @JvmStatic
        fun newInstance(image: String) = ImagePreviewDialog().apply {
            arguments = Bundle().apply {
                putString(ARG_IMAGE_PARAM, image)
            }
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.dialogImagePreviewClose -> dialog!!.dismiss()
            R.id.dialogImagePreviewShare -> {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, mParamImage)
                startActivity(Intent.createChooser(intent, "share it with:"))
            }
        }
    }
}
package com.github.alunicus.cinemalist.core

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.github.alunicus.cinemalist.databinding.ViewErrorBinding
import com.github.alunicus.cinemalist.extensions.setResource

class ErrorView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    private var _binding: ViewErrorBinding? = null
    private val binding get() = _binding!!

    init {
        _binding = ViewErrorBinding.inflate(LayoutInflater.from(context), this)
    }

    fun setErrorMessage(errorMessage: ErrorMessage) {
        binding.viewErrorImage.setResource(errorMessage.imageResId)
        binding.viewErrorMessage.setText(errorMessage.messageResId)
    }
}
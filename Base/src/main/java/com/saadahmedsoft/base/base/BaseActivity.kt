package com.saadahmedsoft.base.base

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.saadahmedsoft.base.databinding.AppToolbarBinding
import com.saadahmedsoft.base.utils.Constants
import com.saadahmedsoft.base.utils.onClicked
import com.saadahmedsoft.base.utils.snackBar
import com.saadahmedsoft.base.utils.toast

abstract class BaseActivity<BINDING: ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater) -> BINDING
) : AppCompatActivity() {

    private lateinit var _binding: BINDING

    val binding: BINDING
        get() = _binding

    abstract val toolbarBinding: AppToolbarBinding?

    abstract fun onActivityCreate(savedInstanceState: Bundle?)

    abstract fun observeData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        setContentView(_binding.root)
        observeData()
        initToolbar()
        onActivityCreate(savedInstanceState)
    }

    private fun initToolbar() {
        if (toolbarBinding != null) {
            toolbarBinding!!.toolbarTitle.text = title
            toolbarBinding!!.toolbarBtn.onClicked {
                super.onBackPressed()
                //Animator.animateSlideRight(this)
            }
        }
    }

    fun shortSnackBar(message: String) {
        showSnackBar(message, Constants.DURATIONS.SNACK_SHORT)
    }

    fun longSnackBar(message: String) {
        showSnackBar(message, Constants.DURATIONS.SNACK_LONG)
    }

    fun shortToast(message: String) {
        showToast(message, Constants.DURATIONS.TOAST_SHORT)
    }

    fun longToast(message: String) {
        showToast(message, Constants.DURATIONS.TOAST_LONG)
    }

    private fun showSnackBar(message: String, duration: Int) {
        snackBar(this, _binding.root, message, duration)
    }

    private fun showToast(message: String, duration: Int) {
        toast(this, message, duration)
    }
}
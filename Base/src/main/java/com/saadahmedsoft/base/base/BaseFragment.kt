package com.saadahmedsoft.base.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.saadahmedsoft.base.utils.Constants
import com.saadahmedsoft.base.utils.snackBar
import com.saadahmedsoft.base.utils.toast

abstract class BaseFragment<BINDING: ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater) -> BINDING
) : Fragment() {

    private lateinit var _binding: BINDING

    val binding: BINDING
        get() = _binding

    abstract val title: String
    abstract val isBackButtonVisible: Boolean

    abstract fun onFragmentCreate(savedInstanceState: Bundle?)

    //fun dashboardActivity() = DashboardActivity.newInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = bindingInflater.invoke(layoutInflater)
        onFragmentCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //dashboardActivity().binding.appToolbar.toolbarTitle.text = title
        //dashboardActivity().binding.appToolbar.toolbarBtn.visibility = if (isBackButtonVisible) visible() else gone()
        return _binding.root
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
        snackBar(requireContext(), _binding.root, message, duration)
    }

    private fun showToast(message: String, duration: Int) {
        toast(requireContext(), message, duration)
    }

    fun onBackPressed() {
        requireActivity().onBackPressed()
    }
}
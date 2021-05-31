package com.ameen.e_store.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<T : ViewBinding> : Fragment() {

    private var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> T

    @Suppress("UNCHECKED_CAST")
    //This var will be used in the child classes
    protected val binding: T
        get() = _binding as T

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater, container, false)
        return _binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupOnViewCreated()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    //Will be implemented in the child class
    abstract fun setupOnViewCreated()
}
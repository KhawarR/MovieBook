package com.pingo.moviebook.shared.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.pingo.moviebook.shared.ext.setToolbar
import com.pingo.moviebook.shared.models.Genre
import kotlinx.android.synthetic.main.fragment_home.*

/**
 * Base Fragment holding [ViewDataBinding] and [ViewModel]
 * @param VB : [ViewDataBinding]
 * @param VM : [ViewModel]
 * @property layout Int
 * @property viewModelObj VM
 * @property binding VB
 * @property viewModelClass KClass<VM>
 * @constructor
 */
abstract class BaseFragment<VB : ViewDataBinding>(@LayoutRes private var layout: Int) :
    Fragment() {

    protected lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layout, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        activity?.setToolbar(toolbar, true)
    }

}

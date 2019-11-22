package com.wenli.bookbrowse.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wenli.bookbrowse.R
import com.wenli.framework.base.BaseFragment

class LoginFragment:BaseFragment() {
    override fun onInflater(inflater: LayoutInflater, container: ViewGroup?): View {
        rootView = inflater.inflate(R.layout.fragment_login, container, false)
        return rootView!!
    }
}
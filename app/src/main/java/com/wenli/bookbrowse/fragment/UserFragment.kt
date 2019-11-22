package com.wenli.bookbrowse.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.wenli.bookbrowse.R
import com.wenli.bookbrowse.login.LoginActivity
import com.wenli.bookbrowse.user.VipOpenSeting
import com.wenli.bookbrowse.user.MesNoticeActivity
import com.wenli.bookbrowse.user.ReadHistoryActivity
import com.wenli.framework.base.BaseFragment
import com.wenli.framework.util.GreenActivityManager
import com.wenli.framework.util.ParmarsValue
import com.wenli.framework.util.SharedPreferencesHelper

class UserFragment : BaseFragment(), View.OnClickListener {
    var v01: View? = null
    var v02: View? = null
    var v03: View? = null
    var v04: View? = null
    var v05: View? = null
    var exte: View? = null

    override fun onInflater(inflater: LayoutInflater, container: ViewGroup?): View {
        rootView = inflater.inflate(R.layout.fragment_user, container, false)
        return rootView!!
    }

    override fun findView() {
        v01 = rootView?.findViewById(R.id.rl01)
        v02 = rootView?.findViewById(R.id.rl02)
        v03 = rootView?.findViewById(R.id.rl03)
        v04 = rootView?.findViewById(R.id.rl04)
        v05 = rootView?.findViewById(R.id.rl05)
        exte = rootView?.findViewById(R.id.tvExte)

        v01?.setOnClickListener(this)
        v02?.setOnClickListener(this)
        v03?.setOnClickListener(this)
        v04?.setOnClickListener(this)
        v05?.setOnClickListener(this)
        exte?.setOnClickListener(this)

        rendView()
    }

    override fun rendView() {

    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.rl01 -> {
            }
            R.id.rl02 -> {
                startActivity(VipOpenSeting::class.java, null)
            }
            R.id.rl03 -> {
                startActivity(ReadHistoryActivity::class.java, null)
            }
            R.id.rl04 -> {
                startActivity(MesNoticeActivity::class.java, null)
            }
            R.id.rl05 -> {
            }
            R.id.tvExte -> {
                GreenActivityManager.finishAllActivity()
                startActivity(LoginActivity::class.java, null)
                SharedPreferencesHelper.getInstance(activity).put(ParmarsValue.KEY_lOGIN, false)
            }

        }
    }
}
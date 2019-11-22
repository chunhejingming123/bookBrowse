package com.wenli.bookbrowse.home

import android.view.View
import android.widget.TextView
import com.wenli.bookbrowse.R
import com.wenli.bookbrowse.dialoge.DialogeUtil
import com.wenli.bookbrowse.login.LoginActivity
import com.wenli.bookbrowse.user.VipOpenSeting
import com.wenli.framework.base.BaseActivity
import com.wenli.framework.util.ParmarsValue
import com.wenli.framework.util.SharedPreferencesHelper

class BookDetailActivity : BaseActivity(), View.OnClickListener {
    var viewRead: TextView? = null
    override fun onLayoutLoad(): Int {
        return R.layout.activity_book_detail
    }

    override fun findView() {
        viewRead = findViewById(R.id.tvRead)
        viewRead?.setOnClickListener(this)

        rendView()
    }

    override fun rendView() {
        initTitleBar(0, R.string.find)
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.tvRead -> {
                val isLogin = SharedPreferencesHelper.getInstance(this).getSharedPreference(ParmarsValue.KEY_lOGIN, false) as Boolean
                if (isLogin) {
                    DialogeUtil.vipOpenDiaoge(this, object : DialogeUtil.DialogeCallBack {
                        override fun close() {
                        }

                        override fun callBack() {
                            startActivity(VipOpenSeting::class.java, null)
                        }
                    }).showDialoge()

                } else {
                    DialogeUtil.loginDialge(this, object : DialogeUtil.DialogeCallBack {
                        override fun close() {
                        }

                        override fun callBack() {
                            startActivity(LoginActivity::class.java, null)
                        }
                    }).showDialoge()

                }

            }
        }
    }
}

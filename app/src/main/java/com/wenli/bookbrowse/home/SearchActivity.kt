package com.wenli.bookbrowse.home

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import com.wenli.bookbrowse.R
import com.wenli.framework.base.BaseActivity

class SearchActivity : BaseActivity() {
    var mEditext: EditText? = null
    var find: View? = null


    override fun onLayoutLoad(): Int {
        return R.layout.activity_search
    }

    override fun findView() {
        findViewById<View>(R.id.ivBack).setOnClickListener { finish() }
        mEditext = findViewById(R.id.etSearch)
        find = findViewById(R.id.tvfind)

        mEditext?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
        find?.setOnClickListener { search() }

    }

    private fun search() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}

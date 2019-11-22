package com.wenli.bookbrowse.user

import androidx.recyclerview.widget.RecyclerView
import com.wenli.Stringbrowse.adapter.MessageAdapter
import com.wenli.bookbrowse.R
import com.wenli.framework.base.BaseActivity

class MesNoticeActivity : BaseActivity() {
    var recycleView: RecyclerView? = null
    var mAdapter: MessageAdapter? = null

    override fun onLayoutLoad(): Int {
        return R.layout.activity_mes_notice
    }

    override fun findView() {
        recycleView = findViewById(R.id.recycleView)
        rendView()
    }

    override fun rendView() {
        initTitleBar(0, R.string.msgnotice)
        mAdapter = MessageAdapter()
        initRecycle(recycleView!!, object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }
        })
        recycleView?.adapter = mAdapter
    }
}

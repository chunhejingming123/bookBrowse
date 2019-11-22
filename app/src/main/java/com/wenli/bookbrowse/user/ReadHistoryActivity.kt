package com.wenli.bookbrowse.user

import androidx.recyclerview.widget.RecyclerView
import com.wenli.bookbrowse.R
import com.wenli.bookbrowse.adapter.HomeAdapter
import com.wenli.bookbrowse.bean.Book
import com.wenli.bookbrowse.home.BookDetailActivity
import com.wenli.framework.base.BaseActivity
import com.wenli.framework.util.ToastUtil

class ReadHistoryActivity : BaseActivity() {
    var mRecycleView: RecyclerView? = null
    var homeAdapter: HomeAdapter? = null
    override fun onLayoutLoad(): Int {
        return R.layout.activity_read_history
    }

    override fun findView() {
        mRecycleView = findViewById(R.id.recycleView)

        rendView()
    }

    override fun rendView() {
        initTitleBar(0, R.string.readhis)
        homeAdapter = HomeAdapter(HomeAdapter.USER_READ)
        initRecycle(mRecycleView!!, object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
            }
        })
        mRecycleView?.adapter = homeAdapter
        homeAdapter?.mList = Book.getBookList()
        homeAdapter?.notifyDataSetChanged()
        homeAdapter?.mOnHomeAction = object : HomeAdapter.OnHomeAction {
            override fun goToDeatail(book: String) {
                startActivity(BookDetailActivity::class.java, null)
            }

            override fun onSearch() {

            }

            override fun onMsg() {

            }
        }

    }
}

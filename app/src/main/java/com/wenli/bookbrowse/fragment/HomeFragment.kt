package com.wenli.bookbrowse.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wenli.bookbrowse.R
import com.wenli.bookbrowse.adapter.HomeAdapter
import com.wenli.bookbrowse.bean.Book
import com.wenli.bookbrowse.home.BookDetailActivity
import com.wenli.framework.base.BaseFragment
import com.wenli.framework.util.ToastUtil

class HomeFragment : BaseFragment() {
    var mRecycleView: RecyclerView? = null
    var homeAdapter: HomeAdapter? = null
    override fun onInflater(inflater: LayoutInflater, container: ViewGroup?): View {
        rootView = inflater.inflate(R.layout.fragment_home, container, false)
        return rootView!!
    }

    override fun findView() {
        mRecycleView = rootView?.findViewById(R.id.recycleView)
        rendView()
    }

    override fun rendView() {
        homeAdapter = HomeAdapter(HomeAdapter.USER_FIND)
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
                ToastUtil.showAlter("search")
            }

            override fun onMsg() {
                ToastUtil.showAlter("msg")
            }
        }

    }
}
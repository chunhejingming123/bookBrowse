package com.wenli.bookbrowse.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.wenli.bookbrowse.R
import com.wenli.bookbrowse.bean.Book

class HomeAdapter(type: Int) : HeaderFooterRecyclerViewAdapter<BaseViewHolder<Book>>() {
    companion object {
        val USER_FIND = 1
        val USER_READ = 2
    }

    var currentType = type

    var mList: List<Book>? = null

    override fun getHeaderItemCount(): Int {
        if (USER_FIND == currentType)
            return 1
        else return 0
    }

    override fun getContentItemCount(): Int {
        return mList?.size!!
    }

    override fun getFooterItemCount(): Int {
        return 0
    }


    override fun onCreateHeaderItemViewHolder(parent: ViewGroup?, headerViewType: Int): BaseViewHolder<Book> {
        var viewSearch = LayoutInflater.from(parent?.context).inflate(R.layout.home_item_search, parent, false)
        return SearchView(viewSearch)
    }

    override fun onCreateContentItemViewHolder(parent: ViewGroup?, contentViewType: Int): BaseViewHolder<Book> {
        var viewBaner = LayoutInflater.from(parent?.context).inflate(R.layout.content, parent, false)
        return ContentView(viewBaner)
    }

    override fun onCreateFooterItemViewHolder(parent: ViewGroup?, footerViewType: Int): BaseViewHolder<Book> {
        var viewSearch = LayoutInflater.from(parent?.context).inflate(R.layout.home_item_search, parent, false)
        return SearchView(viewSearch)
    }


    override fun onBindHeaderItemViewHolder(headerViewHolder: BaseViewHolder<Book>?, position: Int) {
        headerViewHolder?.rendView(null, position)
    }

    override fun onBindFooterItemViewHolder(footerViewHolder: BaseViewHolder<Book>?, position: Int) {
        footerViewHolder?.rendView(null, position)
    }

    override fun onBindContentItemViewHolder(contentViewHolder: BaseViewHolder<Book>?, position: Int) {
        contentViewHolder?.rendView(mList?.get(position), position)
    }


    inner class SearchView(iteam: View) : BaseViewHolder<Book>(iteam), View.OnClickListener {
        var viewSearch: View? = null
        var viewMsg: ImageView? = null

        init {
            viewSearch = iteam.findViewById(R.id.flSearch)
            viewMsg = iteam.findViewById(R.id.ivMsg)
            viewSearch?.setOnClickListener(this)
            viewMsg?.setOnClickListener(this)
        }

        override fun rendView(t: Book?, position: Int) {

        }

        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.ivMsg -> {
                    mOnHomeAction?.onMsg()
                }
                R.id.flSearch -> {
                    mOnHomeAction?.onSearch()
                }
            }
        }
    }

    inner class ContentView(iteam: View) : BaseViewHolder<Book>(iteam) {
        var tvBookName: TextView? = null
        var tvBookAuthor: TextView? = null
        var tvBookPublish: TextView? = null
        var tvBookintro: TextView? = null
        var tvJie: TextView? = null
        var ivCover: ImageView? = null

        init {
            tvBookName = iteam.findViewById(R.id.tvBookName)
            tvBookAuthor = iteam.findViewById(R.id.tvBookAuthor)
            tvBookPublish = iteam.findViewById(R.id.tvBookPublish)
            tvBookintro = iteam.findViewById(R.id.tvBookintro)
            tvJie = iteam.findViewById(R.id.tvJie)
            ivCover = iteam.findViewById(R.id.ivCover)
            iteam?.setOnClickListener { mOnHomeAction?.goToDeatail("book") }
        }

        override fun rendView(t: Book?, position: Int) {
            tvBookName?.text = t?.bookname
            tvBookAuthor?.text = t?.boolAuthor
            tvBookPublish?.text = t?.bookPubish
            tvBookintro?.text = t?.shortdes
            if (currentType == USER_FIND) {
                tvJie?.visibility = View.VISIBLE
            } else {
                tvJie?.visibility = View.GONE
            }
            ivCover?.setBackgroundResource(t?.redIcon!!)

        }
    }


    var mOnHomeAction: OnHomeAction? = null

    interface OnHomeAction {
        fun goToDeatail(book: String)
        fun onSearch()
        fun onMsg()
    }

}
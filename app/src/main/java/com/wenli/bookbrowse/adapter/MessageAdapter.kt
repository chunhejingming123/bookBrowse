package com.wenli.Stringbrowse.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.wenli.bookbrowse.R
import com.wenli.bookbrowse.adapter.BaseViewHolder
import com.wenli.bookbrowse.adapter.HeaderFooterRecyclerViewAdapter


class MessageAdapter() : HeaderFooterRecyclerViewAdapter<BaseViewHolder<String>>() {


    override fun getHeaderItemCount(): Int {
        return 0
    }

    override fun getContentItemCount(): Int {
        return 10
    }

    override fun getFooterItemCount(): Int {
        return 0
    }

    override fun onCreateHeaderItemViewHolder(parent: ViewGroup?, headerViewType: Int): BaseViewHolder<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateContentItemViewHolder(parent: ViewGroup?, contentViewType: Int): BaseViewHolder<String> {
        var viewBaner = LayoutInflater.from(parent?.context).inflate(R.layout.item_message_view, parent, false)
        return ContentView(viewBaner)
    }


    override fun onBindHeaderItemViewHolder(headerViewHolder: BaseViewHolder<String>?, position: Int) {
        headerViewHolder?.rendView(null, position)
    }

    override fun onBindFooterItemViewHolder(footerViewHolder: BaseViewHolder<String>?, position: Int) {
        footerViewHolder?.rendView(null, position)
    }

    override fun onBindContentItemViewHolder(contentViewHolder: BaseViewHolder<String>?, position: Int) {
        contentViewHolder?.rendView("", position)
    }

    override fun onCreateFooterItemViewHolder(parent: ViewGroup?, footerViewType: Int): BaseViewHolder<String> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    inner class ContentView(iteam: View) : BaseViewHolder<String>(iteam) {
        var tvStringName: TextView? = null
        var tvStringAuthor: TextView? = null
        var tvStringPublish: TextView? = null
        var tvStringintro: TextView? = null
        var tvJie: TextView? = null


        override fun rendView(t: String?, position: Int) {

        }
    }


}
package com.wenli.bookbrowse.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView

open class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    open fun rendView() {}
    open fun rendView(t: T?, position: Int) {}
    override fun onClick(v: View?) {

    }
}
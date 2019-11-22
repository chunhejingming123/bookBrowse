package com.wenli.Stringbrowse.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.wenli.bookbrowse.R
import com.wenli.bookbrowse.adapter.BaseViewHolder
import com.wenli.bookbrowse.adapter.HeaderFooterRecyclerViewAdapter
import com.wenli.bookbrowse.bean.VipFree


class XufeiAdapter() : HeaderFooterRecyclerViewAdapter<BaseViewHolder<VipFree>>() {
    val LEFT = 1
    val RIGHT = 2
    var mlist: List<VipFree>? = null
    var select = 0


    override fun getHeaderItemCount(): Int {
        return 0
    }

    override fun getContentItemCount(): Int {
        return mlist?.size!!
    }

    override fun getFooterItemCount(): Int {
        return 0
    }

    override fun getContentItemViewType(position: Int): Int {
        if (position % 2 == 0) {
            return LEFT
        }
        return RIGHT
    }

    override fun onCreateHeaderItemViewHolder(parent: ViewGroup?, headerViewType: Int): BaseViewHolder<VipFree> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onCreateContentItemViewHolder(parent: ViewGroup?, contentViewType: Int): BaseViewHolder<VipFree>? {
        when (contentViewType) {
            LEFT -> return ContentView(LayoutInflater.from(parent?.context).inflate(R.layout.item_xufei_left_view, parent, false))
            RIGHT -> return ContentView(LayoutInflater.from(parent?.context).inflate(R.layout.item_xufei_right_view, parent, false))
        }
        return null
    }


    override fun onBindHeaderItemViewHolder(headerViewHolder: BaseViewHolder<VipFree>?, position: Int) {
        headerViewHolder?.rendView(null, position)
    }

    override fun onBindFooterItemViewHolder(footerViewHolder: BaseViewHolder<VipFree>?, position: Int) {
    }

    override fun onBindContentItemViewHolder(contentViewHolder: BaseViewHolder<VipFree>?, position: Int) {
        contentViewHolder?.rendView(mlist?.get(position), position)
    }

    override fun onCreateFooterItemViewHolder(parent: ViewGroup?, footerViewType: Int): BaseViewHolder<VipFree> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    inner class ContentView(iteam: View) : BaseViewHolder<VipFree>(iteam) {
        var money: TextView? = null
        var time: TextView? = null
        var btn: Button? = null
        var rlRoot: View? = null

        init {
            rlRoot = iteam.findViewById(R.id.rlroot)
            time = iteam.findViewById(R.id.tvTime)
            money = iteam.findViewById(R.id.tvMoney)
            btn = iteam.findViewById(R.id.btnChose)
        }


        override fun rendView(t: VipFree?, position: Int) {
            money?.text = t?.monmey
            time?.text = t?.time
            if (position == select) {
                btn?.setBackgroundResource(R.drawable.shape_select_vip)
                rlRoot?.setBackgroundResource(R.drawable.shape_vip_select)
                money?.setTextColor(itemView.context.resources.getColor(R.color.color_FFF6DFA7))
                time?.setTextColor(itemView.context.resources.getColor(R.color.color_BFA26F))
            } else {
                btn?.setBackgroundResource(R.drawable.shape_normal_vip)
                rlRoot?.setBackgroundResource(R.drawable.shape_vip_normal)
                money?.setTextColor(itemView.context.resources.getColor(R.color.color_ff232221))
                time?.setTextColor(itemView.context.resources.getColor(R.color.color_ff232221))
            }
            btn?.setOnClickListener {
                select = position
                mVipAction?.onItemSelect(t!!)
                notifyDataSetChanged()
            }
        }
    }

    var mVipAction: VipAction? = null

    interface VipAction {
        fun onItemSelect(vip: VipFree)
    }


}
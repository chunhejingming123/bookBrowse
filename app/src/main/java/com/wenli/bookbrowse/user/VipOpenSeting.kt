package com.wenli.bookbrowse.user

import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.wenli.Stringbrowse.adapter.XufeiAdapter
import com.wenli.bookbrowse.R
import com.wenli.bookbrowse.bean.VipFree
import com.wenli.bookbrowse.dialoge.DialogeUtil
import com.wenli.bookbrowse.dialoge.VipSucessDialoge
import com.wenli.framework.base.BaseActivity
import com.wenli.framework.util.ToastUtil

class VipOpenSeting : BaseActivity() {
    var mRecycleView: RecyclerView? = null
    var mAdapter: XufeiAdapter? = null
    var btnXuFei: Button? = null
    var select: VipFree? = null


    override fun onLayoutLoad(): Int {
        return R.layout.activity_huiyuan_seting
    }

    override fun findView() {
        mRecycleView = findViewById(R.id.grideRecycleView)
        btnXuFei = findViewById(R.id.btnXuFei)
        btnXuFei?.setOnClickListener {
            DialogeUtil.vipOpenConfirmDiaoge(this, object : DialogeUtil.DialogeCallBack {
                override fun close() {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun callBack() {
                    vipSucess()
                }
            }).showDialoge(getString(R.string.openviptip, select?.datatime))

        }
        rendView()
    }

    override fun rendView() {
        initTitleBar(0, R.string.vipxufei)
        initRecycle(mRecycleView!!)
        mAdapter = XufeiAdapter()
        mRecycleView?.adapter = mAdapter
        mAdapter?.mlist = VipFree.getVipFreeList()
        mAdapter?.notifyDataSetChanged()
        mAdapter?.mVipAction = object : XufeiAdapter.VipAction {
            override fun onItemSelect(vip: VipFree) {
                select = vip

            }
        }

    }

    fun vipConfirm() {}

    fun vipSucess() {
        DialogeUtil.vipSucessDiaoge(this, null).showDialoge()
    }

}

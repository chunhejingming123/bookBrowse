package com.wenli.bookbrowse.fragment

import com.wenli.bookbrowse.dialoge.DialogeUtil
import com.wenli.bookbrowse.dialoge.ZxingDialoge
import com.wenli.framework.util.ToastUtil
import com.wenli.zxlibing.activity.ScanFragment

class ZxingFragment : ScanFragment() {
    var isVable = false
    override fun setScannerResult(str: String?) {
        super.setScannerResult(str)
        if (!isVable) {
            return
        }

        var scanDialoge = ZxingDialoge(activity)
        scanDialoge.showDialoge(str)
        scanDialoge.setZxingResume(object : ZxingDialoge.ZxingResume {
            override fun close() {
                booksucess()
                //onResume()
            }
        })
        onPause()
    }

    override fun onPause() {
        super.onPause()
    }

    override fun onResume() {
        isVable = true
        super.onResume()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        isVable = !hidden
        if (isVable) {
            onResume()
        } else {
            onPause()
        }
    }

    fun result() {
        DialogeUtil.boookFailDialoge(activity, null).showDialoge()
    }

    fun booksucess() {
        DialogeUtil.boookSucessDiaoge(activity, null).showDialoge()
    }

}
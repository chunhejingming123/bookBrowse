package com.wenli.framework.base

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wenli.framework.R
import com.wenli.framework.util.GreenActivityManager
import com.wenli.framework.view.MyLinearLayoutManager


abstract class BaseActivity : AppCompatActivity(), InitView {
    override fun init() {

    }

    override fun findView() {
    }

    override fun rendView() {
    }

    override fun onloadData() {

    }

    /**
     * 加载布局文件
     */
    abstract fun onLayoutLoad(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
        if (0 != onLayoutLoad()) {
            setContentView(onLayoutLoad())
        }
        findView()
        GreenActivityManager.registerActivity(this)
        onloadData()

    }

    override fun onDestroy() {
        super.onDestroy()
        GreenActivityManager.destoryActivity(this)
    }

    protected fun startActivity(descActivity: Class<*>, bundle: Bundle?) {
        val intent = Intent()
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        intent.setClass(this, descActivity)
        this.startActivity(intent)
    }

    // 设置RecycleView
    public var llm: LinearLayoutManager? = null

    // 线性
    fun initRecycle(recycleView: RecyclerView, listener: RecyclerView.OnScrollListener?) {
        llm = MyLinearLayoutManager(BaseApplication.application)
        recycleView.layoutManager = llm
        // 设置ItemAnimator
        recycleView.itemAnimator = DefaultItemAnimator()
        // 设置固定大小
        recycleView.setHasFixedSize(true)
        recycleView.isNestedScrollingEnabled = false
        if (null != listener) {
            recycleView.addOnScrollListener(listener)
        }
    }

    // 网格
    fun initRecycle(recycleView: RecyclerView) {
        var grideManager = GridLayoutManager(BaseApplication.application, 2)
        recycleView.layoutManager = grideManager
        recycleView.itemAnimator = DefaultItemAnimator()
        recycleView.setHasFixedSize(true)
        recycleView.isNestedScrollingEnabled = false
    }


    fun initTitleBar(resIcon: Int, resString: Int) {
        findViewById<ImageView>(R.id.ivBack).setOnClickListener { finish() }
        findViewById<TextView>(R.id.tvTitle).text = getString(resString)
    }


}

package  com.wenli.framework.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView
import com.wenli.framework.view.MyLinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager as LinearLayoutManager1


abstract class BaseFragment : Fragment(), InitView {
    var rootView: View? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        init()
        if (null == rootView) {
            rootView = onInflater(inflater, container)
            findView()
            onloadData()
        }
        return rootView
    }

    abstract fun onInflater(inflater: LayoutInflater, container: ViewGroup?): View

    override fun init() {

    }

    override fun findView() {

    }

    override fun rendView() {
    }

    override fun onloadData() {
    }

    var llm: MyLinearLayoutManager? = null
    fun initRecycle(recycleView: RecyclerView, listener: RecyclerView.OnScrollListener?) {
        llm = MyLinearLayoutManager(BaseApplication.application)
        llm?.orientation = LinearLayoutManager1.VERTICAL
        recycleView.layoutManager = llm
        // 设置ItemAnimator
        recycleView.itemAnimator = DefaultItemAnimator()
        // 设置固定大小
        recycleView.setHasFixedSize(true)
        if (null != listener) {
            recycleView.addOnScrollListener(listener)
        }
    }

    fun initRecycle(recycleView: RecyclerView, listener: RecyclerView.OnScrollListener?, ll: LinearLayoutManager1) {
        recycleView.layoutManager = ll
        recycleView.itemAnimator = DefaultItemAnimator()
        // 设置固定大小
        recycleView.setHasFixedSize(true)
        if (null != listener) {
            recycleView.addOnScrollListener(listener)
        }
    }

    protected fun startActivity(descActivity: Class<*>, bundle: Bundle?) {
        val intent = Intent()
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        intent.setClass(activity!!, descActivity)
        this.startActivity(intent)
    }

}
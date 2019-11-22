package  com.wenli.framework.base

interface InitView {
    /**
     * 个别页面setContentView前处理
     */
    fun init()

    /**
     * findView
     */
    fun findView()

    /**
     * 渲染ui
     */
    fun rendView()

    /**
     * 加载数据
     */
    fun onloadData()

}
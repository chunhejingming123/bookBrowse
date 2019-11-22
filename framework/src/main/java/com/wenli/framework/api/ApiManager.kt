package com.greenengerapp.api


import com.wenli.framework.base.BaseApplication
import com.wenli.framework.http.HttpRespnse
import com.wenli.framework.http.HttpUtil
import com.wenli.framework.util.NetworkUtil
import com.wenli.framework.util.ToastUtil
import okhttp3.RequestBody
import java.util.*


/**
 * 作者 :created  by suochunming
 * 日期：2018/11/17 0018:09
 */

class ApiManager private constructor() {
    companion object {
        val getInstance = Helper.instance
    }

    private object Helper {
        val instance = ApiManager()
    }

    fun loadDataByParmars(url: String, map: Map<String, String>, mHttpResponse: HttpRespnse) {
        var iscont = NetworkUtil.isOpenNetwork(BaseApplication.application, false)
        if (!iscont) {
            ToastUtil.showAlter("请检查网络")
            return
        }
        HttpUtil.instance.postRequest(url, map, mHttpResponse)
    }

    //
    fun getDataByParmars(url: String, map: Map<String, String>, mHttpResponse: HttpRespnse) {
        var iscont = NetworkUtil.isOpenNetwork(BaseApplication.application, false)
        if (!iscont) {
            ToastUtil.showAlter("请检查网络")
            return
        }
        HttpUtil.instance.getRequest(url, map, mHttpResponse)
    }


    fun getDataByUrl(url: String, mHttpResponse: HttpRespnse) {
        var iscont = NetworkUtil.isOpenNetwork(BaseApplication.application, false)
        if (!iscont) {
            ToastUtil.showAlter("请检查网络")
            return
        }
        HttpUtil.instance.getRequest(url, mHttpResponse)
    }

    fun upLoadData(url: String, strmap: Map<String, String>, map: HashMap<String, RequestBody>?, mHttpResponse: HttpRespnse) {
        var iscont = NetworkUtil.isOpenNetwork(BaseApplication.application, false)
        if (!iscont) {
            ToastUtil.showAlter("请检查网络")
            return
        }
        HttpUtil.instance.upLoadData(url, strmap, map, mHttpResponse)
    }

    fun upLoadData(url: String, map: HashMap<String, RequestBody>?, mHttpResponse: HttpRespnse) {
        var iscont = NetworkUtil.isOpenNetwork(BaseApplication.application, false)
        if (!iscont) {
            ToastUtil.showAlter("请检查网络")
            return
        }
        HttpUtil.instance.upLoadData(url, map, mHttpResponse)
    }
}
package com.greenengerapp.api

import android.text.TextUtils
import com.wenli.framework.http.UriConsts

/**
 * 作者 :created  by suochunming
 * 日期：2018/8/21 0021:14
 * 存放接口
 */

class ApiInterface {
    companion object {
        val wxAppid="wx38b5d7ad60218dd5"

        fun getRequestUrl(url: String): String {
            var buff = StringBuffer(UriConsts.BASE_URL)
            if (!TextUtils.isEmpty(url)) {
                buff.append(url)
            }
            return buff.toString()
        }
    }
}
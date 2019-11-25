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
        val  wxSecret="901dac87827945f7e3791bb392cb8d6c"

        fun getRequestUrl(url: String): String {
            var buff = StringBuffer(UriConsts.BASE_URL)
            if (!TextUtils.isEmpty(url)) {
                buff.append(url)
            }
            return buff.toString()
        }
    }
}
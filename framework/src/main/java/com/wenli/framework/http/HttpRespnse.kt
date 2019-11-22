package com.wenli.framework.http

/**
 * 作者 :created  by suochunming
 * 日期：2018/8/18 0018:14
 */

interface HttpRespnse {

    fun _onSucess(t: String)

    fun _onError(e: Throwable)
}
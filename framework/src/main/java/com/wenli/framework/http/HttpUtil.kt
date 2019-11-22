package com.wenli.framework.http

import android.text.TextUtils
import com.wenli.framework.util.LogUtil
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.RequestBody
import okhttp3.ResponseBody

/**
 * 作者 :created  by suochunming
 * 日期：2018/8/18 0018:09
 */

class HttpUtil private constructor() {
    companion object {
        @JvmStatic
        val instance: HttpUtil by lazy {
            HttpUtil()
        }
    }

    private val mApiService: ApiService = Api.default

    fun postRequest(url: String, map: Map<String, String>, mHttpRespnse: HttpRespnse) {
        var observer = mApiService.postGetObservable(url, map)
        observer.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<ResponseBody> {
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: ResponseBody) {
                        try {
                            mHttpRespnse._onSucess(t.string())

                        } catch (t: Throwable) {

                        }


                    }

                    override fun onError(e: Throwable) {
                        mHttpRespnse._onError(e)
                    }
                })

    }

    fun postRequestNoRes(url: String, map: Map<String, String>, mHttpRespnse: HttpRespnse) {
        var observer = mApiService.postGetObservable(url, map)
        observer.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<ResponseBody> {
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: ResponseBody) {
                        try {

                            mHttpRespnse._onSucess(t.string())

                        } catch (t: Throwable) {
                            LogUtil.e("---throw:" + t.localizedMessage)
                        }


                    }

                    override fun onError(e: Throwable) {
                        LogUtil.e("--onError=" + e.localizedMessage)
                        mHttpRespnse._onError(e)
                    }
                })

    }

    fun getRequest(url: String, map: Map<String, String>, mHttpRespnse: HttpRespnse) {
        var observer = mApiService.getObservable(url, map)
        observer.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<ResponseBody> {
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: ResponseBody) {
                        try {
                            mHttpRespnse._onSucess(t.string())
                        } catch (t: Throwable) {
                            LogUtil.e("---throw:" + t.localizedMessage)
                        }
                    }

                    override fun onError(e: Throwable) {
                        LogUtil.e("--onError=" + e.localizedMessage)
                        mHttpRespnse._onError(e)
                    }
                })

    }

    fun getRequest(url: String, mHttpRespnse: HttpRespnse) {
        var observer = mApiService.getObservable(url)
        observer.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<ResponseBody> {
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: ResponseBody) {
                        try {
                            if (TextUtils.isEmpty(t.string()))
                                return

                            mHttpRespnse._onSucess(t.string())

                        } catch (t: Throwable) {
                            LogUtil.e("---throw:" + t.localizedMessage)
                        }
                    }

                    override fun onError(e: Throwable) {
                        LogUtil.e("--onError=" + e.localizedMessage)
                    }
                })

    }

    fun upLoadData(url: String, strMap: Map<String, String>, mapImg: HashMap<String, RequestBody>?, mHttpRespnse: HttpRespnse) {
        var observer = mApiService.upLoadData(url, strMap, mapImg)
        observer.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<ResponseBody> {
                    override fun onComplete() {
                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: ResponseBody) {

                        try {
                            if (TextUtils.isEmpty(t.string()))
                                return

                            mHttpRespnse._onSucess(t.string())

                        } catch (t: Throwable) {
                            LogUtil.e("---throw:" + t.localizedMessage)
                        }
                    }

                    override fun onError(e: Throwable) {
                        LogUtil.e("--onError=" + e.localizedMessage)
                        mHttpRespnse._onError(e)
                    }
                })

    }


    fun upLoadData(url: String, mapImg: HashMap<String, RequestBody>?, mHttpRespnse: HttpRespnse) {
        var observer = mApiService.upLoadData(url, mapImg)
        observer.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<ResponseBody> {
                    override fun onComplete() {

                    }

                    override fun onSubscribe(d: Disposable) {
                    }

                    override fun onNext(t: ResponseBody) {
                        try {

                            mHttpRespnse._onSucess(t.string())

                        } catch (t: Throwable) {
                            LogUtil.e("---throw:" + t.localizedMessage)
                        }
                    }

                    override fun onError(e: Throwable) {
                        LogUtil.e("--onError=" + e.localizedMessage)
                        mHttpRespnse._onError(e)
                    }
                })

    }
}
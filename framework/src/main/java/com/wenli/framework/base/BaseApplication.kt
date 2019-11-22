package  com.wenli.framework.base

import android.app.Application

open class BaseApplication : Application() {
    companion object {
        val isDebug: Boolean = true
        var application = Helper.instance
    }

    private object Helper {
        var instance = BaseApplication()
    }

    override fun onCreate() {
        super.onCreate()
        application = this
    }

}
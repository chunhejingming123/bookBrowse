package  com.wenli.framework.util

import android.app.Activity
import java.util.*


/**
 * 作者 :created  by suochunming
 * 日期：2018/8/16 0016:16
 * activity 管理类
 */

object GreenActivityManager {

    var stackActivity: Stack<Activity>? = null


    /**
     * 添加activity 到栈中 好管理
     */
    fun registerActivity(activity: Activity) {
        if (null == stackActivity) {
            stackActivity = Stack<Activity>()
        }
        if (!stackActivity?.contains(activity)!!) {
            stackActivity?.add(activity)
        }
    }

    /**
     * 清空所有activity
     */
    fun finishAllActivity() {
        stackActivity?.clear()
    }

    /**
     * 退出栈中的某个activity
     */
    fun destoryActivity(activity: Activity) {
        activity.finish()
        if (stackActivity?.contains(activity)!!) {
            stackActivity?.remove(activity)
        }
        activity == null

    }

    /**
     * 清空所有 除指定的activity
     */
    fun clearAllException(cla: Class<Activity>) {
        while (true) {
            var currrent: Activity = getCurrentActivity() ?: break

            if (currrent.localClassName.equals(cla)) {
                break
            }
            destoryActivity(currrent)
        }

    }

    /**
     * 获取当前栈顶的元素
     */
    fun getCurrentActivity(): Activity {
        return stackActivity!!.lastElement()
    }

}
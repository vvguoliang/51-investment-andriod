package sp51.spotpass.com.spotpass.base

/**
 * @Time : 2018/5/2 no 下午2:14
 * @USER : vvguoliang
 * @File : BaseActivityManager.java
 * @Software: Android Studio
 *code is far away from bugs with the god animal protecting
 *   I love animals. They taste delicious.
 * ***┏┓   ┏ ┓
 * **┏┛┻━━━┛ ┻┓
 * **┃   ☃   ┃
 * **┃ ┳┛  ┗┳ ┃
 * **┃    ┻   ┃
 * **┗━┓    ┏━┛
 * ****┃    ┗━━━┓
 * ****┃ 神兽保佑 ┣┓
 * ****┃ 永无BUG！┏┛
 * ****┗┓┓┏━┳┓┏┛┏┛
 * ******┃┫┫  ┃┫┫
 * ******┗┻┛  ┗┻┛
 * 类功能介绍
 * TODO 堆栈管理器
 */


import android.app.Activity
import android.text.TextUtils

import java.util.Stack

class BaseActivityManager private constructor() {

    fun popActivity() {
        var activity: Activity? = activityStack!!.lastElement()
        if (activity != null) {
            activity.finish()
            activity = null
        }
    }

    fun remove(activity: Activity?) {
        if (activity != null) {
            activityStack!!.remove(activity)
        }
    }

    fun popActivity(activity: Activity?) {
        val activity = activity
        if (activity != null) {
            activity.finish()
            activityStack!!.remove(activity)
        }
    }

    fun currentActivity(): Activity? {
        return if (activityStack == null || activityStack!!.size == 0) {
            null
        } else activityStack!!.lastElement()
    }

    fun pushActivity(activity: Activity) {
        if (activityStack == null) {
            activityStack = Stack()
        }
        activityStack!!.add(activity)
    }

    fun popAllActivityExceptOne(cls: Class<*>) {
        while (true) {
            val activity = currentActivity() ?: break
            if (activity.javaClass == cls) {
                break
            }
            popActivity(activity)
        }
    }

    //销毁所有
    fun finishAll() {
        for (activity in activityStack!!) {
            if (!activity.isFinishing) {
                activity.finish()
            }
        }
    }

    fun finshAllActivityExceptOne(cls: Class<*>) {
        for (i in activityStack!!.indices) {
            if (activityStack!![i].javaClass != cls) {
                activityStack!![i].finish()
            }
        }
    }

    fun popActivityOne(cls: Class<*>?) {
        if (TextUtils.isEmpty(cls.toString()))
            return

        for (i in activityStack!!.indices) {
            if (activityStack!![i].javaClass == cls) {
                activityStack!![i].finish()
            }

            // String name1 = activityStack.get(i).getClass().getName();
            // String name2 = cls.getName();
            // if(name1.equals(name2)){
            // activityStack.get(i).finish();
            // }
            // }
        }
    }

    fun exitApp() {
        while (true) {
            val activity = currentActivity() ?: break
            popActivity(activity)
        }
    }

    fun isStart(cls: Class<*>): Boolean? {
        var start: Boolean? = false
        for (i in activityStack!!.indices) {
            if (activityStack!![i].javaClass == cls) {
                start = true
                break
            }
        }
        return start
    }

    /**
     * 结束所有Activity
     */
    fun finishAllActivity() {
        var i = 0
        val size = activityStack!!.size
        while (i < size) {
            if (null != activityStack!![i]) {
                activityStack!![i].finish()
            }
            i++
        }
        activityStack!!.clear()
    }

    companion object {

        private var activityStack: Stack<Activity>? = null
        private var instance: BaseActivityManager? = null

        val activityManager: BaseActivityManager
            get() {
                if (instance == null) {
                    instance = BaseActivityManager()
                }
                return instance!!
            }
    }
}


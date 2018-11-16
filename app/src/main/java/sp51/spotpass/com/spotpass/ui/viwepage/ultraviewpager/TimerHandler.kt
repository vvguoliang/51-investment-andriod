package sp51.spotpass.com.spotpass.ui.viwepage.ultraviewpager

import android.os.Handler
import android.os.Message
import android.util.SparseIntArray

/**
 * @Time : 2018/5/23 no 19:34
 * @USER : vvguoliang
 * @File : TimerHandler.java
 * @Software: Android Studio
 *code is far away from bugs with the god animal protecting
 *   I love animals. They taste delicious.
 * ***┏┓   ┏ ┓
 * **┏┛┻━━━┛ ┻┓
 * **┃        ┃
 * **┃ ┳┛  ┗┳ ┃
 * **┃    ┻   ┃
 * **┗━┓    ┏━┛
 * ****┃    ┗━━━┓
 * ****┃ 神兽保佑 ┣┓
 * ****┃ 永无BUG！┏┛
 * ****┗┓┓┏━┳┓┏┛┏┛
 * ******┃┫┫  ┃┫┫
 * ******┗┻┛  ┗┻┛
 */
class TimerHandler(internal var listener: TimerHandlerListener?, internal var interval: Long) : Handler() {

    internal var specialInterval: SparseIntArray? = null
    var isStopped = true

    interface TimerHandlerListener {
        val nextItem: Int
        fun callBack()
    }

    override fun handleMessage(msg: Message) {
        if (MSG_TIMER_ID == msg.what) {
            if (listener != null) {
                val nextIndex = listener!!.nextItem
                listener!!.callBack()
                tick(nextIndex)
            }
        }
    }

    fun tick(index: Int) {
        sendEmptyMessageDelayed(TimerHandler.MSG_TIMER_ID, getNextInterval(index))
    }

    private fun getNextInterval(index: Int): Long {
        var next = interval
        if (specialInterval != null) {
            val has = specialInterval!!.get(index, -1).toLong()
            if (has > 0) {
                next = has
            }
        }
        return next
    }

    fun setListener(listener: TimerHandlerListener) {
        this.listener = listener
    }

    fun setSpecialInterval(specialInterval: SparseIntArray) {
        this.specialInterval = specialInterval
    }

    companion object {

        internal val MSG_TIMER_ID = 87108
    }
}

package sp51.spotpass.com.spotpass.ui.utils

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.CountDownTimer
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.Button

/**
 * @Time : 2018/4/19 no 上午3:40
 * @USER : vvguoliang
 * @File : CountDownTimerUtils.java
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
 *
 * 倒计时封装
 */
class CountDownTimerUtils
/**
 * @param textView          The TextView
 * @param millisInFuture    The number of millis in the future from the call
 * to [.start] until the countdown is done and [.onFinish]
 * is called.
 * @param countDownInterval The interval along the way to receiver
 * [.onTick] callbacks.
 */
(private val mButton: Button, millisInFuture: Long, countDownInterval: Long) : CountDownTimer(millisInFuture, countDownInterval) {

    @SuppressLint("SetTextI18n")
    override fun onTick(millisInFuture: Long) {
        mButton.isClickable = false //设置不可点击
        mButton.text = (millisInFuture / 1000).toString() + "秒"  //设置倒计时时间
        //        mButton.setBackgroundResource(R.drawable.bg_identify_code_press); //设置按钮为灰色，这时是不能点击的

        /*
         * 超链接 URLSpan
         * 文字背景颜色 BackgroundColorSpan
         * 文字颜色 ForegroundColorSpan
         * 字体大小 AbsoluteSizeSpan
         * 粗体、斜体 StyleSpan
         * 删除线 StrikethroughSpan
         * 下划线 UnderlineSpan
         * 图片 ImageSpan
         * http://blog.csdn.net/ah200614435/article/details/7914459
         */
        val spannableString = SpannableString(mButton.text.toString())  //获取按钮上的文字
        val span = ForegroundColorSpan(Color.RED)
        /*
         * public void setSpan(Object what, int start, int end, int flags) {
         * 主要是start跟end，start是起始位置,无论中英文，都算一个。
         * 从0开始计算起。end是结束位置，所以处理的文字，包含开始位置，但不包含结束位置。
         */
        spannableString.setSpan(span, 0, 2, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)//将倒计时的时间设置为红色
        mButton.text = spannableString
    }

    override fun onFinish() {
        mButton.text = "重新获取"
        mButton.isClickable = true//重新获得点击
        //        mButton.setBackgroundResource(R.drawable.bg_identify_code_normal);  //还原背景色
    }
}
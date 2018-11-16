package sp51.spotpass.com.spotpass.ui.kchartlib

/**
 * @Time : 2018/5/10 no 18:13
 * @USER : vvguoliang
 * @File : Utils.java
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
 * 工具类
 */
internal object Utils {

    /**
     * 检查实体是否为null
     * @param object 实体
     * @param msg 异常信息
     * @throws NullPointerException 实体为空时抛出异常
     */
    fun checkNull(`object`: Any?, msg: String) {
        if (`object` == null) {
            throw NullPointerException(msg)
        }
    }
}

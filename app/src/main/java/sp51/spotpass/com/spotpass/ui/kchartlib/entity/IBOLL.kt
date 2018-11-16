package sp51.spotpass.com.spotpass.ui.kchartlib.entity

/**
 * @Time : 2018/5/10 no 18:56
 * @USER : vvguoliang
 * @File : IBOLL.java
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
/**
 * 布林线指标接口
 * @see [](https://baike.baidu.com/item/%E5%B8%83%E6%9E%97%E7%BA%BF%E6%8C%87%E6%A0%87/3325894)相关说明
 */

interface IBOLL {

    /**
     * 上轨线
     */
    val up: Float

    /**
     * 中轨线
     */
    val mb: Float

    /**
     * 下轨线
     */
    val dn: Float
}
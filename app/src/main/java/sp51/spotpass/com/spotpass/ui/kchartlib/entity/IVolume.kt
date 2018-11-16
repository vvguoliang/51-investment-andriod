package sp51.spotpass.com.spotpass.ui.kchartlib.entity

/**
 * @Time : 2018/5/10 no 18:58
 * @USER : vvguoliang
 * @File : IVolume.java
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
 * 成交量接口
 */

interface IVolume {

    /**
     * 开盘价
     */
    val openPrice: Float

    /**
     * 收盘价
     */
    val closePrice: Float

    /**
     * 成交量
     */
    val volume: Float

    /**
     * 五(月，日，时，分，5分等)均量
     */
    val mA5Volume: Float

    /**
     * 十(月，日，时，分，5分等)均量
     */
    val mA10Volume: Float
}
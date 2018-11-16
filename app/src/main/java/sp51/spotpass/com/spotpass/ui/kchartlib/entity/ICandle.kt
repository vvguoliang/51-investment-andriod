package sp51.spotpass.com.spotpass.ui.kchartlib.entity

/**
 * @Time : 2018/5/10 no 18:56
 * @USER : vvguoliang
 * @File : ICandle.java
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
 * 蜡烛图实体接口
 */

interface ICandle {

    /**
     * 开盘价
     */
    val openPrice: Float

    /**
     * 最高价
     */
    val highPrice: Float

    /**
     * 最低价
     */
    val lowPrice: Float

    /**
     * 收盘价
     */
    val closePrice: Float

    /**
     * 五(月，日，时，分，5分等)均价
     */
    val mA5Price: Float

    /**
     * 十(月，日，时，分，5分等)均价
     */
    val mA10Price: Float

    /**
     * 二十(月，日，时，分，5分等)均价
     */
    val mA20Price: Float
}

package sp51.spotpass.com.spotpass.ui.kchartlib.entity

/**
 * @Time : 2018/5/10 no 18:57
 * @USER : vvguoliang
 * @File : IMACD.java
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
 * MACD指标(指数平滑移动平均线)接口
 * @see [](https://baike.baidu.com/item/MACD指标)相关说明
 */

interface IMACD {


    /**
     * DEA值
     */
    val dea: Float

    /**
     * DIF值
     */
    val dif: Float

    /**
     * MACD值
     */
    val macd: Float

}

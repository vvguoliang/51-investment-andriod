package sp51.spotpass.com.spotpass.ui.kchartlib.entity

import java.util.*

/**
 * @Time : 2018/5/10 no 18:22
 * @USER : vvguoliang
 * @File : IMinuteLine.java
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
 * 分时图实体接口
 */
interface IMinuteLine {

    /**
     * @return 获取均价
     */
    val avg: Float

    /**
     * @return 获取成交价
     */
    val price: Float

    /**
     * 该指标对应的时间
     */
    val time: Date?

    /**
     * 成交量
     */
    val volume: Float
}

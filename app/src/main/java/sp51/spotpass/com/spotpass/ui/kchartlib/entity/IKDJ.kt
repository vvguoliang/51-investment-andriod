package sp51.spotpass.com.spotpass.ui.kchartlib.entity

/**
 * @Time : 2018/5/10 no 18:57
 * @USER : vvguoliang
 * @File : IKDJ.java
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
 * KDJ指标(随机指标)接口
 * @see [](https://baike.baidu.com/item/KDJ%E6%8C%87%E6%A0%87/6328421?fr=aladdin&fromid=3423560&fromtitle=kdj)相关说明
 * Created by tifezh on 2016/6/10.
 */
interface IKDJ {

    /**
     * K值
     */
    val k: Float

    /**
     * D值
     */
    val d: Float

    /**
     * J值
     */
    val j: Float

}

package sp51.spotpass.com.spotpass.ui.viwepage.holder

/**
 * @Time : 2018/5/4 no 11:50
 * @USER : vvguoliang
 * @File : MZHolderCreator.java
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
interface MZHolderCreator<VH : MZViewHolder<*>> {
    /**
     * 创建ViewHolder
     * @return
     */
    fun createViewHolder(): VH
}

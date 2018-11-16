package sp51.spotpass.com.spotpass.ui.viwepage.ultraviewpager

import android.graphics.Bitmap

/**
 * @Time : 2018/5/23 no 19:32
 * @USER : vvguoliang
 * @File : IUltraIndicatorBuilder.java
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
interface IUltraIndicatorBuilder {
    /**
     * Set focused color for indicator.
     * @param focusColor
     * @return
     */
    fun setFocusColor(focusColor: Int): IUltraIndicatorBuilder

    /**
     * Set normal color for indicator.
     * @param normalColor
     * @return
     */
    fun setNormalColor(normalColor: Int): IUltraIndicatorBuilder

    /**
     * Set stroke color for indicator.
     * @param strokeColor
     * @return
     */
    fun setStrokeColor(strokeColor: Int): IUltraIndicatorBuilder

    /**
     * Set stroke width for indicator.
     * @param strokeWidth
     * @return
     */
    fun setStrokeWidth(strokeWidth: Int): IUltraIndicatorBuilder

    /**
     * Set spacing between indicator item ，the default value is item's height.
     * @param indicatorPadding
     * @return
     */
    fun setIndicatorPadding(indicatorPadding: Int): IUltraIndicatorBuilder

    /**
     * Set the corner radius of the indicator item.
     * @param radius
     * @return
     */
    fun setRadius(radius: Int): IUltraIndicatorBuilder

    /**
     * Sets the orientation of the layout.
     * @param orientation
     * @return
     */
    fun setOrientation(orientation: UltraViewPager.Orientation): IUltraIndicatorBuilder

    /**
     * Set the location at which the indicator should appear on the screen.
     *
     * @param gravity android.view.Gravity
     * @return
     */
    fun setGravity(gravity: Int): IUltraIndicatorBuilder

    /**
     * Set focused resource ID for indicator.
     * @param focusResId
     * @return
     */
    fun setFocusResId(focusResId: Int): IUltraIndicatorBuilder

    /**
     * Set normal resource ID for indicator.
     * @param normalResId
     * @return
     */
    fun setNormalResId(normalResId: Int): IUltraIndicatorBuilder

    /**
     * Set focused icon for indicator.
     * @param bitmap
     * @return
     */
    fun setFocusIcon(bitmap: Bitmap): IUltraIndicatorBuilder

    /**
     * Set normal icon for indicator.
     * @param bitmap
     * @return
     */
    fun setNormalIcon(bitmap: Bitmap): IUltraIndicatorBuilder

    /**
     * Set margins for indicator.
     * @param left   the left margin in pixels
     * @param top    the top margin in pixels
     * @param right  the right margin in pixels
     * @param bottom the bottom margin in pixels
     * @return
     */
    fun setMargin(left: Int, top: Int, right: Int, bottom: Int): IUltraIndicatorBuilder

    /**
     * Combine all of the options that have been set and return a new IUltraIndicatorBuilder object.
     */
    fun build()
}

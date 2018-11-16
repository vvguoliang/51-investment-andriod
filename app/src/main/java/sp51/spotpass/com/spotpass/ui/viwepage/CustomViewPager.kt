package sp51.spotpass.com.spotpass.ui.viwepage

import android.annotation.SuppressLint
import android.content.Context
import android.support.v4.view.ViewPager
import android.util.AttributeSet
import android.util.SparseArray
import android.view.View
import java.util.*

/**
 * @Time : 2018/5/4 no 11:59
 * @USER : vvguoliang
 * @File : CustomViewPager1.java
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
class CustomViewPager : ViewPager {
    private val childCenterXAbs = ArrayList<Int>()
    @SuppressLint("UseSparseArrays")
    private val childIndex = SparseArray<Int>()

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    private fun init() {
        clipToPadding = false
        overScrollMode = View.OVER_SCROLL_NEVER
    }


    /**
     * @param childCount
     * @param n
     * @return 第n个位置的child 的绘制索引
     */
    override fun getChildDrawingOrder(childCount: Int, n: Int): Int {
        if (n == 0 || childIndex.size() != childCount) {
            childCenterXAbs.clear()
            childIndex.clear()
            val viewCenterX = getViewCenterX(this)
            for (i in 0 until childCount) {
                var indexAbs = Math.abs(viewCenterX - getViewCenterX(getChildAt(i)))
                //两个距离相同，后来的那个做自增，从而保持abs不同
                if (childIndex.get(indexAbs) != null) {
                    ++indexAbs
                }
                childCenterXAbs.add(indexAbs)
                childIndex.append(indexAbs, i)
            }
            Collections.sort(childCenterXAbs)//1,0,2  0,1,2
        }
        //那个item距离中心点远一些，就先draw它。（最近的就是中间放大的item,最后draw）
        return childIndex.get(childCenterXAbs[childCount - 1 - n])
    }

    private fun getViewCenterX(view: View): Int {
        val array = IntArray(2)
        view.getLocationOnScreen(array)
        return array[0] + view.width / 2
    }
}

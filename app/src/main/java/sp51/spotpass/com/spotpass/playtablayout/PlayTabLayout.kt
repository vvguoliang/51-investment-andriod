package sp51.spotpass.com.spotpass.playtablayout

import android.animation.Animator
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.v4.view.ViewCompat
import android.support.v4.view.animation.FastOutSlowInInterpolator
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.ViewAnimationUtils
import android.widget.FrameLayout
import sp51.spotpass.com.spotpass.R
import kotlin.properties.Delegates

@RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
/**
 * @Time : 2018/5/8 no 12:49
 * @USER : vvguoliang
 * @File : PlayTabLayout.java
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
class PlayTabLayout : FrameLayout, TouchableTabLayout.TabClickListener {

    private val ANIMATION_DURATION = 550L

    private var animator: Animator? = null
    private var tabColorHolder: FrameLayout by Delegates.notNull()
    var tabLayout: TouchableTabLayout  by Delegates.notNull()

    var colors = intArrayOf()
        set(value) {
            setBackgroundColor(context.color(value[0]))
            field = value
        }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    init {
        ViewCompat.setElevation(this, 0F)
        ViewCompat.setTranslationZ(this, 0F)
        tabLayout = TouchableTabLayout(context)
        tabColorHolder = FrameLayout(context)
        addView(tabColorHolder, params.MATCH_PARENT, params.MATCH_PARENT)
        addView(tabLayout, params.MATCH_PARENT, params.MATCH_PARENT)
        tabLayout.tabClickListener = this
    }

    override fun onTabClicked(selected: Int, fromTouch: Boolean, event: MotionEvent?) {
        on21orAbove(up = {
            animate(fromTouch, event, selected)
        }, down = {
            ObjectAnimator.ofInt(tabColorHolder,
                    tabColorHolder.BACKGROUND_COLOR, color(colors[selected]))
                    .apply {
                        duration = ANIMATION_DURATION
                        setEvaluator(ArgbEvaluator())
                        interpolator = FastOutSlowInInterpolator()
                        start()
                    }
        })
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun animate(fromTouch: Boolean, event: MotionEvent?, selected: Int) {
        val startRadius = 0F
        val endRadius = Math.hypot(tabLayout.width.toDouble(), tabLayout.height.toDouble()).toFloat()
        val color = color(colors[selected])
        animator?.cancel()
        animator = if (fromTouch && event != null) {
            ViewAnimationUtils.createCircularReveal(tabColorHolder, event.rawX.toInt(), event.y.toInt(), startRadius, endRadius)
        } else {
            if (ViewCompat.isAttachedToWindow(tabColorHolder)) {
                tabLayout.mViewPager?.let {
                    fun dimen(dimenResId: Int) = context.resources.getDimension(dimenResId).toInt()
                    val oneTabWidth = tabLayout.mSelectedTab?.mView?.width ?: 1
                    val centerX = (oneTabWidth / 2) + oneTabWidth * selected
                    val hasIcon = tabLayout.getTabAt(selected)?.getIcon() != null
                    val paddingBottom = if (hasIcon)
                        dimen(R.dimen.commont_dp5)
                    else
                        dimen(R.dimen.commont_dp5)
                    val centerY = tabColorHolder.height - paddingBottom
                    ViewAnimationUtils.createCircularReveal(tabColorHolder, centerX, centerY, startRadius, endRadius)
                }
            } else {
                setBackgroundColor(color)
                null
            }
        }
        animator?.run {
            duration = ANIMATION_DURATION
            interpolator = FastOutSlowInInterpolator()

            listen(start = {
                tabColorHolder.setBackgroundColor(color)
            }, end = {
                setBackgroundColor(color)
            }, cancel = {
                setBackgroundColor(color)
            })
            start()
        }
    }

    private fun color(colorResId: Int) = context.color(colorResId)
}
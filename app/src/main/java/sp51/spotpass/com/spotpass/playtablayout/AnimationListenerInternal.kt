package sp51.spotpass.com.spotpass.playtablayout

import android.animation.Animator

/**
 * @Time : 2018/5/8 no 12:46
 * @USER : vvguoliang
 * @File : AnimationListenerInternal.java
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
private class AnimationListenerInternal(private val start: () -> Unit = {},
                                        private val end: () -> Unit = {},
                                        private val cancel: () -> Unit = {}) : Animator.AnimatorListener {

    override fun onAnimationEnd(animation: Animator?) {
        end()
    }

    override fun onAnimationCancel(animation: Animator?) {
        cancel()
    }

    override fun onAnimationStart(animation: Animator?) {
        start()
    }

    override fun onAnimationRepeat(animation: Animator?) {
    }
}

internal fun Animator.listen(start: () -> Unit = {},
                             end: () -> Unit = {},
                             cancel: () -> Unit = {}) {
    addListener(AnimationListenerInternal(start, end, cancel))
}

internal fun Animator.onStart(start: () -> Unit) {
    addListener(AnimationListenerInternal(start = start))
}

internal fun Animator.onEnd(end: () -> Unit) {
    addListener(AnimationListenerInternal(end = end))
}

internal fun Animator.onCancel(cancel: () -> Unit) {
    addListener(AnimationListenerInternal(cancel = cancel))
}
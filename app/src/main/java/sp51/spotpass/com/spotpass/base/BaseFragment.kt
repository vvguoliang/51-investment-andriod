package sp51.spotpass.com.spotpass.base

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import com.umeng.analytics.MobclickAgent

/**
 * @Time : 2018/3/20 no 下午4:51
 * @USER : vvguoliang
 * @File : BaseFragment.java
 * @Software: Android Studio
 *code is far away from bugs with the god animal protecting
 *   I love animals. They taste delicious.
 * ***┏┓   ┏ ┓
 * **┏┛┻━━━┛ ┻┓
 * **┃   ☃   ┃
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
open class BaseFragment : Fragment() {

    override fun onAttach(context: Context?) {
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        MobclickAgent.setScenarioType(activity, MobclickAgent.EScenarioType.E_UM_NORMAL)
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}
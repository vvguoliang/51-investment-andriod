package sp51.spotpass.com.spotpass.ui.utils

import android.content.Context
import android.content.SharedPreferences
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*

@Suppress("NAME_SHADOWING")
/**
 * @Time : 2018/3/22 no 上午11:07
 * @USER : vvguoliang
 * @File : SPUtils.java
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
class SPUtils private constructor(context: Context, spName: String) {
    private val sp: SharedPreferences

    init {
        sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE)
    }

    /**
     * SP中写入String
     * @param key   键
     * *
     * @param value 值
     */
    fun put(key: String, value: String) {
        sp.edit().putString(key, value).apply()
    }

    /**
     * SP中读取String
     * @param key          键
     * *
     * @param defaultValue 默认值
     * *
     * @return 存在返回对应值，不存在返回默认值`defaultValue`
     */
    @JvmOverloads
    fun getString(key: String, defaultValue: String = ""): String {
        return sp.getString(key, defaultValue)
    }

    /**
     * SP中写入int
     * @param key   键
     * *
     * @param value 值
     */
    fun put(key: String, value: Int) {
        sp.edit().putInt(key, value).apply()
    }

    /**
     * SP中读取int
     * @param key          键
     * *
     * @param defaultValue 默认值
     * *
     * @return 存在返回对应值，不存在返回默认值`defaultValue`
     */
    @JvmOverloads
    fun getInt(key: String, defaultValue: Int = -1): Int {
        return sp.getInt(key, defaultValue)
    }

    /**
     * SP中写入long
     * @param key   键
     * *
     * @param value 值
     */
    fun put(key: String, value: Long) {
        sp.edit().putLong(key, value).apply()
    }

    /**
     * SP中读取long
     * @param key          键
     * *
     * @param defaultValue 默认值
     * *
     * @return 存在返回对应值，不存在返回默认值`defaultValue`
     */
    @JvmOverloads
    fun getLong(key: String, defaultValue: Long = -1L): Long {
        return sp.getLong(key, defaultValue)
    }

    /**
     * SP中写入float
     * @param key   键
     * *
     * @param value 值
     */
    fun put(key: String, value: Float) {
        sp.edit().putFloat(key, value).apply()
    }

    /**
     * SP中读取float
     * @param key          键
     * *
     * @param defaultValue 默认值
     * *
     * @return 存在返回对应值，不存在返回默认值`defaultValue`
     */
    @JvmOverloads
    fun getFloat(key: String, defaultValue: Float = -1f): Float {
        return sp.getFloat(key, defaultValue)
    }

    /**
     * SP中写入boolean
     * @param key   键
     * *
     * @param value 值
     */
    fun put(key: String, value: Boolean) {
        sp.edit().putBoolean(key, value).apply()
    }

    /**
     * SP中读取boolean
     * @param key          键
     * *
     * @param defaultValue 默认值
     * *
     * @return 存在返回对应值，不存在返回默认值`defaultValue`
     */
    @JvmOverloads
    fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return sp.getBoolean(key, defaultValue)
    }

    /**
     * SP中写入String集合
     * @param key    键
     * *
     * @param values 值
     */
    fun put(key: String, values: Set<String>) {
        sp.edit().putStringSet(key, values).apply()
    }

    /**
     * SP中读取StringSet
     * @param key          键
     * *
     * @param defaultValue 默认值
     * *
     * @return 存在返回对应值，不存在返回默认值`defaultValue`
     */
    @JvmOverloads
    fun getStringSet(key: String, defaultValue: Set<String> = Collections.emptySet()): Set<String> {
        return sp.getStringSet(key, defaultValue)
    }

    /**
     * SP中获取所有键值对
     * @return Map对象
     */
    val all: Map<String, *>
        get() = sp.all

    /**
     * SP中移除该key
     * @param key 键
     */
    fun remove(key: String) {
        sp.edit().remove(key).apply()
    }

    /**
     * SP中是否存在该key
     * @param key 键
     * *
     * @return `true`: 存在<br></br>`false`: 不存在
     */
    operator fun contains(key: String): Boolean {
        return sp.contains(key)
    }

    fun saveInfo(context: Context, datas: List<Map<String, Any>>): String {
        val mJsonArray = JSONArray()
        for (i in datas.indices) {
            val itemMap = datas[i]
            val iterator = itemMap.entries.iterator()
            val `object` = JSONObject()
            while (iterator.hasNext()) {
                val entry = iterator.next()
                try {
                    `object`.put(entry.key, entry.value)
                } catch (ignored: JSONException) {

                }

            }
            mJsonArray.put(`object`)
        }
        return mJsonArray.toString()
    }

    fun getInfo(context: Context, result: String): List<Map<String, Any>> {
        val datas = ArrayList<Map<String, Any>>()
        try {
            val array = JSONArray(result)
            for (i in 0 until array.length()) {
                val itemObject = array.getJSONObject(i)
                val itemMap = HashMap<String, Any>()
                val names = itemObject.names()
                if (names != null) {
                    for (j in 0 until names.length()) {
                        val name = names.getString(j)
                        val value = itemObject.getString(name)
                        itemMap[name] = value
                    }
                }
                datas.add(itemMap)
            }
        } catch (ignored: JSONException) {

        }

        return datas
    }

    /**
     * SP中清除所有数据
     */
    fun clear() {
        sp.edit().clear().apply()
    }

    companion object {

        private val sSPMap = HashMap<String, SPUtils>()

        /**
         * 获取SP实例
         * @param spName sp名
         * *
         * @return [SPUtils]
         */
        fun getInstance(context: Context, spName: String): SPUtils {
            var spName = spName
            if (isSpace(spName)) spName = "spUtils"
            var sp: SPUtils? = sSPMap[spName]
            if (sp == null) {
                sp = SPUtils(context, spName)
                sSPMap.put(spName, sp)
            }
            return sp
        }

        private fun isSpace(s: String?): Boolean {
            if (s == null) return true
            var i = 0
            val len = s.length
            while (i < len) {
                if (!Character.isWhitespace(s[i])) {
                    return false
                }
                ++i
            }
            return true
        }
    }
}
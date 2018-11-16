package sp51.spotpass.com.spotpass.ui.utils

import android.annotation.SuppressLint
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

/**
 * @Time : 2018/5/24 no 16:21
 * @USER : vvguoliang
 * @File : IdentityUtils.java
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
 * Created by Arjun on 2017/4/25.
 * 身份证有效性校验
 */

class IdentityUtils {
    init {
        setWiBuffer()
    }

    companion object {
        //    位权值数组
        private val Wi = ByteArray(17)
        //    身份证前部分字符数
        private val fPart: Byte = 6
        //    身份证算法求模关键值
        private val fMod: Byte = 11
        //    旧身份证长度
        private val oldIDLen: Byte = 15
        //    新身份证长度
        private val newIDLen: Byte = 18
        //    新身份证年份标志
        private val yearFlag = "19"
        //    校验码串
        private val CheckCode = "10X98765432"
        //    最小的行政区划码
        private val minCode = 150000
        //    最大的行政区划码
        private val maxCode = 700000
        //    旧身份证号码
        //    private String oldIDCard="";
        //    新身份证号码
        //    private String newIDCard="";
        //    地区及编码


        //private String Area[][2] =
        private fun setWiBuffer() {
            for (i in Wi.indices) {
                val k = Math.pow(2.0, (Wi.size - i).toDouble()).toInt()
                Wi[i] = (k % fMod).toByte()
            }
        }

        //获取新身份证的最后一位:检验位
        private fun getCheckFlag(idCard: String): String {
            var sum = 0
            //进行加权求和
            for (i in 0..16) {
                sum += Integer.parseInt(idCard.substring(i, i + 1)) * Wi[i]
            }
            //取模运算，得到模值
            val iCode = (sum % fMod).toByte()
            return CheckCode.substring(iCode.toInt(), iCode + 1)
        }

        //判断串长度的合法性
        private fun checkLength(idCard: String, newIDFlag: Boolean): Boolean {
            var newIDFlag = newIDFlag
            val right = idCard.length == oldIDLen.toInt() || idCard.length == newIDLen.toInt()
            newIDFlag = false
            if (right) {
                newIDFlag = idCard.length == newIDLen.toInt()
            }
            return right
        }

        //获取时间串
        private fun getIDDate(idCard: String, newIDFlag: Boolean): String {
            var dateStr = ""
            if (newIDFlag)
                dateStr = idCard.substring(fPart.toInt(), fPart + 8)
            else
                dateStr = yearFlag + idCard.substring(fPart.toInt(), fPart + 6)
            return dateStr
        }

        //判断时间合法性
        @SuppressLint("SimpleDateFormat")
        private fun checkDate(dateSource: String): Boolean {
            val dateStr = dateSource.substring(0, 4) + "-" + dateSource.substring(4, 6) + "-" + dateSource.substring(6, 8)
            println(dateStr)
            val df = SimpleDateFormat("yyyy-MM-dd")
            try {
                val date = df.parse(dateStr)
                return date != null
            } catch (e: java.text.ParseException) {
                e.printStackTrace()
                return false
            }

        }

        //旧身份证转换成新身份证号码
        fun getNewIDCard(oldIDCard: String): String {
            //初始化方法
            IdentityUtils.setWiBuffer()
            if (!checkIDCard(oldIDCard)) {
                return oldIDCard
            }
            var newIDCard = oldIDCard.substring(0, fPart.toInt())
            newIDCard += yearFlag
            newIDCard += oldIDCard.substring(fPart.toInt(), oldIDCard.length)
            val ch = getCheckFlag(newIDCard)
            newIDCard += ch
            return newIDCard
        }

        //新身份证转换成旧身份证号码
        fun getOldIDCard(newIDCard: String): String {
            //初始化方法
            IdentityUtils.setWiBuffer()
            return if (!checkIDCard(newIDCard)) {
                newIDCard
            } else newIDCard.substring(0, fPart.toInt()) + newIDCard.substring(fPart + yearFlag.length, newIDCard.length - 1)
        }

        //判断身份证号码的合法性
        fun checkIDCard(idCard: String): Boolean {
            //初始化方法
            IdentityUtils.setWiBuffer()

            val length = idCard.length
            val isNew: Boolean
            if (length == oldIDLen.toInt())
                isNew = false
            else if (length == newIDLen.toInt())
                isNew = true
            else
                return false

            //String message = "";
            if (!checkLength(idCard, isNew)) {
                //message = "ID长度异常";
                return false
            }
            val idDate = getIDDate(idCard, isNew)
            if (!checkDate(idDate)) {
                //message = "ID时间异常";
                return false
            }
            if (isNew) {
                val checkFlag = getCheckFlag(idCard)
                val theFlag = idCard.substring(idCard.length - 1, idCard.length)
                if (checkFlag != theFlag) {
                    //message = "新身份证校验位异常";
                    return false
                }
            }
            return true
        }

        //获取一个随机的"伪"身份证号码
        fun getRandomIDCard(idNewID: Boolean): String {
            //初始化方法
            IdentityUtils.setWiBuffer()
            val ran = Random()
            var idCard = getAddressCode(ran) + getRandomDate(ran, idNewID) + getIDOrder(ran)
            if (idNewID) {
                val ch = getCheckFlag(idCard)
                idCard += ch
            }
            return idCard
        }

        //产生随机的地区编码
        private fun getAddressCode(ran: Random?): String {
            if (ran == null) {
                return ""
            } else {
                val addrCode = minCode + ran.nextInt(maxCode - minCode)
                return Integer.toString(addrCode)
            }
        }

        //产生随机的出生日期
        private fun getRandomDate(ran: Random?, idNewID: Boolean): String {
            // TODO Auto-generated method stub
            if (ran == null) {
                return ""
            }
            var year = 0
            if (idNewID) {
                year = 1900 + ran.nextInt(2017 - 1900)
            } else {
                year = 1 + ran.nextInt(99)
            }
            val month = 1 + ran.nextInt(12)
            var day = 0
            if (month == 2) {
                day = 1 + ran.nextInt(28)
            } else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
                day = 1 + ran.nextInt(31)
            } else {
                day = 1 + ran.nextInt(30)
            }
            val nf = NumberFormat.getIntegerInstance()
            nf.setMaximumIntegerDigits(2)
            nf.setMinimumIntegerDigits(2)
            return Integer.toString(year) + nf.format(month) + nf.format(day)
        }

        //产生随机的序列号
        private fun getIDOrder(ran: Random?): String {
            // TODO Auto-generated method stub
            val nf = NumberFormat.getIntegerInstance()
            nf.setMaximumIntegerDigits(3)
            nf.setMinimumIntegerDigits(3)
            if (ran == null) {
                return ""
            } else {
                val order = 1 + ran.nextInt(999)
                return nf.format(order)
            }
        }
    }
}
@file:Suppress("NAME_SHADOWING")

package sp51.spotpass.com.spotpass.ui.http

import android.content.Context
import android.graphics.Bitmap
import android.os.Handler
import android.os.Looper
import android.util.Base64
import okhttp3.*
import org.json.JSONException
import org.json.JSONObject
import java.io.*
import java.util.HashMap
import java.util.concurrent.TimeUnit
import okhttp3.RequestBody
import sp51.spotpass.com.spotpass.ui.utils.SPUtils


/**
 * @Time : 2018/4/2 no 下午10:49
 * @USER : vvguoliang
 * @File : OkHttpManager.java
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
class OkHttpManager
/**
 * 构造方法
 */
private constructor() {

    /**
     * okhttpclient实例
     */
    private val mClient: OkHttpClient = OkHttpClient()

    /**
     * 因为我们请求数据一般都是子线程中请求，在这里我们使用了handler
     */
    private val mHandler: Handler

    init {

        /*
          在这里直接设置连接超时.读取超时，写入超时
         */
        mClient.newBuilder().connectTimeout((10 * 1000).toLong(), TimeUnit.SECONDS)
        mClient.newBuilder().readTimeout((10 * 1000).toLong(), TimeUnit.SECONDS)
        mClient.newBuilder().writeTimeout((10 * 1000).toLong(), TimeUnit.SECONDS)

        /*
         * 如果是用的3.0之前的版本  使用以下直接设置连接超时.读取超时，写入超时
         */

        //client.setConnectTimeout(10, TimeUnit.SECONDS);
        //client.setWriteTimeout(10, TimeUnit.SECONDS);
        //client.setReadTimeout(30, TimeUnit.SECONDS);


        /*
         * 初始化handler
         */
        mHandler = Handler(Looper.getMainLooper())
    }

    /*
     * GET方式请求的内部逻辑处理方式，同步的方式
     *
     * @param url
     * @return
     */
    private fun inner_getSync(url: String): Response? {
        val request = Request.Builder().url(url).build()
        var response: Response? = null
        try {
            //同步请求返回的是response对象
            response = mClient.newCall(request).execute()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return response
    }


    /*
     * 同步方法
     */
    private fun inner_getSyncString(url: String): String? {
        var result: String? = null
        try {
            /*
             * 把取得到的结果转为字符串，这里最好用string()
             */
            result = inner_getSync(url)!!.body()!!.string()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return result
    }

    /*
     * 内部逻辑请求的方法
     *
     * @param url
     * @param callBack
     * @return
     */
    private fun inner_getAsync(context: Context, url: String, name: String, callBack: DataCallBack) {
        val request = Request.Builder().url(url).addHeader("Authorization", "Bearer ${SPUtils.getInstance(context, "Authorization").getString("Authorization", "")}").build()

        mClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                deliverDataFailure(request, name, e, callBack)
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                var result: String? = null
                try {
                    result = response.body()!!.string()
                } catch (e: IOException) {
                    deliverDataFailure(request, name, e, callBack)
                }
                response.newBuilder().build()
                deliverDataSuccess(result, name, callBack)
            }
        })
    }
    /*
     * 内部逻辑请求的方法
     *
     * @param url
     * @param callBack
     * @return
     */
    private fun inner_getAsyncAuthorization(context: Context, url: String, name: String, callBack: DataCallBack) {
        val request = Request.Builder().url(url).addHeader("Authorization", "Bearer ${SPUtils.getInstance(context, "Authorization").getString("Authorization", "")}").build()

        mClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                deliverDataFailure(request, name, e, callBack)
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                var result: String? = null
                try {
                    result = response.body()!!.string()
                } catch (e: IOException) {
                    deliverDataFailure(request, name, e, callBack)
                }
                response.newBuilder().build()
                deliverDataSuccess(result, name, callBack)
            }
        })
    }


    /*
     * 分发失败的时候调用
     *
     * @param request
     * @param e
     * @param callBack
     */
    private fun deliverDataFailure(request: Request, name: String, e: IOException, callBack: DataCallBack?) {
        /*
         * 在这里使用异步处理
         */
        mHandler.post { callBack?.requestFailure(request, name, e) }
    }

    /*
     * 分发成功的时候调用
     *
     * @param result
     * @param callBack
     */
    private fun deliverDataSuccess(result: String?, name: String, callBack: DataCallBack?) {
        /*
         * 在这里使用异步线程处理
         */
        mHandler.post {
            if (callBack != null) {
                try {
                    callBack.requestSuccess(result!!, name)
                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
    }

    private fun getjson(result: String): Boolean {
        try {
            JSONObject(result)
            return true
        } catch (e: JSONException) {
            e.printStackTrace()
            return false
        }

    }

    val JSON = MediaType.parse("application/json; charset=utf-8")
    private fun inner_postAsync(context: Context, url: String, name: String, params: Map<String, Any>?, callBack: DataCallBack) {
        var params = params

        val requestBody: RequestBody
        if (params == null) {
            params = HashMap()
        }
        /*
         * 如果是3.0之前版本的，构建表单数据是下面的一句
         */
        //FormEncodingBuilder builder = new FormEncodingBuilder();

        /*
         * 3.0之后版本
         */
        val builder = FormBody.Builder()
        /*
         * 在这对添加的参数进行遍历，map遍历有四种方式，如果想要了解的可以网上查找
         */
        var json: String = ""
        val s = JSONObject()
        for ((key, value1) in params) {
            /*
             * 判断值是否是空的
             */
            val value: String = value1.toString()
            /*
             * 把key和value添加到formbody中
             */
            builder.add(key, value)
            s.put(key, value).toString()
        }
        json = s.toString()
        val body = RequestBody.create(
                JSON,
                json
        )
//        requestBody = builder.build()
        //结果返回
            val request = Request.Builder().url(url).post(body).addHeader("Authorization", "Bearer ${SPUtils.getInstance(context, "Authorization").getString("Authorization", "")}").build()
            mClient.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    deliverDataFailure(request, name, e, callBack)
                }

                @Throws(IOException::class)
                override fun onResponse(call: Call, response: Response) {
                    val result = response.body()!!.string()
                    deliverDataSuccess(result, name, callBack)
                }
            })
    }

//    /*
//     * 下载文件的内部逻辑处理类
//     *
//     * @param url      下载地址
//     * @param desDir   目标地址
//     * @param callBack
//     */
//    private fun inner_downloadAsync(url: String, name: String, desDir: String, callBack: DataCallBack) {
//        val request = Request.Builder().url(url).build()
//        mClient.newCall(request).enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                deliverDataFailure(request, name, e, callBack)
//            }
//
//            @Throws(IOException::class)
//            override fun onResponse(call: Call, response: Response) {
//
//                /*
//                 * 在这里进行文件的下载处理
//                 */
//                var inputStream: InputStream? = null
//                var fileOutputStream: FileOutputStream? = null
//                try {
//                    //文件名和目标地址
//                    val file = File(desDir, getFileName(url))
//                    //把请求回来的response对象装换为字节流
//                    inputStream = response.body()!!.byteStream()
//                    fileOutputStream = FileOutputStream(file)
//                    var len: Int
//                    val bytes = ByteArray(2048)
//                    //循环读取数据
//                    while ((len = inputStream!!.read(bytes)) != -1) fileOutputStream.write(bytes, 0, len)
//                    //关闭文件输出流
//                    fileOutputStream.flush()
//                    //调用分发数据成功的方法
//                    deliverDataSuccess(file.absolutePath, name, callBack)
//                } catch (e: IOException) {
//                    //如果失败，调用此方法
//                    deliverDataFailure(request, name, e, callBack)
//                    e.printStackTrace()
//                } finally {
//                    if (inputStream != null) {
//                        inputStream.close()
//                    }
//                    if (fileOutputStream != null) {
//                        fileOutputStream.close()
//                    }
//
//                }
//            }
//
//        })
//    }

    /*
     * 根据文件url获取文件的路径名字
     *
     * @param url
     * @return
     */
    private fun getFileName(url: String): String {
        val separatorIndex = url.lastIndexOf("/")
        return if (separatorIndex < 0) url else url.substring(separatorIndex + 1, url.length)
    }

    private fun inner_postAsync(context: Context, url: String, name: String, is_face: String, name1: File, callBack: DataCallBack) {
        /* 第一个要上传的file */
        val fileBody1 = RequestBody.create(MediaType.parse("application/octet-stream"), name1)

        val file1Name = "avatar.png"
        val mBody = MultipartBody.Builder(file1Name).setType(MultipartBody.FORM)
                /* 上传一个普通的String参数 , key 叫 "p" */
                .addFormDataPart("file", name1.toString(), fileBody1)
                .addFormDataPart("directory", is_face)
                .build()
        //结果返回
        val request = Request.Builder().url(url).post(mBody).addHeader("Authorization", "Bearer ${SPUtils.getInstance(context, "Authorization").getString("Authorization", "")}").build()
        mClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                deliverDataFailure(request, name, e, callBack)
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                val result = response.body()!!.string()
                deliverDataSuccess(result, name, callBack)
            }
        })
    }

    private fun inner_postAsync(url: String, name: String, uid: String, problem: String, mobile: String, email: String, name1: File?,
                                callBack: DataCallBack) {
        val mBody: MultipartBody
        if (name1 != null) {
            /* 第一个要上传的file */
            val fileBody1 = RequestBody.create(MediaType.parse("application/octet-stream"), name1)
            val file1Name = "jsy.png"
            mBody = MultipartBody.Builder(file1Name).setType(MultipartBody.FORM)
                    /* 上传一个普通的String参数 , key 叫 "p" */
                    .addFormDataPart("user_id", uid)
                    /* 底下是上传了两个文件 */
                    .addFormDataPart("photo", name1.toString(), fileBody1)
                    .addFormDataPart("problem", problem)
                    .addFormDataPart("mobile", mobile)
                    .addFormDataPart("email", email)
                    .build()
        } else {
            val file1Name = "jsy.png"
            mBody = MultipartBody.Builder(file1Name).setType(MultipartBody.FORM)
                    /* 上传一个普通的String参数 , key 叫 "p" */
                    .addFormDataPart("user_id", uid)
                    /* 底下是上传了两个文件 */
                    .addFormDataPart("problem", problem)
                    .addFormDataPart("mobile", mobile)
                    .addFormDataPart("email", email)
                    .build()
        }
        //结果返回
        val request = Request.Builder().url(url).post(mBody).build()
        mClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                deliverDataFailure(request, name, e, callBack)
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                val result = response.body()!!.string()
                deliverDataSuccess(result, name, callBack)
            }
        })
    }

    private fun inner_postAsync(url: String, name: String, idcard_name: String, idcard_number: String, delta: String,
                                file: String, callBack: DataCallBack) {
        val file1Name = "jyh.png"
        val fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file)
        val mBody = MultipartBody.Builder(file1Name).setType(MultipartBody.FORM)
                .addFormDataPart("idcard_name", idcard_name)
                .addFormDataPart("idcard_number", idcard_number)
                .addFormDataPart("delta", delta)
                .addFormDataPart("image_best", file)
                //                .addFormDataPart( "image_best", file.toString(), fileBody )
                .build()

        //结果返回
        val request = Request.Builder().url(url).post(mBody).build()
        mClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                deliverDataFailure(request, name, e, callBack)
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                val result = response.body()!!.string()
                deliverDataSuccess(result, name, callBack)
            }
        })
    }

    companion object {

        /**
         * 静态实例
         */
        private var sOkHttpManager: OkHttpManager? = null


        /*
     * 单例模式  获取OkHttpManager实例
     *
     * @return
     */
        val instance: OkHttpManager
            get() {

                if (sOkHttpManager == null) {
                    sOkHttpManager = OkHttpManager()
                }
                return sOkHttpManager as OkHttpManager
            }

        //-------------------------同步的方式请求数据--------------------------

        /*
     * 对外提供的get方法,同步的方式
     *
     * @param url 传入的地址
     * @return
     */
        fun getSync(url: String): Response? {

            //通过获取到的实例来调用内部方法
            return sOkHttpManager!!.inner_getSync(url)
        }

        /*
     * 对外提供的同步获取String的方法
     *
     * @param url
     * @return
     */
        fun getSyncString(url: String): String? {
            return sOkHttpManager!!.inner_getSyncString(url)
        }

        //-------------------------异步的方式请求数据--------------------------
        fun getAsync(context: Context, url: String, name: String, callBack: DataCallBack) {
            instance.inner_getAsync(context, url, name, callBack)
        }

        fun getAsyncAuthorization(context: Context, url: String, name: String, callBack: DataCallBack) {
            instance.inner_getAsyncAuthorization(context, url, name, callBack)
        }


        //-------------------------提交表单--------------------------

        fun postAsync(context: Context, url: String, name: String, params: Map<String, Any>, callBack: DataCallBack) {
            instance.inner_postAsync(context, url, name, params, callBack)
        }

        //-------------------------文件下载--------------------------
//        fun downloadAsync(url: String, name: String, desDir: String, callBack: DataCallBack) {
//            instance.inner_downloadAsync(url, name, desDir, callBack)
//        }

        fun uploadAsync(context: Context, url: String, name: String, is_face: String, name1: File, callBack: DataCallBack) {
            instance.inner_postAsync(context, url, name, is_face, name1, callBack)
        }

        fun feedbackAsync(url: String, name: String, uid: String, problem: String, mobile: String, email: String, name1: File,
                          callBack: DataCallBack) {
            instance.inner_postAsync(url, name, uid, problem, mobile, email, name1, callBack)
        }


        fun postImageVerify(url: String, name: String, idcard_name: String, idcard_number: String, delta: String,
                            bitmap: Bitmap, callBack: DataCallBack) {
            val baos = ByteArrayOutputStream()// outputstream
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
            val appicon = baos.toByteArray()// 转为byte数组
            instance.inner_postAsync(url, name, idcard_name, idcard_number, delta, Base64.encodeToString(appicon, Base64.DEFAULT), callBack)
        }
    }

}

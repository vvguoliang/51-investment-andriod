@file:Suppress("DEPRECATED_IDENTITY_EQUALS")

package sp51.spotpass.com.spotpass.ui.activity

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.annotation.RequiresApi
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.umeng.analytics.MobclickAgent
import kotlinx.android.synthetic.main.act_account_management.*
import kotlinx.android.synthetic.main.view_title_textview.*
import sp51.spotpass.com.spotpass.MainActivity
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.base.BaseActivity
import sp51.spotpass.com.spotpass.base.BaseActivityManager
import sp51.spotpass.com.spotpass.statusBar.ImmersionBar
import sp51.spotpass.com.spotpass.ui.baseEntity.Img_Uplod
import sp51.spotpass.com.spotpass.ui.baseEntity.QryOrderH
import sp51.spotpass.com.spotpass.ui.http.HttpALl
import sp51.spotpass.com.spotpass.ui.http.HttpImplements
import sp51.spotpass.com.spotpass.ui.utils.AppUtil
import sp51.spotpass.com.spotpass.ui.utils.Camera.BitmapUtils
import sp51.spotpass.com.spotpass.ui.utils.Camera.UserCenterRealize
import sp51.spotpass.com.spotpass.ui.utils.SPUtils
import sp51.spotpass.com.spotpass.ui.utils.ToatUtils
import sp51.spotpass.com.spotpass.ui.view.Dialog.BottomDialog
import sp51.spotpass.com.spotpass.ui.view.Dialog.NickNameDialogBuilder
import java.io.File

@SuppressLint("Registered")
/**
 * @Time : 2018/5/20 no 18:06
 * @USER : vvguoliang
 * @File : AccountManagementActivity.java
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
class AccountManagementActivity : BaseActivity(), View.OnClickListener {

    private var userCenterRealize: UserCenterRealize? = null

    private lateinit var bottomDialog: BottomDialog

    private var nickname: String = ""

    override fun initParams(arguments: Bundle?) {
    }

    override fun bindLayout(): Int {
        return R.layout.act_account_management
    }

    override fun initView(rootView: View) {
    }

    override fun setListener() {
        userCenterRealize = UserCenterRealize()
        name = getString(R.string.textView_accout_management)
        title_right_arrow_white.visibility = View.VISIBLE
        title_right_arrow_white.setOnClickListener(this)
        title_textview.visibility = View.VISIBLE
        title_textview.text = name

        nickname = SPUtils.getInstance(this, "login").getString("name", "")
        account_nickname_text.text = nickname
        account_current_account.text = SPUtils.getInstance(this, "login").getString("phone", "")

        if (!TextUtils.isEmpty(SPUtils.getInstance(this, "img").getString("img", ""))) {
            val options = RequestOptions()
            options.centerCrop().placeholder(R.mipmap.ic_gethead).error(R.mipmap.ic_gethead).fallback(R.mipmap.ic_gethead);
            Glide.with(this).load(SPUtils.getInstance(this, "img").getString("img", "")).apply(options).into(account_head_portrait_imag)
        }

        account_head_portrait.setOnClickListener(this)
        account_nickname.setOnClickListener(this)
        account_modify_password.setOnClickListener(this)

    }

    override fun doBusiness() {
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.title_right_arrow_white -> finish()
            R.id.account_head_portrait -> {
                getBottomDialog()
            }
            R.id.account_modify_password -> {
                val intent = Intent(this, ModifyPasswordActivity::class.java)
                intent.putExtra("type", "2")
                startActivity(intent)
            }
            R.id.my_pop_cancel_button1 -> {
                userCenterRealize!!.getFileByPhotograph(this)
            }
            R.id.my_pop_cancel_button2 -> {
                userCenterRealize!!.getFileByPhotoAlbum(this)
            }
            R.id.my_pop_cancel_button -> {
                bottomDialog.dismiss()
            }
            R.id.account_nickname -> {
                NickNameDialogBuilder(this)
                        .cancelText("取消")
                        .sureText("确定")
                        .messageeditext_text(true)
                        .setCancelOnClickListener { }
                        .setSureOnClickListener {
                            if (!TextUtils.isEmpty(nickname))
                                HttpALl.get().seteditNickName(nickname, mHandler, this)
                            else
                                ToatUtils.showShort1(this, "请输入您的昵称")
                        }
                        .setTextWatcherr(object : TextWatcher {
                            override fun afterTextChanged(s: Editable?) {
                                nickname = s.toString()
                            }

                            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                            }

                            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                            }
                        })
                        .build().show()
            }

        }
    }

    private lateinit var name: String

    public override fun onResume() {
        super.onResume()
        MobclickAgent.onPageStart(name); //手动统计页面("SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(this)
    }

    public override fun onPause() {
        super.onPause()
        MobclickAgent.onPageEnd(name); //手动统计页面("SplashScreen"为页面名称，可自定义)，必须保证 onPageEnd 在 onPause 之前调用，因为SDK会在 onPause 中保存onPageEnd统计到的页面数据。
        MobclickAgent.onPause(this)
    }

    /**
     * 沉浸式
     */
    override fun initImmersionBar() {
        super.initImmersionBar()
        mImmersionBar?.statusBarView(R.id.top_view)?.init()
        ImmersionBar.with(this@AccountManagementActivity)
                .statusBarDarkFont(false, 0.1f)
                .init()
    }

    private fun getBottomDialog() {
        bottomDialog = BottomDialog(this@AccountManagementActivity, R.layout.my_path_botton_popupwindow)
        bottomDialog.window!!.setWindowAnimations(R.style.AnimBottom)
        bottomDialog.window!!.setGravity(Gravity.BOTTOM)
        bottomDialog.setWidthHeight(AppUtil.getScreenDispaly(this)[0], 0)
        bottomDialog.setOnClick(R.id.my_pop_cancel_button1, this)
        bottomDialog.setOnClick(R.id.my_pop_cancel_button2, this)
        bottomDialog.setOnClick(R.id.my_pop_cancel_button, this)
        if (!this@AccountManagementActivity.isFinishing) {
            bottomDialog.show()
        }
    }


    @SuppressLint("SetTextI18n")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (AppUtil.instance.CAPTURE_IMAGE_REQUEST === requestCode) {
            if (Activity.RESULT_OK == resultCode) {
                Log.d("拍照得到图片", AppUtil.instance.mImageFile.toString())
                val mDegree = BitmapUtils.getBitmapDegree(AppUtil.instance.mImageFile!!.absolutePath)
                Log.d("拍照得到图片的角度：", mDegree.toString())
                if (mDegree == 90 || mDegree == 180 || mDegree == 270) {
                    try {
                        val mBitmap = BitmapUtils.getFileBitmap(AppUtil.instance.mImageFile!!)
                        val bitmap = BitmapUtils.rotateBitmapByDegree(mBitmap!!, mDegree)
                        if (BitmapUtils.saveBitmapFile(bitmap, AppUtil.instance.mImageFile!!)) {
                            userCenterRealize!!.startClip(activity = this, file = AppUtil.instance.mImageFile!!)
                        } else {
                            Toast.makeText(this, "保存图片失败", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        Toast.makeText(this, "读取图片失败", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    userCenterRealize!!.startClip(activity = this, file = AppUtil.instance.mImageFile!!)
                }
            }
            //相册
        } else if (AppUtil.instance.LOAD_IMAGE_REQUEST === requestCode) {
            if (data != null) {
                val uri = data.data
                val filepath = BitmapUtils.FileUtils.getImageAbsolutePath(this, uri)
                Log.d("相册获取到的文件路径", filepath)
                val file = File(filepath)
                userCenterRealize!!.startClip(activity = this, file = file)
            }
            //剪裁
        } else if (AppUtil.instance.CLIP_IMAGE_REQUEST === requestCode) {
            Log.d("剪裁得到图片", AppUtil.instance.mOutFile.toString())
            val bitmap = BitmapUtils.getFileBitmap(AppUtil.instance.mOutFile!!)
            account_head_portrait_imag.setImageBitmap(bitmap)
            HttpALl.get().setimg_upload(AppUtil.instance.mOutFile.toString().replace(".", ""), AppUtil.instance.mOutFile!!, mHandler, this)
            BitmapUtils.deleteFile(AppUtil.instance.mImageFile)
        }
    }

    @SuppressLint("HandlerLeak")
    private val mHandler = object : Handler() {

        @SuppressLint("SetTextI18n")
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            when (msg.what) {
                1048 -> {
                    val img_Uplod = msg.obj as Img_Uplod
                    SPUtils.getInstance(this@AccountManagementActivity, "img").put("img", "${HttpImplements.get().HttpS}${img_Uplod.data.fileUrl.substring(1, img_Uplod.data.fileUrl.length)}")
                    getEditAvatar(img_Uplod.data.fileUrl)
                }
                1049 -> ToatUtils.showShort1(this@AccountManagementActivity, "上传成功")
                1050 -> {
                    account_nickname_text.text = nickname
                    ToatUtils.showShort1(this@AccountManagementActivity, "成功")
                }
            }
        }
    }

    private fun getEditAvatar(fileUrl: String) {
        HttpALl.get().seteditAvatar(fileUrl, mHandler, this@AccountManagementActivity)
    }


    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == AppUtil.instance.MY_PERMISSIONS_REQUEST_CAMERA) {
            if (!TextUtils.isEmpty("${grantResults[0]}") && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                userCenterRealize!!.getFileByPhotograph(this)
            } else {
                Toast.makeText(this, "请授予相机权限", Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == AppUtil.instance.MY_PERMISSIONS_REQUEST_READ_SD) {
            if (!TextUtils.isEmpty("${grantResults[0]}") && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                userCenterRealize!!.getFileByPhotograph(this)
            } else {
                Toast.makeText(this, "请授予读SD卡权限", Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == AppUtil.instance.MY_PERMISSIONS_REQUEST_WRITE_SK) {
            if (!TextUtils.isEmpty("${grantResults[0]}") && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                userCenterRealize!!.getFileByPhotograph(this)
            } else {
                Toast.makeText(this, "请授予写SD卡权限", Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == AppUtil.instance.MY_PERMISSIONS_REQUEST_READ_SD_PHOTOALBUM) {
            if (!TextUtils.isEmpty("${grantResults[0]}") && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                userCenterRealize!!.startPhotoAlbum(this)
            } else {
                Toast.makeText(this, "请授予读SD卡权限", Toast.LENGTH_SHORT).show()
            }
        } else if (requestCode == AppUtil.instance.MY_PERMISSIONS_REQUEST_WRITE_SK_PHOTOALBUM) {
            if (!TextUtils.isEmpty("${grantResults[0]}") && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                userCenterRealize!!.startPhotoAlbum(this)
            } else {
                Toast.makeText(this, "请授予写SD卡权限", Toast.LENGTH_SHORT).show()
            }
        }
    }


}
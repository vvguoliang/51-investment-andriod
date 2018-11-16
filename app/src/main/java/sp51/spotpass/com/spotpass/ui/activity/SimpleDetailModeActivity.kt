//package sp51.spotpass.com.spotpass.ui.activity
//
//import android.annotation.SuppressLint
//import android.content.res.Configuration
//import android.os.Bundle
//import android.view.View
//import android.widget.ImageView
//import com.bumptech.glide.Glide
//import com.bumptech.glide.request.RequestOptions
//import com.shuyu.gsyvideoplayer.GSYVideoManager
//import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder
//import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack
//import com.shuyu.gsyvideoplayer.listener.LockClickListener
//import com.shuyu.gsyvideoplayer.utils.OrientationUtils
//import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer
//import kotlinx.android.synthetic.main.act_account_management.*
//import sp51.spotpass.com.spotpass.R
//import sp51.spotpass.com.spotpass.base.BaseActivity
//import sp51.spotpass.com.spotpass.ui.utils.SPUtils
//
//@SuppressLint("Registered")
///**
// * @Time : 2018/6/22 no 下午3:24
// * @USER : vvguoliang
// * @File : SimpleDetailMActivity.java
// * @Software: Android Studio
// *code is far away from bugs with the god animal protecting
// *   I love animals. They taste delicious.
// * ***┏┓   ┏ ┓
// * **┏┛┻━━━┛ ┻┓
// * **┃   ☃   ┃
// * **┃ ┳┛  ┗┳ ┃
// * **┃    ┻   ┃
// * **┗━┓    ┏━┛
// * ****┃    ┗━━━┓
// * ****┃ 神兽保佑 ┣┓
// * ****┃ 永无BUG！┏┛
// * ****┗┓┓┏━┳┓┏┛┏┛
// * ******┃┫┫  ┃┫┫
// * ******┗┻┛  ┗┻┛
// */
//class SimpleDetailModeActivity : BaseActivity() {
//
//    private var orientationUtils: OrientationUtils? = null
//
//    private lateinit var detail_player: StandardGSYVideoPlayer
//
//    private var isPlay: Boolean = false
//    private var isPause: Boolean = false
//
//    private var url: String = ""
//
//    private var coverUrl: String = ""
//
//    override fun initParams(arguments: Bundle?) {
//    }
//
//    override fun bindLayout(): Int {
//        return R.layout.act_simple_detail_mode
//    }
//
//    override fun initView(rootView: View) {
//    }
//
//    override fun setListener() {
//        url = intent.getStringExtra("videoUrl")
//        coverUrl = intent.getStringExtra("coverUrl")
//        detail_player = this.findViewById(R.id.detail_player)
//        //增加封面
//        val imageView = ImageView(this)
//        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
//        val options = RequestOptions()
//        options.centerCrop().placeholder(R.mipmap.ic_banner).error(R.mipmap.ic_banner).fallback(R.mipmap.ic_banner);
//        Glide.with(this).load(coverUrl).apply(options).into(imageView)
//
//
//        //外部辅助的旋转，帮助全屏
//        orientationUtils = OrientationUtils(this, detail_player)
//        //初始化不打开外部的旋转
//        orientationUtils!!.isEnable = false
//
//
//        val gsyVideoOption = GSYVideoOptionBuilder()
//        gsyVideoOption.setThumbImageView(imageView)
//                .setIsTouchWiget(true)
//                .setRotateViewAuto(false)
//                .setLockLand(false)
//                .setAutoFullWithSize(true)
//                .setShowFullAnimation(false)
//                .setNeedLockFull(true)
//                .setUrl(url)
//                .setCacheWithPlay(false)
//                .setVideoTitle("测试视频")
//                .setVideoAllCallBack(object : GSYSampleCallBack() {
//                    override fun onPrepared(url: String?, vararg objects: Any) {
//                        super.onPrepared(url, *objects)
//                        //开始播放了才能旋转和全屏
//                        orientationUtils!!.isEnable = true
//                        isPlay = true
//                    }
//
//                    override fun onQuitFullscreen(url: String?, vararg objects: Any) {
//                        super.onQuitFullscreen(url, *objects)
//                        if (orientationUtils != null) {
//                            orientationUtils!!.backToProtVideo()
//                        }
//                    }
//                }).setLockClickListener { view, lock ->
//                    if (orientationUtils != null) {
//                        //配合下方的onConfigurationChanged
//                        orientationUtils!!.isEnable = !lock
//                    }
//                }.build(detail_player)
//
//        detail_player.fullscreenButton.setOnClickListener(View.OnClickListener {
//            //直接横屏
//            orientationUtils!!.resolveByClick()
//            //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
//            detail_player.startWindowFullscreen(this@SimpleDetailModeActivity, true, true)
//        })
//    }
//
//    override fun doBusiness() {
//    }
//
//
//    override fun onBackPressed() {
//        if (orientationUtils != null) {
//            orientationUtils!!.backToProtVideo()
//        }
//        if (GSYVideoManager.backFromWindowFull(this)) {
//            return
//        }
//        super.onBackPressed()
//    }
//
//
//    override fun onPause() {
//        detail_player.currentPlayer.onVideoPause()
//        super.onPause()
//        isPause = true
//    }
//
//    override fun onResume() {
//        detail_player.currentPlayer.onVideoResume(false)
//        super.onResume()
//        isPause = false
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        if (isPlay) {
//            detail_player.currentPlayer.release()
//        }
//        if (orientationUtils != null)
//            orientationUtils!!.releaseListener()
//    }
//
//
//    override fun onConfigurationChanged(newConfig: Configuration) {
//        super.onConfigurationChanged(newConfig)
//        //如果旋转了就全屏
//        if (isPlay && !isPause) {
//            detail_player.onConfigurationChanged(this, newConfig, orientationUtils, true, true)
//        }
//    }
//
//}
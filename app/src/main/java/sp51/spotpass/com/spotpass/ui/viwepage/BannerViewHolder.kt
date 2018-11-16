package sp51.spotpass.com.spotpass.ui.viwepage

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import sp51.spotpass.com.spotpass.R
import sp51.spotpass.com.spotpass.ui.viwepage.holder.MZViewHolder

/**
 * @Time : 2018/5/4 no 11:59
 * @USER : vvguoliang
 * @File : BannerViewHolder1.java
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
class BannerViewHolder : MZViewHolder<String> {

    override fun onBind(context: Context, position: Int, data: String) {
        mImageView!!.scaleType = ImageView.ScaleType.FIT_XY
        val options = RequestOptions()
        options.centerCrop().placeholder(R.mipmap.ic_banner).error(R.mipmap.ic_banner).fallback(R.mipmap.ic_banner);
        Glide.with(context).load(data).apply(options).into(mImageView!!)
    }

    private var mImageView: ImageView? = null

    @SuppressLint("InflateParams")
    override fun createView(context: Context): View {
        // 返回页面布局文件
        val view = LayoutInflater.from(context).inflate(R.layout.banner_item, null)
        mImageView = view.findViewById(R.id.banner_image)
        return view
    }
}
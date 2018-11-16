package sp51.spotpass.com.spotpass.ui.ftagment;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import sp51.spotpass.com.spotpass.R;
import sp51.spotpass.com.spotpass.base.BaseFragment;
import sp51.spotpass.com.spotpass.playtablayout.PlayTabLayout;
import sp51.spotpass.com.spotpass.playtablayout.TouchableTabLayout;
import sp51.spotpass.com.spotpass.ui.adapter.DocumentTabAdapter;
import sp51.spotpass.com.spotpass.ui.adapter.SimpleAdapter;
import sp51.spotpass.com.spotpass.ui.recyclerview.XRefreshView;
import sp51.spotpass.com.spotpass.ui.recyclerview.XRefreshViewFooter;
import sp51.spotpass.com.spotpass.ui.viwepage.BannerViewHolder;
import sp51.spotpass.com.spotpass.ui.viwepage.MZBannerView;

import static sp51.spotpass.com.spotpass.R.color.white;

/**
 * @Time : 2018/5/14 no 14:45
 * @USER : vvguoliang
 * @File : MainDocumentary.java
 * @Software: Android Studio
 * code is far away from bugs with the god animal protecting
 * I love animals. They taste delicious.
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
 * 跟单
 */
public class MainDocumentary extends BaseFragment implements View.OnClickListener, TouchableTabLayout.OnTabSelectedListener {

    private PlayTabLayout transaction_PlayTab;

    private ViewPager transaction_viewpage;

    private DocumentTabAdapter documentTabAdapter;

    private TouchableTabLayout tabLayout;

    private MZBannerView banner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.far_documentary, container, false);
        TextView title_textview = view.findViewById(R.id.title_textview);
        title_textview.setVisibility(View.VISIBLE);
        title_textview.setText(getString(R.string.navigation_documentary));
        TextView title_integral_rule = view.findViewById(R.id.title_integral_rule);
        title_integral_rule.setVisibility(View.VISIBLE);
        title_integral_rule.setText(getString(R.string.textView_rule));
        title_integral_rule.setOnClickListener(this);

        transaction_PlayTab = view.findViewById(R.id.transaction_PlayTab);
        transaction_viewpage = view.findViewById(R.id.transaction_viewpage);

        banner = view.findViewById(R.id.banner);
        return view;
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        List<String> list1 = new ArrayList<>();
        list1.add("http://51.loc//uploads//images//694e195695611fc26340481abd276060.png");
        getBranner(list1);

        int[] colosint = {white, white, white, white};
        transaction_PlayTab.setColors(colosint);

        documentTabAdapter = new DocumentTabAdapter(getChildFragmentManager());

        ArrayList<Fragment> list = new ArrayList<>();
        list.add(new DocumentaryFragment());
        list.add(new DocumentaryFragment1());
        list.add(new DocumentaryFragment2());
        list.add(new DocumentaryFragment3());

        documentTabAdapter.setLazy(list);
        transaction_viewpage.setAdapter(documentTabAdapter);

        tabLayout = transaction_PlayTab.getTabLayout();
        tabLayout.setupWithViewPager(transaction_viewpage);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setSelectedTabIndicatorHeight(7);
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FF6D64"));
        tabLayout.setTabTextColors(R.color.black_grey, Color.parseColor("#FF6D64"));
        tabLayout.addOnTabSelectedListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_integral_rule:
                break;
        }
    }

    /**
     * 轮播图
     *
     * @param bannerList
     */
    private void getBranner(List<String> bannerList) {
        banner.setBannerPageClickListener((view, position) -> {
//                if (TextUtils.isEmpty(bannerList.get(position).getUrl())) {
//                    Toast.makeText(getActivity(), getString(R.string.coming_soon), Toast.LENGTH_LONG).show();
//                } else {
//                    Intent intent = new Intent(getActivity(), WebVIewActivity.class);
//                    intent.putExtra("url", bannerList.get(position).getUrl());
//                    getActivity().startActivity(intent);
//                }
        });
        banner.setIndicatorVisible(true);
        // 代码中更改indicator 的位置
        //mMZBanner.setIndicatorAlign(MZBannerView.IndicatorAlign.LEFT);
        //mMZBanner.setIndicatorPadding(10,0,0,150);
        banner.setPages(bannerList, BannerViewHolder::new);
    }


    @Override
    public void onTabSelected(@NotNull TouchableTabLayout.Tab tab) {
//        tab.getIcon().setColorFilter(Color.parseColor("#999999"), PorterDuff.Mode.SRC_IN);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onTabUnselected(@NotNull TouchableTabLayout.Tab tab) {
//        tab.getIcon().setColorFilter(R.color.white, PorterDuff.Mode.SRC_IN);
    }

    @Override
    public void onTabReselected(@NotNull TouchableTabLayout.Tab tab) {

    }

    @Override
    public void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("MainDocumentary"); //统计页面("MainScreen"为页面名称，可自定义)
    }


    @Override
    public void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("MainDocumentary");
    }
}

package sp51.spotpass.com.spotpass.ui.ftagment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.umeng.analytics.MobclickAgent;
import com.yanzhenjie.permission.AndPermission;

import sp51.spotpass.com.spotpass.ui.activity.OnlineServiceActivity;
import sp51.spotpass.com.spotpass.ui.activity.RegisterActivity;
import sp51.spotpass.com.spotpass.ui.activity.SchoolActivity;
import sp51.spotpass.com.spotpass.ui.activity.WebViewActivity;
import sp51.spotpass.com.spotpass.ui.adapter.HomeHonerViewSimpleAdapter;
import sp51.spotpass.com.spotpass.ui.baseEntity.AppVersion;
import sp51.spotpass.com.spotpass.ui.baseEntity.GetTopcaRousels;
import sp51.spotpass.com.spotpass.ui.baseEntity.ProfitNews;
import sp51.spotpass.com.spotpass.ui.baseEntity.QryKprice;
import sp51.spotpass.com.spotpass.ui.baseEntity.QryStockNoName;
import sp51.spotpass.com.spotpass.ui.baseEntity.Recommend;
import sp51.spotpass.com.spotpass.ui.baseEntity.TindexShow;
import sp51.spotpass.com.spotpass.ui.http.HttpALl;
import sp51.spotpass.com.spotpass.ui.service.LastPriceService;
import sp51.spotpass.com.spotpass.ui.utils.AppUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import sp51.spotpass.com.spotpass.R;
import sp51.spotpass.com.spotpass.base.BaseFragment;
import sp51.spotpass.com.spotpass.ui.activity.IntegralMallActivity;
import sp51.spotpass.com.spotpass.ui.activity.ProfitListActivity;
import sp51.spotpass.com.spotpass.ui.adapter.SimpleAdapter;
import sp51.spotpass.com.spotpass.ui.kchartlib.chatr.ExampleActivity;
import sp51.spotpass.com.spotpass.ui.recyclerview.XRefreshView;
import sp51.spotpass.com.spotpass.ui.utils.SPUtils;
import sp51.spotpass.com.spotpass.ui.utils.Util;
import sp51.spotpass.com.spotpass.ui.view.Dialog.EnvelopesDialogBuilder;
import sp51.spotpass.com.spotpass.ui.view.Dialog.Vison.VisionShowDialog;
import sp51.spotpass.com.spotpass.ui.view.Marquee;
import sp51.spotpass.com.spotpass.ui.view.MarqueeView;
import sp51.spotpass.com.spotpass.ui.viwepage.BannerViewHolder;
import sp51.spotpass.com.spotpass.ui.viwepage.MZBannerView;

/**
 * @Time : 2018/5/4 no 14:35
 * @USER : vvguoliang
 * @File : MainHome.java
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
 */
public class MainHome extends BaseFragment implements OnClickListener {

    private XRefreshView custom_view;

    private XRefreshView custom_view1;

    private RecyclerView recycler_view_test_rv;

    private RecyclerView recycler_view_test_rv1;

    private SimpleAdapter adapter;

    private HomeHonerViewSimpleAdapter honerViewSimpleAdapter;

    private MZBannerView banner;

    private ImageView title_imageview_customer_service;

    private MarqueeView marqueeView;

    private Marquee marquee;

    private List<Marquee> marquees;

    private int mLoadCount = 0;

    private List<Map<String, Object>> list = null;

    private List<Map<String, Object>> list1 = null;

    private boolean serviceboolean = false;

    private boolean xrboolean = false;

    private RelativeLayout title_relative;

    private TextView title_textview;

    private LastPriceService lastPriceService = new LastPriceService();

    @Override
    public void onAttach(@org.jetbrains.annotations.Nullable Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.far_home, container, false);
        custom_view = view.findViewById(R.id.custom_view);
        recycler_view_test_rv = view.findViewById(R.id.recycler_view_test_rv);
        title_imageview_customer_service = view.findViewById(R.id.title_imageview_customer_service);
        title_relative = view.findViewById(R.id.title_relative);
        title_relative.setVisibility(View.GONE);
        title_textview = view.findViewById(R.id.title_textview);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        marquees = new ArrayList<>();
//        for (int i = 0; 10 > i; i++) {
//            marquee = new Marquee();
//            marquee.setContext("===那点对方的====" + i);
//            marquee.setImgUrl("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1525428815775&di=0c011c0d2dfd5d89210018ce7dabc9d5&imgtype=0&src=http%3A%2F%2Fww2.sinaimg.cn%2Flarge%2F79a17fdbjw1e9274zrr7yj215o0u0nac.jpg");
//            marquee.setTitle("andkahdadadk==" + i);
//            marquees.add(marquee);
//        }

        custom_view.setPullLoadEnable(true);
        recycler_view_test_rv.setHasFixedSize(true);
        adapter = new SimpleAdapter(getActivity());
        getRecycler_view_test_rv();

        title_textview.setVisibility(View.VISIBLE);
        title_textview.setText(getString(R.string.app_name));

        title_imageview_customer_service.setVisibility(View.VISIBLE);
        title_imageview_customer_service.setOnClickListener(this);

        recycler_view_test_rv.setLayoutManager(new LinearLayoutManager(getActivity()));

        list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("name", "清风老师");
        map.put("context", "【数据提示】美国5月零售销售月率、美国五月纽约联储制造业指数低于预期。利空美元。");
        map.put("time", AppUtil.Companion.getInstance().getTime());
        map.put("role_name", AppUtil.Companion.getInstance().getTime());
        map.put("avatar", "");
        list.add(map);
        map = new HashMap<>();
        map.put("name", "李刚老师");
        map.put("context", "【欧洲红酒】交易提示：作为欧盟第一大经济强国的德国刚刚公布了“德国第一季度末季调GDP年率初值”，数据表现不佳，单是欧酒下行趋势不明显。目前建议大家依然关注美元走势，美元上涨动能依旧存在，建议投资者不要轻易抄底。个人观点仅供参考。");
        map.put("time", AppUtil.Companion.getInstance().getTime());
        map.put("role_name", AppUtil.Companion.getInstance().getTime());
        map.put("avatar", "");
        list.add(map);
        map = new HashMap<>();
        map.put("name", "清风老师");
        map.put("context", "【数据提示】路透快评美国4月零售销售月率：但是美国消费者支出似乎仍处在正轨之上，一季度数据表现曾出现大幅放缓。");
        map.put("time", AppUtil.Companion.getInstance().getTime());
        map.put("role_name", AppUtil.Companion.getInstance().getTime());
        map.put("avatar", "");
        list.add(map);
        map = new HashMap<>();
        map.put("name", "清风老师");
        map.put("context", "【行情提示】美元指数DXY上破93关口，为3个交易日首次，日内涨0.37%。");
        map.put("time", AppUtil.Companion.getInstance().getTime());
        map.put("role_name", AppUtil.Companion.getInstance().getTime());
        map.put("avatar", "");
        list.add(map);

        View headerView = adapter.setHeaderView(R.layout.home_banner_content, recycler_view_test_rv);

        custom_view1 = headerView.findViewById(R.id.custom_view);
        recycler_view_test_rv1 = headerView.findViewById(R.id.recycler_view_test_rv);

        custom_view1.setPullLoadEnable(true);
        recycler_view_test_rv1.setHasFixedSize(true);
        honerViewSimpleAdapter = new HomeHonerViewSimpleAdapter(getActivity());
        getRecycler_view_test_rv1();

        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler_view_test_rv1.setLayoutManager(linearLayoutManager);
        recycler_view_test_rv1.setAdapter(honerViewSimpleAdapter);
        // -----------------------------------------------------------------------------------------

        marqueeView = headerView.findViewById(R.id.marqueeView);
//        marqueeView.setOnItemClickListener((position, textView) -> startActivity(new Intent(getActivity(), DetailsWarehouseActivity.class)));
//        marqueeView.setImage(true);
//        marqueeView.startWithList(marquees);

        headerView.findViewById(R.id.profit_list_liner).setOnClickListener(this);
        headerView.findViewById(R.id.integral_mall_liner).setOnClickListener(this);
        headerView.findViewById(R.id.grab_a_red_envelope_liner).setOnClickListener(this);

        banner = headerView.findViewById(R.id.banner);

        adapter.setData(list);
        recycler_view_test_rv.setAdapter(adapter);

//        adapter.setOnItemClickListener((view1, data) -> {
//            Map<String, Object> map1 = list.get(data - 1);
//            Intent intent = new Intent(getActivity(), MainInteractionDetailsActivity.class);
//            intent.putExtra("look", "0");
//            startActivity(intent);
//        });
        //设置其滑动事件
        recycler_view_test_rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    title_relative.setVisibility(View.VISIBLE);
                } else {
                    title_relative.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        HttpALl.Companion.get().setRecommend(mHandler, Objects.requireNonNull(getActivity()));
        visionShowDialog = new VisionShowDialog(getActivity());
        HttpALl.Companion.get().setappVersion(mHandler, Objects.requireNonNull(getActivity()));
        banner.start();
        serviceboolean = true;
        MobclickAgent.onPageStart("MainHome"); //统计页面("MainScreen"为页面名称，可自定义)
    }

    private void getHTTp() {
        if (TextUtils.isEmpty(SPUtils.Companion.getInstance(getActivity(), "login").getString("login", ""))) {
            if (TextUtils.isEmpty(SPUtils.Companion.getInstance(getActivity(), "envelopes").getString("envelopes", ""))) {
                new EnvelopesDialogBuilder(getActivity()).setCancelOnClickListener(v -> {

                }).setSureOnClickListener(v -> {
                    Intent intent = new Intent(getActivity(), RegisterActivity.class);
                    intent.putExtra("type", 1);
                    startActivityForResult(intent, 209);
                }).build().show();
            }
        }
        HttpALl.Companion.get().setGETINDEXSHOW(mHandler, getActivity());
        HttpALl.Companion.get().setGETTOPCAROUSELS(mHandler, getActivity());
        HttpALl.Companion.get().setprofitNews(mHandler, getActivity());
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.pause();
        if (AppUtil.Companion.getInstance().isServiceRunning(getActivity(), "sp51.spotpass.com.spotpass.ui.service.LastPriceService")) {
            lastPriceService.stop(getActivity());
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        serviceboolean = false;
        MobclickAgent.onPageEnd("MainHome");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (AppUtil.Companion.getInstance().isServiceRunning(getActivity(), "sp51.spotpass.com.spotpass.ui.service.LastPriceService")) {
            lastPriceService.stop(getActivity());
        }
    }

    private GetTopcaRousels getTopcaRousels;

    /**
     * 轮播图
     *
     * @param bannerList
     */
    private void getBranner(List<String> bannerList) {
        banner.setBannerPageClickListener((view, position) -> {
            if (!TextUtils.isEmpty(getTopcaRousels.getData().get(position).getDescription())) {
                intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("url", getTopcaRousels.getData().get(position).getDescription());
                intent.putExtra("type", "3");
                Objects.requireNonNull(getActivity()).startActivity(intent);
            }
        });
        banner.setIndicatorVisible(true);
        // 代码中更改indicator 的位置
        //mMZBanner.setIndicatorAlign(MZBannerView.IndicatorAlign.LEFT);
        //mMZBanner.setIndicatorPadding(10,0,0,150);
        banner.setPages(bannerList, BannerViewHolder::new);
    }

    private void getRecycler_view_test_rv() {
        custom_view.setPinnedTime(1000);
        custom_view.setPullLoadEnable(true);
        custom_view.setMoveForHorizontal(true);
        custom_view.setAutoLoadMore(true);
        custom_view.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {

            @Override
            public void onRefresh(boolean isPullDown) {
                xrboolean = true;
                getHTTp();
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                custom_view.setLoadComplete(true);
            }
        });
    }

    private int data = 0;

    private void getRecycler_view_test_rv1() {
        custom_view1.setPinnedTime(1000);
        custom_view1.setPullLoadEnable(false);
        custom_view1.setMoveForHorizontal(true);
        custom_view1.setAutoLoadMore(false);
        honerViewSimpleAdapter.setOnItemClickListener((view1, data) -> {
            if (AppUtil.Companion.getInstance().isServiceRunning(getActivity(), "sp51.spotpass.com.spotpass.ui.service.LastPriceService")) {
                lastPriceService.stop(getActivity());
                Objects.requireNonNull(getActivity()).unbindService(serviceConnection);
            }
            if (list1 != null && list1.size() > 0) {
                this.data = data;
                HttpALl.Companion.get().setqrykprice(list1.get(data).get("quote").toString(), mHandler, getActivity());
            }
        });
    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1006:
                    Recommend recommend = (Recommend) msg.obj;
                    list1 = new ArrayList<>();
                    StringBuilder qtecode = new StringBuilder();
                    for (int i = 0; recommend.getData().getQtepricelist().size() > i; i++) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("quote", recommend.getData().getQtepricelist().get(i).getQuote());
                        map.put("price", recommend.getData().getQtepricelist().get(i).getPrice());
                        map.put("floatPrice", recommend.getData().getQtepricelist().get(i).getFloatPrice());
                        map.put("floatPercent", recommend.getData().getQtepricelist().get(i).getFloatPercent());
                        map.put("quoteName", recommend.getData().getQtepricelist().get(i).getQuoteName());
                        map.put("exchangeId", recommend.getData().getQtepricelist().get(i).getExchangeId());
                        map.put("lastPrice", "0.0");
                        list1.add(map);
                        qtecode.append(recommend.getData().getQtepricelist().get(i).getQuote()).append("|");
                    }

                    HttpALl.Companion.get().setLastPrice(qtecode.substring(0, qtecode.length() - 1), mHandler, getActivity());

                    if (AppUtil.Companion.getInstance().isServiceRunning(getActivity(), "sp51.spotpass.com.spotpass.ui.service.LastPriceService")) {
                        lastPriceService.stop(getActivity());
                        Objects.requireNonNull(getActivity()).unbindService(serviceConnection);
                    }
//                    honerViewSimpleAdapter.setData(list1);
                    intent = new Intent(getActivity(), LastPriceService.class);
                    intent.putExtra("qtecode", qtecode.substring(0, qtecode.length() - 1));
                    Objects.requireNonNull(getActivity()).bindService(intent, serviceConnection, Activity.BIND_AUTO_CREATE);

//                    Intent intent1 = new Intent(getActivity(), DesignatesActivity.class);
//                    intent1.putExtra("stkcode", list1.get(0).get("quote").toString());
//                    intent1.putExtra("quoteName", list1.get(0).get("quoteName").toString());
//                    intent1.putExtra("price", list1.get(0).get("price").toString());
//                    getActivity().startActivity(intent1);
                    break;
                case 1007:
                    QryStockNoName qryStockNoName = (QryStockNoName) msg.obj;
                    if (list1 != null && list1.size() > 0) {
                        for (int i = 0; qryStockNoName.getData().size() > i; i++) {
                            QryStockNoName.Data qtepricelist = qryStockNoName.getData().get(i);
                            list1.get(i).put("floatPercent", qtepricelist.getFloatPercent());
                            list1.get(i).put("floatPrice", qtepricelist.getFloatPrice());
                            list1.get(i).put("price", qtepricelist.getPrice());
                            list1.get(i).put("quote", qtepricelist.getQuote());
                            list1.get(i).put("lastPrice", qtepricelist.getLastPrice());
                        }
                        honerViewSimpleAdapter.setData(list1);
                    }
                    break;
                case 1008:
                    QryKprice todayPrice = (QryKprice) msg.obj;
                    QryKprice.Data sourceArray = todayPrice.getData();
                    String ClosePrice = sourceArray.getClosePrice();
                    if (list1 != null && list1.size() > 0) {

                        intent = new Intent(getActivity(), ExampleActivity.class);
                        intent.putExtra("stkcode", list1.get(data).get("quote").toString());
                        intent.putExtra("quoteName", list1.get(data).get("quoteName").toString());
                        intent.putExtra("HighPrice", sourceArray.getHighPrice());
                        intent.putExtra("LowPrice", sourceArray.getLowPrice());
                        intent.putExtra("ClosePrice", ClosePrice);
                        intent.putExtra("OpenPrice", sourceArray.getOpenPrice());
                        startActivity(intent);
                    }
                    break;
                case 1014:
                    TindexShow tindexShow = (TindexShow) msg.obj;
                    if (list != null && list.size() > 0) {
                        list.clear();
                    }
                    for (int i = 0; tindexShow.getData().size() > i; i++) {
                        TindexShow.X tindex = tindexShow.getData().get(i);
                        Map<String, Object> map = new HashMap<>();
                        map.put("name", tindex.getUser().getName());
                        map.put("context", tindex.getContent());
                        map.put("time", tindex.getCreated());
                        map.put("role_name", tindex.getRoleName());
                        map.put("created_at", tindex.getCreatedAt());
                        map.put("avatar", tindex.getUser().getAvatar());
                        list.add(map);
                    }
                    adapter.setData(list);
                    break;
                case 1015:
                    getTopcaRousels = (GetTopcaRousels) msg.obj;
                    List<String> list = new ArrayList<>();
                    for (int i = 0; getTopcaRousels.getData().size() > i; i++) {
                        list.add(getTopcaRousels.getData().get(i).getImg().replace("\"", ""));
                    }
                    getBranner(list);
                    break;
                case 1038:
                    if (marquees != null && marquees.size() > 0) {
                        marquees.size();
                    }
                    ProfitNews profitNews = (ProfitNews) msg.obj;
                    marqueeView.setImage(false);
                    for (int i = 0; profitNews.getData().size() > i; i++) {
                        marquee = new Marquee();
                        marquee.setContext(profitNews.getData().get(i).getMoney());
                        marquee.setTitle("用户：" + profitNews.getData().get(i).getUserName());
                        marquees.add(marquee);
                    }
                    marqueeView.startWithList(marquees);
                    break;
                case 1051:
                    AppVersion appVersion = (AppVersion) msg.obj;
                    // 申请多个权限。大神的界面
                    AndPermission.with(Objects.requireNonNull(getActivity()))
                            .requestCode(VisionShowDialog.REQUEST_CODE_PERMISSION_SD)
                            .permission(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)
                            // rationale作用是：用户拒绝一次权限，再次申请时先征求用户同意，再打开授权对话框，避免用户勾选不再提示。
                            .rationale((requestCode, rationale) -> {
                                        // 这里的对话框可以自定义，只要调用rationale.resume()就可以继续申请。
                                        AndPermission.rationaleDialog(getActivity(), rationale).show();
                                    }
                            )
                            .send();
                    mLoadCount = appVersion.getData().getUpdateType();
                    url = appVersion.getData().getAppUrl();
                    version = appVersion.getData().getVersion();
                    String version1 = Objects.requireNonNull(AppUtil.Companion.getInstance().getVersionName(1, Objects.requireNonNull(getActivity()))).replace(".", "");
                    if (Long.parseLong(version1) <= Long.parseLong(version.replace(".", ""))) {
                        getHTTp();
                    } else {
                        if (mLoadCount == 1) {
                            visionShowDialog.ShowDialog(version, "建议升级", url, mLoadCount);
                        } else {
                            visionShowDialog.ShowDialog(version, "强制升级", url, mLoadCount);
                        }
                    }
                    break;
            }

            if (xrboolean) {
                xrboolean = false;
                custom_view.stopRefresh();
            }
        }
    };

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LastPriceService.Binder binder = (LastPriceService.Binder) service;
            LastPriceService myService = binder.getService();
            myService.setCallback(data -> {
                Message msg = new Message();
                msg.what = 1007;
                msg.obj = data;
                mHandler.sendMessage(msg);
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private Intent intent = null;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_imageview_customer_service:
                intent = new Intent(getActivity(), OnlineServiceActivity.class);
                startActivity(intent);
                break;
            case R.id.profit_list_liner:
                startActivity(new Intent(getActivity(), ProfitListActivity.class));
                break;
            case R.id.integral_mall_liner:
                if (!TextUtils.isEmpty(SPUtils.Companion.getInstance(Objects.requireNonNull(getActivity()), "login").getString("login", ""))) {
                    startActivity(new Intent(getActivity(), IntegralMallActivity.class));
                } else {
                    Util.INSTANCE.registerA1(getActivity());
                }
                break;
            case R.id.grab_a_red_envelope_liner:
                startActivity(new Intent(getActivity(), SchoolActivity.class));
                break;
        }
    }

    private VisionShowDialog visionShowDialog = null;
    private String url = null;
    private String version = "";

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        AndPermission.onRequestPermissionsResult(this, requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case VisionShowDialog.REQUEST_CODE_SETTING: {
                Toast.makeText(getActivity(), R.string.message_setting_back, Toast.LENGTH_LONG).show();
                //设置成功，再次请求更新
                visionShowDialog.ShowDialog(version, "建议升级", url, mLoadCount);
                break;
            }
        }
    }
}

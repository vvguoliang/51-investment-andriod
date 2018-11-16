package sp51.spotpass.com.spotpass.ui.kchartlib.chatr;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.umeng.analytics.MobclickAgent;

import sp51.spotpass.com.spotpass.playtablayout.PlayTabLayout;
import sp51.spotpass.com.spotpass.playtablayout.TouchableTabLayout;
import sp51.spotpass.com.spotpass.ui.activity.DesignatesActivity;
import sp51.spotpass.com.spotpass.ui.activity.ExperienceVoucherAcitivty;
import sp51.spotpass.com.spotpass.ui.activity.MyAssetsActivity;
import sp51.spotpass.com.spotpass.ui.activity.RechargeActivity;
import sp51.spotpass.com.spotpass.ui.activity.RegisterActivity;
import sp51.spotpass.com.spotpass.ui.adapter.ExampleTabAdapter;
import sp51.spotpass.com.spotpass.ui.adapter.SelectionVarietiesAdapter;
import sp51.spotpass.com.spotpass.ui.adapter.TransactionViewSimpleAdapter;
import sp51.spotpass.com.spotpass.ui.baseEntity.QryAccount;
import sp51.spotpass.com.spotpass.ui.baseEntity.QryKchartr1;
import sp51.spotpass.com.spotpass.ui.baseEntity.QryKprice;
import sp51.spotpass.com.spotpass.ui.baseEntity.QryQuoteTrdTime;
import sp51.spotpass.com.spotpass.ui.baseEntity.QryStockNoName;
import sp51.spotpass.com.spotpass.ui.baseEntity.QryTicket;
import sp51.spotpass.com.spotpass.ui.baseEntity.TodayPrice;
import sp51.spotpass.com.spotpass.ui.baseEntity.qrystock1;
import sp51.spotpass.com.spotpass.ui.ftagment.ExampleFragment0;
import sp51.spotpass.com.spotpass.ui.ftagment.ExampleFragment2;
import sp51.spotpass.com.spotpass.ui.ftagment.ExampleFragment3;
import sp51.spotpass.com.spotpass.ui.ftagment.ExampleFragment4;
import sp51.spotpass.com.spotpass.ui.ftagment.ExampleFragment6;
import sp51.spotpass.com.spotpass.ui.ftagment.ExampleFragment7;
import sp51.spotpass.com.spotpass.ui.http.HttpALl;
import sp51.spotpass.com.spotpass.ui.kchartlib.utils.ConvertUtil;
import sp51.spotpass.com.spotpass.ui.service.LastPriceService;
import sp51.spotpass.com.spotpass.ui.service.QryKChartService;
import sp51.spotpass.com.spotpass.ui.utils.AppUtil;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sp51.spotpass.com.spotpass.R;
import sp51.spotpass.com.spotpass.base.BaseActivity;
import sp51.spotpass.com.spotpass.ui.utils.SPUtils;
import sp51.spotpass.com.spotpass.ui.utils.Util;
import sp51.spotpass.com.spotpass.ui.view.Dialog.DesignatesDialogBuilder;
import sp51.spotpass.com.spotpass.ui.view.NoScrollViewPager;

@SuppressWarnings({"deprecation", "SuspiciousMethodCalls", "CollectionAddedToSelf"})
@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
public class ExampleActivity extends BaseActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, TouchableTabLayout.TabClickListener {

    LinearLayout linear_button_linear, ll_status;
    ImageView title_right_arrow_white, tv_price_image;
    TextView title_textview, title_integral_rule, tv_Highest, tv_minimum, tv_yesterday, tv_opening_quotation, tv_percent, tv_price, recharge_cash_coupon, textView_my_available_capital;

    private String stkcode = "";
    private String quoteName = "";

    private String HighPrice = "";
    private String LowPrice = "";
    private String ClosePrice = "";
    private String OpenPrice = "";

    //    private ViewPager viewpager_jiage;
    private SelectionVarietiesAdapter selectionVarietiesAdapter;

    private RecyclerView recycler_view_test_rv;
    private TransactionViewSimpleAdapter transactionViewSimpleAdapter;

    private int mLoadCount = 0;

    private ExampleTabAdapter exampleTabAdapter;
    private NoScrollViewPager transaction_viewpage;
    private PlayTabLayout transaction_PlayTab;
    private TouchableTabLayout tabLayout;

    private RelativeLayout title_relative;

    private Button example_register;

    private int ismore = 0;  // 买张

    private int viewpageposition = 0;

    private boolean isCancelable = false;

    private Map<String, Object> map = null;

    private QryTicket qryTicket = null;
    private QryTicket.Data.Ticketlist ticketlist = null;

    private String money = "";

    private int type = 1;

    private int timetype = 1;

    boolean aBoolean = true;

    private boolean typeimte = false;

    private float FloatPrice = 0;

    private String[] quoteName1 = null;

    private String stkcode1 = "";

    private ExampleFragment0 exampleFragment0;
    //    private ExampleFragment1 exampleFragment1;
    private ExampleFragment2 exampleFragment2;
    private ExampleFragment3 exampleFragment3;
    private ExampleFragment4 exampleFragment4;
    //    private ExampleFragment5 exampleFragment5;
    private ExampleFragment6 exampleFragment6;
    private ExampleFragment7 exampleFragment7;
//    private ExampleFragment8 exampleFragment8;

    private ArrayList<Fragment> list = null;

    private List<Map<String, Object>> mapList = null;

    private List<KLineEntity> lineEntities = null;

    @Override
    public void initParams(@Nullable Bundle arguments) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        stkcode = getIntent().getStringExtra("stkcode");
        HighPrice = getIntent().getStringExtra("HighPrice");
        LowPrice = getIntent().getStringExtra("LowPrice");
        ClosePrice = getIntent().getStringExtra("ClosePrice");
        OpenPrice = getIntent().getStringExtra("OpenPrice");

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_example;
    }

    @Override
    public void initView(@NotNull View rootView) {

    }

    @Override
    public void setListener() {
    }

    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void doBusiness() {
        tv_price = findViewById(R.id.tv_price);
        tv_price_image = findViewById(R.id.tv_price_image);
        tv_percent = findViewById(R.id.tv_percent);
        linear_button_linear = findViewById(R.id.linear_button_linear);
        ll_status = findViewById(R.id.ll_status);
        title_right_arrow_white = findViewById(R.id.title_right_arrow_white);
        title_right_arrow_white.setVisibility(View.VISIBLE);
        title_textview = findViewById(R.id.title_textview);
        title_integral_rule = findViewById(R.id.title_integral_rule);
        tv_Highest = findViewById(R.id.tv_Highest);
        tv_minimum = findViewById(R.id.tv_minimum);
        tv_opening_quotation = findViewById(R.id.tv_opening_quotation);
        tv_yesterday = findViewById(R.id.tv_yesterday);
        title_integral_rule.setVisibility(View.GONE);
        title_textview.setVisibility(View.VISIBLE);

        recycler_view_test_rv = findViewById(R.id.recycler_view_test_rv);
//        viewpager_jiage = findViewById(R.id.viewpager_jiage);
        textView_my_available_capital = findViewById(R.id.textView_my_available_capital);
        title_relative = findViewById(R.id.title_relative);
        findViewById(R.id.account_management_linta).setOnClickListener(this);

        transaction_viewpage = findViewById(R.id.transaction_viewpage);
        recharge_cash_coupon = findViewById(R.id.recharge_cash_coupon);
        transaction_PlayTab = findViewById(R.id.transaction_PlayTab);

        example_register = findViewById(R.id.example_register);

        example_register.setOnClickListener(this);

        title_right_arrow_white.setOnClickListener(this);
        title_integral_rule.setOnClickListener(this);

        quoteName = getIntent().getStringExtra("quoteName");
//        quoteName1 = quoteName.split(",");
        name = quoteName;
//        tv_price.setText(name);
        title_textview.setText(name);


        tv_Highest.setText("最高  " + HighPrice);
        tv_minimum.setText("最低  " + LowPrice);
        tv_yesterday.setText("昨收  " + ClosePrice);
        tv_opening_quotation.setText("今开  " + OpenPrice);

        exampleTabAdapter = new ExampleTabAdapter(getSupportFragmentManager());
        exampleFragment0 = new ExampleFragment0();
//        exampleFragment1 = new ExampleFragment1();
        exampleFragment2 = new ExampleFragment2();
        exampleFragment3 = new ExampleFragment3();
        exampleFragment4 = new ExampleFragment4();
//        exampleFragment5 = new ExampleFragment5();
        exampleFragment6 = new ExampleFragment6();
        exampleFragment7 = new ExampleFragment7();
//        exampleFragment8 = new ExampleFragment8();

        list = new ArrayList<>();
        list.add(exampleFragment0);
//        list.add(exampleFragment1);
        list.add(exampleFragment2);
        list.add(exampleFragment3);
        list.add(exampleFragment4);
//        list.add(exampleFragment5);
        list.add(exampleFragment6);
        list.add(exampleFragment7);
//        list.add(exampleFragment8);

        exampleFragment2.getTypeimage(data -> HttpALl.Companion.get().setqrykchart(stkcode, timetype, mHandler, ExampleActivity.this));
        exampleFragment3.getTypeimage(data -> HttpALl.Companion.get().setqrykchart(stkcode, timetype, mHandler, ExampleActivity.this));
        exampleFragment4.getTypeimage(data -> HttpALl.Companion.get().setqrykchart(stkcode, timetype, mHandler, ExampleActivity.this));
        exampleFragment6.getTypeimage(data -> HttpALl.Companion.get().setqrykchart(stkcode, timetype, mHandler, ExampleActivity.this));
        exampleFragment7.getTypeimage(data -> HttpALl.Companion.get().setqrykchart(stkcode, timetype, mHandler, ExampleActivity.this));

        tabLayout = transaction_PlayTab.getTabLayout();
        tabLayout.setTabClickListener(this);
        getVoid(type);
        transactionViewSimpleAdapter = new TransactionViewSimpleAdapter(this);

        recycler_view_test_rv.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler_view_test_rv.setLayoutManager(linearLayoutManager);
        recycler_view_test_rv.setAdapter(transactionViewSimpleAdapter);

        transactionViewSimpleAdapter.setOnItemClickListener(new TransactionViewSimpleAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int data) {
                transactionViewSimpleAdapter.getTransactionView(data, "");
                viewpageposition = data;
                if (mapList != null && mapList.size() > 0) {
                    map = mapList.get(data);
                    stkcode1 = mapList.get(data).get("stkcode").toString();
                }
                if (qryTicket != null && qryTicket.getData().getTicketlist().size() > 0) {
                    for (int i = 0; qryTicket.getData().getTicketlist().size() > i; i++) {
                        ticketlist = qryTicket.getData().getTicketlist().get(i);
                        if (mapList != null && mapList.size() > 0) {
                            if (Float.parseFloat(mapList.get(data).get("marginrate").toString()) == Float.parseFloat(ticketlist.getQprice())) {
                                if (Long.parseLong(ticketlist.getExcount()) >= 1) {
                                    recharge_cash_coupon.setText((int) Float.parseFloat(ticketlist.getQprice()) + "x" + ticketlist.getExcount());
                                } else {
                                    recharge_cash_coupon.setText((int) Float.parseFloat(ticketlist.getQprice()) + "");
                                }
                                return;
                            } else {
                                recharge_cash_coupon.setText("0");
                            }
                        }
                    }
                }
            }
        });
    }

//    private ServiceConnection serviceConnection = new ServiceConnection() {
//        @Override
//        public void onServiceConnected(ComponentName name, IBinder service) {
//            QryKChartService.Binder binder = (QryKChartService.Binder) service;
//            QryKChartService myService = binder.getService();
//            myService.setCallback(data -> {
//                Message msg = new Message();
//                msg.what = 1010;
//                msg.obj = data;
//                mHandler.sendMessage(msg);
//            });
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName name) {
//
//        }
//    };

    /**
     * 轮播图
     *
     * @param mapList
     */
    private void getBranner(List<Map<String, Object>> mapList) {
//        selectionVarietiesAdapter = new SelectionVarietiesAdapter();
//        selectionVarietiesAdapter.getData(mapList);
//        viewpager_jiage.setAdapter(selectionVarietiesAdapter);
//        viewpager_jiage.setPageMargin(50);
//        viewpager_jiage.setPageTransformer(true, new ScaleTransformer());
//        viewpager_jiage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            }
//
//            @SuppressLint("SetTextI18n")
//            @Override
//            public void onPageSelected(int position) {
//                selectionVarietiesAdapter.getINt(position);
//                viewpageposition = position;
//                if (qryTicket != null && qryTicket.getData().getTicketlist().size() > 0) {
//                    ticketlist = qryTicket.getData().getTicketlist().get(position);
//                    if (Long.parseLong(ticketlist.getExcount()) >= 1) {
//                        recharge_cash_coupon.setText((int) Float.parseFloat(ticketlist.getQprice()) + "x" + ticketlist.getExcount());
//                    } else {
//                        recharge_cash_coupon.setText((int) Float.parseFloat(ticketlist.getQprice()));
//                    }
//                }
//                if (mapList != null && mapList.size() > 0) {
//                    map = mapList.get(viewpageposition);
//                }
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//        viewpager_jiage.setCurrentItem(1, true);
    }

    private void getVoid(int type) {
        if (type == 0) {
            getData(type);
            title_integral_rule.setText("黑天");
            title_integral_rule.setTextColor(Color.BLACK);
            linear_button_linear.setBackgroundColor(Color.BLACK);
            tv_Highest.setTextColor(Color.WHITE);
            tv_minimum.setTextColor(Color.WHITE);
            tv_yesterday.setTextColor(Color.WHITE);
            tv_opening_quotation.setTextColor(Color.WHITE);
            transaction_viewpage.setBackgroundColor(Color.BLACK);
            ll_status.setBackgroundColor(Color.BLACK);
            example_register.setTextColor(Color.WHITE);
        } else {
            getData(type);
            title_integral_rule.setText("白天");
            title_integral_rule.setTextColor(Color.WHITE);
            linear_button_linear.setBackgroundColor(Color.WHITE);
            tv_Highest.setTextColor(Color.BLACK);
            tv_minimum.setTextColor(Color.BLACK);
            tv_yesterday.setTextColor(Color.BLACK);
            tv_opening_quotation.setTextColor(Color.BLACK);
            transaction_viewpage.setBackgroundColor(Color.WHITE);
            ll_status.setBackgroundColor(Color.WHITE);
            example_register.setTextColor(Color.BLACK);
        }
    }

    private void getData(int type) {
        int[] colosint;
        if (type == 0) {
            colosint = new int[list.size()];
            for (int j = 0; list.size() > j; j++) {
                colosint[j] = R.color.black;
            }
            tabLayout.setTabTextColors(R.color.white, Color.parseColor("#FF6D64"));
            transaction_PlayTab.setBackgroundColor(Color.BLACK);
        } else {
            colosint = new int[list.size()];
            for (int j = 0; list.size() > j; j++) {
                colosint[j] = R.color.white;
            }
            tabLayout.setTabTextColors(R.color.black, Color.parseColor("#FF6D64"));
            transaction_PlayTab.setBackgroundColor(Color.WHITE);
        }
        tabLayout.setSelectedTabIndicatorColor(Color.parseColor("#FF6D64"));
        transaction_PlayTab.setColors(colosint);
        tabLayout.setupWithViewPager(transaction_viewpage);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        tabLayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tabLayout.setSelectedTabIndicatorHeight(7);
        exampleTabAdapter.setLazy(list);
        transaction_viewpage.setAdapter(exampleTabAdapter);
        exampleTabAdapter.notifyDataSetChanged();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            title_relative.setVisibility(View.GONE);
            linear_button_linear.setVisibility(View.GONE);

            ViewGroup.LayoutParams lp = transaction_viewpage.getLayoutParams();
            lp.width = LinearLayout.MarginLayoutParams.MATCH_PARENT;
            lp.height = LinearLayout.MarginLayoutParams.MATCH_PARENT;
            transaction_viewpage.setLayoutParams(lp);

        } else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            title_relative.setVisibility(View.VISIBLE);
            linear_button_linear.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_right_arrow_white:
                finish();
                break;
            case R.id.title_integral_rule:
                if (aBoolean) {
                    aBoolean = false;
                    type = 1;
                } else {
                    aBoolean = true;
                    type = 0;
                }
                getVoid(type);
                break;
            case R.id.example_more:
                if (!AppUtil.Companion.getInstance().isFastDoubleClick()) {
                    if (TextUtils.isEmpty(SPUtils.Companion.getInstance(this, "login").getString("login", ""))) {
                        Util.INSTANCE.registerA(this);
                    } else {
                        isCancelable = false;
                        SPUtils.Companion.getInstance(this, "voucher").put("voucher", "");
                        new DesignatesDialogBuilder(this).title(quoteName)
                                .isboolean(isCancelable, stkcode)
                                .listData(map, money, ticketlist, viewpageposition)
                                .setCancelOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (TextUtils.isEmpty(SPUtils.Companion.getInstance(ExampleActivity.this, "login").getString("login", ""))) {
                                            Util.INSTANCE.registerA(ExampleActivity.this);
                                        } else {
                                            intent = new Intent(ExampleActivity.this, RechargeActivity.class);
                                            intent.putExtra("type", 0);
                                            startActivity(intent);
                                        }
                                    }
                                }).setSureOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                intent = new Intent(ExampleActivity.this, ExperienceVoucherAcitivty.class);
                                intent.putExtra("Qprice", ticketlist.getQprice());
                                startActivityForResult(intent, 210);
                            }
                        })
                                .build().show();
                    }
                }
                break;
            case R.id.example_fall:
                if (!AppUtil.Companion.getInstance().isFastDoubleClick()) {
                    if (TextUtils.isEmpty(SPUtils.Companion.getInstance(this, "login").getString("login", ""))) {
                        Util.INSTANCE.registerA(this);
                    } else {
                        isCancelable = true;
                        SPUtils.Companion.getInstance(this, "voucher").put("voucher", "");
                        new DesignatesDialogBuilder(this).title(quoteName)
                                .isboolean(isCancelable, stkcode)
                                .listData(map, money, ticketlist, viewpageposition)
                                .setCancelOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        if (TextUtils.isEmpty(SPUtils.Companion.getInstance(ExampleActivity.this, "login").getString("login", ""))) {
                                            Util.INSTANCE.registerA(ExampleActivity.this);
                                        } else {
                                            Intent intent = new Intent(ExampleActivity.this, RechargeActivity.class);
                                            intent.putExtra("type", 0);
                                            startActivity(intent);
                                        }
                                    }
                                })
                                .setSureOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(ExampleActivity.this, ExperienceVoucherAcitivty.class);
                                        intent.putExtra("Qprice", ticketlist.getQprice());
                                        startActivityForResult(intent, 210);
                                    }
                                })
                                .build().show();
                    }
                }
                break;
            case R.id.example_register:
                if (TextUtils.isEmpty(SPUtils.Companion.getInstance(this, "login").getString("login", ""))) {
                    Util.INSTANCE.registerA(this);
                } else {
                    intent = new Intent(ExampleActivity.this, DesignatesActivity.class);
                    intent.putExtra("stkcode", stkcode);
                    intent.putExtra("stkcode1", stkcode1);
                    intent.putExtra("quoteName", quoteName);
                    intent.putExtra("price", tv_price.getText().toString());
                    this.startActivity(intent);
//                    if (AppUtil.Companion.getInstance().isServiceRunning(this, "sp51.spotpass.com.spotpass.ui.service.QryKChartService")) {
//                        unbindService(serviceConnection);
//                    }
                }
                break;
            case R.id.account_management_linta:
                if (TextUtils.isEmpty(SPUtils.Companion.getInstance(this, "login").getString("login", ""))) {
                    Util.INSTANCE.registerA(this);
                } else {
                    intent = new Intent(this, MyAssetsActivity.class);
                    startActivity(intent);
                }
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 210 && resultCode == 210) {
            new DesignatesDialogBuilder(this).title(quoteName)
                    .isboolean(isCancelable, stkcode)
                    .listData(map, money, ticketlist, viewpageposition)
                    .setCancelOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(ExampleActivity.this, RechargeActivity.class);
                            intent.putExtra("type", 0);
                            startActivity(intent);
                        }
                    })
                    .setSureOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(ExampleActivity.this, ExperienceVoucherAcitivty.class);
                            intent.putExtra("Qprice", ticketlist.getQprice());
                            startActivityForResult(intent, 210);
                        }
                    }).build().show();
        }
    }

    private String name = "";
    private Intent intent = null;

    private int tye = 0;

    @Override
    protected void onResume() {
        super.onResume();
//        HttpALl.Companion.get().setQryStock("qryStock", stkcode, mHandler, this);
        HttpALl.Companion.get().setqrystock(stkcode, mHandler, this);
        HttpALl.Companion.get().setQRYQUOTETRDTIME("qryQuoteTrdTime", stkcode, mHandler, this);
        if (!TextUtils.isEmpty(SPUtils.Companion.getInstance(this, "login").getString("login", ""))) {
            HttpALl.Companion.get().setQryAccount("qryAccount",
                    SPUtils.Companion.getInstance(this, "login").getString("phone", ""), "", "", mHandler, this);
            HttpALl.Companion.get().setqryTicket(mHandler, this);
        }
        intent = new Intent(this, LastPriceService.class);
        intent.putExtra("qtecode", stkcode);
        bindService(intent, serviceConnection1, BIND_AUTO_CREATE);

//        if (!AppUtil.Companion.getInstance().isServiceRunning(this, "sp51.spotpass.com.spotpass.ui.service.QryKChartService")) {
//            intent = new Intent(this, QryKChartService.class);
//            intent.putExtra("qtecode", stkcode);
//            intent.putExtra("timetype", timetype);
//            bindService(intent, serviceConnection, Activity.BIND_AUTO_CREATE);
//        }

        MobclickAgent.onPageStart(name); //手动统计页面("SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(this);

        HttpALl.Companion.get().setqrykchart(stkcode, timetype, mHandler, this);
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
        @SuppressLint({"SetTextI18n", "CheckResult"})
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1003:
                    QryAccount qryAccount = (QryAccount) msg.obj;
                    money = qryAccount.getData().getBalance();
                    textView_my_available_capital.setText(money);
                    break;
                case 1034:
                    qryTicket = (QryTicket) msg.obj;
                    if (qryTicket.getData().getTicketlist().size() > 0) {
                        QryTicket.Data.Ticketlist ticketlist = qryTicket.getData().getTicketlist().get(viewpageposition);
                        if (Long.parseLong(ticketlist.getExcount()) >= 1) {
                            recharge_cash_coupon.setText((int) Float.parseFloat(ticketlist.getQprice()) + "x" + ticketlist.getExcount());
                        } else {
                            recharge_cash_coupon.setText((int) Float.parseFloat(ticketlist.getQprice()));
                        }
                    } else {
                        recharge_cash_coupon.setText("0");
                    }
                    break;
                case 114:
                    Intent intent = new Intent(ExampleActivity.this, RegisterActivity.class);
                    intent.putExtra("type", 2);
                    startActivityForResult(intent, 209);
                    break;
                case 1011:
                    qrystock1 qryStock = (qrystock1) msg.obj;
                    mapList = new ArrayList<>();
                    for (int i = 0; qryStock.getData().size() > i; i++) {
                        qrystock1.Data qrylist = qryStock.getData().get(i);
                        if (stkcode.equals(qrylist.getQuotecode()) && Long.parseLong(qrylist.getMarginrate()) <= 500) {
                            Map<String, Object> map = new HashMap<>();
                            map.put("stkcode", qrylist.getStkcode());
                            map.put("stkname", qrylist.getStkname());
                            map.put("stktype", qrylist.getStktype());
                            map.put("stksize", qrylist.getStksize());
                            map.put("marginrate", qrylist.getMarginrate());
                            map.put("openfeerate", qrylist.getOpenfeerate());
                            map.put("closefeerate", qrylist.getClosefeerate());
                            map.put("holdfeerate", qrylist.getHoldfeerate());
                            map.put("orderlimitqry", qrylist.getOrderlimitqry());
                            map.put("sumlimitqry", qrylist.getSumlimitqry());
                            map.put("quotecode", qrylist.getQuotecode());
                            map.put("losspoints", qrylist.getLosspoints());
                            map.put("profitpoints", qrylist.getProfitpoints());
                            map.put("unitprice", qrylist.getUnitprice());
                            mapList.add(map);
                        }
                    }
                    transactionViewSimpleAdapter.setData(mapList);
                    transactionViewSimpleAdapter.getTransactionView(1, "");
                    stkcode1 = mapList.get(1).get("stkcode").toString();
                    if (mapList != null && mapList.size() > 0) {
                        map = mapList.get(1);
                    }
                    break;
                case 1008:
                    QryKprice todayPrice = (QryKprice) msg.obj;
                    QryKprice.Data sourceArray = todayPrice.getData();
                    String time = sourceArray.getTime();
                    ClosePrice = sourceArray.getClosePrice();
                    tv_Highest.setText("最高  " + sourceArray.getHighPrice());
                    tv_minimum.setText("最低  " + sourceArray.getLowPrice());
                    tv_yesterday.setText("昨收  " + ClosePrice);
                    tv_opening_quotation.setText("今开  " + sourceArray.getOpenPrice());
                    break;
                case 1009:
                    HttpALl.Companion.get().setqrykprice(stkcode, mHandler, ExampleActivity.this);
                    QryStockNoName qryStockNoName = (QryStockNoName) msg.obj;
                    String Price = "";
                    for (int i = 0; qryStockNoName.getData().size() > i; i++) {
                        QryStockNoName.Data qtepricelist = qryStockNoName.getData().get(i);
                        if (stkcode.equals(qtepricelist.getQuote())) {
                            RequestOptions options = new RequestOptions();
                            if (Float.parseFloat(qtepricelist.getPrice()) >= Float.parseFloat(qtepricelist.getLastPrice())) {
                                tv_price.setTextColor(Color.parseColor("#FF6D64"));
                                tv_percent.setTextColor(Color.parseColor("#FF6D64"));
                            } else {
                                tv_price.setTextColor(Color.parseColor("#028845"));
                                tv_percent.setTextColor(Color.parseColor("#028845"));
                            }
                            int ic_ = 0;
                            if (FloatPrice != 0) {
                                if (Float.parseFloat(qtepricelist.getFloatPrice()) > FloatPrice) {
                                    ic_ = R.mipmap.ic_up;
                                } else {
                                    ic_ = R.mipmap.ic_down;
                                }
                                FloatPrice = Float.parseFloat(qtepricelist.getFloatPrice());
                            } else {
                                FloatPrice = Float.parseFloat(qtepricelist.getFloatPrice());
                            }
                            options.centerCrop().placeholder(ic_).error(ic_).fallback(ic_);
                            if (!ExampleActivity.this.isDestroyed()) {
                                Glide.with(ExampleActivity.this).load(ic_).apply(options).into(tv_price_image);
                            }
                            tv_price.setText(qtepricelist.getPrice());
                            tv_percent.setText(qtepricelist.getFloatPrice() + "  " + qtepricelist.getFloatPercent() + "%");
                            Price = qtepricelist.getPrice();
                        }
                    }
                    if (lineEntities != null && lineEntities.size() > 0) {
                        if (tye == 1) {
                            tye = 0;
                            switch (timetype) {
                                case 1:
                                    exampleFragment0.getTy(System.currentTimeMillis() / 1000L, Float.parseFloat(Price), ClosePrice);
                                    break;
                                case 6:
                                    exampleFragment2.getTyp(System.currentTimeMillis() / 1000L, Float.parseFloat(Price), 1);
                                    break;
                                case 2:
                                    exampleFragment3.getTyp(System.currentTimeMillis() / 1000L, Float.parseFloat(Price), 1);
                                    break;
                                case 3:
                                    exampleFragment4.getTyp(System.currentTimeMillis() / 1000L, Float.parseFloat(Price), 1);
                                    break;
                                case 5:
                                    exampleFragment6.getTyp(System.currentTimeMillis() / 1000L, Float.parseFloat(Price), 1);
                                    break;
                                case 4:
                                    exampleFragment7.getTyp(System.currentTimeMillis() / 1000L, Float.parseFloat(Price), 2);
                                    break;
                            }
                        } else {
                            tye++;
                        }
                    }
                    break;
                case 1025:
                    QryQuoteTrdTime qryQuoteTrdTime = (QryQuoteTrdTime) msg.obj;
                    if (!qryQuoteTrdTime.getData().getMemo().equals("交易日")) {
                        findViewById(R.id.example_more).setOnClickListener(ExampleActivity.this);
                        findViewById(R.id.example_more).setBackgroundResource(R.drawable.shape_c5_grle_while);
                        findViewById(R.id.example_more).setClickable(false);

                        findViewById(R.id.example_fall).setOnClickListener(ExampleActivity.this);
                        findViewById(R.id.example_fall).setBackgroundResource(R.drawable.shape_c5_grle_while);
                        findViewById(R.id.example_fall).setClickable(false);
                        title_textview.setText(name + "  休");
                    } else {
                        findViewById(R.id.example_more).setOnClickListener(ExampleActivity.this);
                        findViewById(R.id.example_more).setBackgroundResource(R.drawable.shape_corner_pink_red);
                        findViewById(R.id.example_more).setClickable(true);

                        findViewById(R.id.example_fall).setOnClickListener(ExampleActivity.this);
                        findViewById(R.id.example_fall).setBackgroundResource(R.drawable.shape_corner_green);
                        findViewById(R.id.example_fall).setClickable(true);
                    }
                    break;
                case 1010:
                    QryKchartr1 qryKChart = (QryKchartr1) msg.obj;
                    lineEntities = new ArrayList<>();
                    for (int i = 0; qryKChart.getData().size() > i; i++) {
                        QryKchartr1.Data sourceArray1 = qryKChart.getData().get(i);
                        KLineEntity kLineEntity = new KLineEntity();
                        if (!TextUtils.isEmpty(sourceArray1.getClosePrice())) {
                            kLineEntity.setClose(ConvertUtil.Companion.get().convertToFloat(sourceArray1.getClosePrice(), 0.0f));
                        }
                        if (!TextUtils.isEmpty(sourceArray1.getOpenPrice())) {
                            kLineEntity.setOpen(ConvertUtil.Companion.get().convertToFloat(sourceArray1.getOpenPrice(), 0.0f));
                        }
                        if (!TextUtils.isEmpty(sourceArray1.getHighPrice())) {
                            kLineEntity.setHigh(ConvertUtil.Companion.get().convertToFloat(sourceArray1.getHighPrice(), 0.0f));
                        }
                        if (!TextUtils.isEmpty(sourceArray1.getLowPrice())) {
                            kLineEntity.setLow(ConvertUtil.Companion.get().convertToFloat(sourceArray1.getLowPrice(), 0.0f));
                        }
                        if (!TextUtils.isEmpty(sourceArray1.getPrice())) {
                            kLineEntity.setVolume(ConvertUtil.Companion.get().convertToFloat(sourceArray1.getPrice(), 0.0f));
                        }
                        kLineEntity.setDate(sourceArray1.getTime() + "");
                        lineEntities.add(kLineEntity);
                    }
                    switch (timetype) {
                        case 1:
                            exampleFragment0.getType(lineEntities, ClosePrice);
                            break;
                        case 6:
                            exampleFragment2.getType(lineEntities, 1);
                            break;
                        case 2:
                            exampleFragment3.getType(lineEntities, 1);
                            break;
                        case 3:
                            exampleFragment4.getType(lineEntities, 1);
                            break;
                        case 5:
                            exampleFragment6.getType(lineEntities, 1);
                            break;
                        case 4:
                            exampleFragment7.getType(lineEntities, 2);
                            break;
                    }
                    break;
            }
        }
    };

    private ServiceConnection serviceConnection1 = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LastPriceService.Binder binder = (LastPriceService.Binder) service;
            LastPriceService myService = binder.getService();
            myService.setCallback(data -> {
                Message msg = new Message();
                msg.what = 1009;
                msg.obj = data;
                mHandler.sendMessage(msg);
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onPause() {
        super.onPause();
        unbindService(serviceConnection1);
        MobclickAgent.onPageEnd(name); //手动统计页面("SplashScreen"为页面名称，可自定义)，必须保证 onPageEnd 在 onPause 之前调用，因为SDK会在 onPause 中保存onPageEnd统计到的页面数据。
        MobclickAgent.onPause(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onTabClicked(int selected, boolean fromTouch, @Nullable MotionEvent event) {
        switch (selected) {
            case 0:
                timetype = 1;
                break;
            case 1:
                timetype = 6;
                break;
            case 2:
                timetype = 2;
                break;
            case 3:
                timetype = 3;
                break;
            case 4:
                timetype = 5;
                break;
            case 5:
                timetype = 4;
                break;
            default:
                break;
        }
        HttpALl.Companion.get().setqrykchart(stkcode, timetype, mHandler, this);
//        if (timetype != 1) {
//            unbindService(serviceConnection);
//            intent = new Intent(this, QryKChartService.class);
//            intent.putExtra("qtecode", stkcode);
//            intent.putExtra("timetype", timetype);
//            bindService(intent, serviceConnection, Activity.BIND_AUTO_CREATE);
//        }
    }
}

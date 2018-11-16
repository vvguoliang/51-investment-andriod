package sp51.spotpass.com.spotpass.ui.view.Dialog;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.ActionBar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import sp51.spotpass.com.spotpass.ui.activity.WebViewActivity;
import sp51.spotpass.com.spotpass.ui.baseEntity.QryStockNoName;
import sp51.spotpass.com.spotpass.ui.baseEntity.QryTicket;
import sp51.spotpass.com.spotpass.ui.http.HttpALl;
import sp51.spotpass.com.spotpass.ui.http.HttpImplements;
import sp51.spotpass.com.spotpass.ui.utils.AppUtil;

import java.util.Map;
import java.util.Objects;

import sp51.spotpass.com.spotpass.R;
import sp51.spotpass.com.spotpass.ui.utils.SPUtils;
import sp51.spotpass.com.spotpass.ui.utils.ToatUtils;

/**
 * @Time : 2018/5/18 no 14:27
 * @USER : vvguoliang
 * @File : DialogBuilder.java
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
public class DesignatesDialogBuilder implements View.OnClickListener {

    private Context context;
    private int themeResId;
    private View layout;
    private boolean cancelable = true;
    private CharSequence title;
    private CharSequence cancelText;
    private CharSequence sureText;//除了message的所有文本，不写则Gone。
    private View.OnClickListener sureClickListener, cancelClickListener;
    private String stkcode = "";
    private boolean isCancelable = false;

    private QryTicket.Data.Ticketlist ticketlist;

    private int orderlimitqry = 0;

    private int orderlimit = 1;

    private Map<String, Object> map;
    private String money;

    private int type = 0;

    private String losspoints = "1";
    private String profitpoints = "1";

    private TextView target_profit76;
    private TextView target_profit20;
    private TextView target_profit40;
    private TextView target_profit60;
    private TextView target_profit80;
    private TextView target_profit100;
    private TextView stop_loss76;
    private TextView stop_loss20;
    private TextView stop_loss30;
    private TextView stop_loss40;
    private TextView stop_loss50;
    private TextView stop_loss60;
    private TextView select_coupons_text;
    private TextView total_text;
    private TextView total_cost_text;
    private TextView designates_dialog_text;
    private TextView designates_dialog_wave;

    public DesignatesDialogBuilder(Context context) {
        this(context, R.style.CustomDialog);
    }

    @SuppressLint("InflateParams")
    private DesignatesDialogBuilder(Context context, int themeResId) {
        this(context, themeResId, ((LayoutInflater) Objects.requireNonNull(context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE))).inflate(R.layout.custom_designates_dialog_layout, null));
    }

    //自定义layout用这个
    private DesignatesDialogBuilder(Context context, int themeResId, View layout) {
        this.context = context;
        this.themeResId = themeResId;
        this.layout = layout;
    }

    //能否返回键取消
    public DesignatesDialogBuilder setCancelable(Boolean cancelable) {
        this.cancelable = cancelable;
        return this;
    }

    public DesignatesDialogBuilder title(CharSequence title) {
        this.title = title;
        return this;
    }

    public DesignatesDialogBuilder listData(Map<String, Object> map, String money, QryTicket.Data.Ticketlist ticketlist, int position) {
        this.map = map;
        this.money = money;
        this.ticketlist = ticketlist;
        int position1 = position;
        return this;
    }

    public DesignatesDialogBuilder message(CharSequence message) {
        CharSequence message1 = message;
        return this;
    }

    public DesignatesDialogBuilder isboolean(boolean isCancelable) {
        this.isCancelable = isCancelable;
        return this;
    }

    public DesignatesDialogBuilder isboolean(boolean isCancelable, String stkcode) {
        this.stkcode = stkcode;
        this.isCancelable = isCancelable;
        return this;
    }

    public DesignatesDialogBuilder setSureOnClickListener(View.OnClickListener listener) {
        this.sureClickListener = listener;
        return this;
    }

    public DesignatesDialogBuilder setCancelOnClickListener(View.OnClickListener listener) {
        this.cancelClickListener = listener;
        return this;
    }

    private Dialog dialog = null;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @SuppressLint("SetTextI18n")
    public Dialog build() {
        dialog = new Dialog(context, themeResId);
        dialog.setCancelable(cancelable);
        dialog.addContentView(layout, new ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT));
        if (map != null && map.size() > 0) {
            setText(map.get("stkname").toString(), R.id.designates_name);
            //设置显不显示
            orderlimitqry = Integer.parseInt(map.get("orderlimitqry").toString());
        }

        TextView designates_dialog_plus = dialog.findViewById(R.id.designates_dialog_plus);
        designates_dialog_plus.setOnClickListener(this);
        TextView designates_dialog_minus_sign = dialog.findViewById(R.id.designates_dialog_minus_sign);
        designates_dialog_minus_sign.setOnClickListener(this);
        designates_dialog_text = dialog.findViewById(R.id.designates_dialog_text);
        target_profit76 = dialog.findViewById(R.id.target_profit76);
        target_profit20 = dialog.findViewById(R.id.target_profit20);
        target_profit40 = dialog.findViewById(R.id.target_profit40);
        target_profit60 = dialog.findViewById(R.id.target_profit60);
        target_profit80 = dialog.findViewById(R.id.target_profit80);
        target_profit100 = dialog.findViewById(R.id.target_profit100);

        dialog.findViewById(R.id.rule_image_webview).setOnClickListener(this);

        stop_loss76 = dialog.findViewById(R.id.stop_loss76);
        stop_loss20 = dialog.findViewById(R.id.stop_loss20);
        stop_loss30 = dialog.findViewById(R.id.stop_loss30);
        stop_loss40 = dialog.findViewById(R.id.stop_loss40);
        stop_loss50 = dialog.findViewById(R.id.stop_loss50);
        stop_loss60 = dialog.findViewById(R.id.stop_loss60);

        designates_dialog_text.setText("1");
        total_cost_text = dialog.findViewById(R.id.total_cost_text);
        LinearLayout select_coupons_linrea = dialog.findViewById(R.id.select_coupons_linrea);
        designates_dialog_wave = dialog.findViewById(R.id.designates_dialog_wave);
        select_coupons_text = dialog.findViewById(R.id.select_coupons_text);
        total_text = dialog.findViewById(R.id.total_text);
        TextView gold_entry_text = dialog.findViewById(R.id.gold_entry_text);
        SpannableString spannableString = new SpannableString("可用金额  " + money + "元");
        spannableString.setSpan(new ForegroundColorSpan(Color.parseColor("#FF6D64")), 4, spannableString.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        gold_entry_text.setText(spannableString);

        if (map != null && map.size() > 0) {
            SpannableString spannableString1 = new SpannableString("总    计  " + map.get("marginrate") + "元");
            spannableString1.setSpan(new ForegroundColorSpan(Color.parseColor("#FF6D64")), 6, spannableString1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            total_text.setText(spannableString1);

            SpannableString spannableString2 = new SpannableString("波动1个点赚" + map.get("holdfeerate") + "元");
            spannableString2.setSpan(new ForegroundColorSpan(Color.parseColor("#FF6D64")), 6, spannableString2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            designates_dialog_wave.setText(spannableString2);

            total_cost_text.setText("(手续费 " + map.get("closefeerate") + "元，平仓时收取)");

            profitpoints = Float.parseFloat(map.get("profitpoints").toString()) * 0.76 + "";
            losspoints = Float.parseFloat(map.get("losspoints").toString()) * 0.76 + "";
        } else {
            SpannableString spannableString1 = new SpannableString("总    计  0.00元");
            spannableString1.setSpan(new ForegroundColorSpan(Color.parseColor("#FF6D64")), 6, spannableString1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            total_text.setText(spannableString1);

            SpannableString spannableString2 = new SpannableString("波动1个点赚0.00元");
            spannableString2.setSpan(new ForegroundColorSpan(Color.parseColor("#FF6D64")), 6, spannableString2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            designates_dialog_wave.setText(spannableString2);

            total_cost_text.setText("(手续费 0.00元，平仓时收取)");

            profitpoints = 0 * 0.76 + "";
            losspoints = 0 * 0.76 + "";
        }

        if (ticketlist != null) {
            if (TextUtils.isEmpty(SPUtils.Companion.getInstance(context, "voucher").getString("voucher", "")) ||
                    !SPUtils.Companion.getInstance(context, "voucher").getString("voucher", "").equals("0")) {
                select_coupons_linrea.setVisibility(View.VISIBLE);
                String excount = "";
                if (Float.parseFloat(map.get("marginrate").toString()) == Float.parseFloat(ticketlist.getQprice())) {
                    if (Long.parseLong(ticketlist.getExcount()) >= 1) {
                        excount = ticketlist.getPzname() + "x" + ticketlist.getExcount() + "数量";
                        select_coupons_text.setText(excount);
                    } else {
                        excount = ticketlist.getPzname();
                        select_coupons_text.setText(excount);
                    }
                } else {
                    select_coupons_text.setText("0");
                }
            } else {
                select_coupons_text.setText("0");
            }
        } else {
            select_coupons_text.setText("0");
            select_coupons_linrea.setVisibility(View.GONE);
        }

        if (sureClickListener != null) {
            select_coupons_linrea.setOnClickListener(view -> {
                sureClickListener.onClick(view);
                dialog.dismiss();
            });
        }
        Button designates_dialog_button = dialog.findViewById(R.id.designates_dialog_button);

        target_profit76.setOnClickListener(this);
        target_profit20.setOnClickListener(this);
        target_profit40.setOnClickListener(this);
        target_profit60.setOnClickListener(this);
        target_profit80.setOnClickListener(this);
        target_profit100.setOnClickListener(this);
        stop_loss76.setOnClickListener(this);
        stop_loss20.setOnClickListener(this);
        stop_loss30.setOnClickListener(this);
        stop_loss40.setOnClickListener(this);
        stop_loss50.setOnClickListener(this);
        stop_loss60.setOnClickListener(this);


        //没有title时message变大
        if (!isValid(title)) {
            ((TextView) layout.findViewById(R.id.designates_name)).setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
        }

        if (isCancelable) {
            designates_dialog_button.setBackgroundColor(Color.parseColor("#35DF7E"));
            designates_dialog_button.setText("买跌");
        } else {
            designates_dialog_button.setBackgroundColor(Color.parseColor("#FF6D64"));
            designates_dialog_button.setText("买涨");
        }
        designates_dialog_button.setOnClickListener(v -> HttpALl.Companion.get().setLastPrice(stkcode, mHandler, context));
        if (cancelClickListener != null) {
            layout.findViewById(R.id.gold_entry_linter).setOnClickListener(view -> {
                cancelClickListener.onClick(view);
                dialog.dismiss();
            });
        }

        getType(type);

        dialog.setCancelable(true); //触摸屏幕与点Back键都失效。也无法监听onCancel
        dialog.setCanceledOnTouchOutside(true);// 触摸屏幕Dialog不消失，但点击Back键可取消
        //设置宽度
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = AppUtil.Companion.getInstance().Dispay((Activity) context)[0];
        dialog.getWindow().setAttributes(params);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        return dialog;
    }

    private void setText(CharSequence text, int id) {
        if (isValid(text)) {
            TextView textView = layout.findViewById(id);
            textView.setText(text);
            textView.setVisibility(View.VISIBLE);
        }
    }

    private boolean isValid(CharSequence text) {
        return text != null && !"".equals(text.toString().trim());
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1007:
                    QryStockNoName qryStockNoName = (QryStockNoName) msg.obj;
                    QryStockNoName.Data qtepricelist = qryStockNoName.getData().get(0);
                    String y = "";
                    if (ticketlist != null && !select_coupons_text.getText().toString().equals("0")) {
                        y = "Y";
                    } else {
                        y = "N";
                    }
                    if (map == null || map.size() <= 0) {
                        ToatUtils.Companion.showShort1(context, "您暂时不能下单，请查看网络");
                    } else {
                        if (Float.parseFloat(money) > 0) {
                            if (isCancelable) {
                                HttpALl.Companion.get().setopenGranaryProvideRelief("open",
                                        "",
                                        "",
                                        "", map.get("stkcode").toString(), "1",
                                        qtepricelist.getPrice(), orderlimit + "", losspoints, profitpoints, y, "3", mHandler, context);
                            } else {
                                HttpALl.Companion.get().setopenGranaryProvideRelief("open",
                                        "",
                                        "",
                                        "", map.get("stkcode").toString(), "2",
                                        qtepricelist.getPrice(), orderlimit + "", losspoints, profitpoints, y, "3", mHandler, context);
                            }
                        } else {
                            ToatUtils.Companion.showShort1(context, "您的账户资金不足，请充值");
                        }
                    }
                    break;
                case 1035:
                    ToatUtils.Companion.showShort1(context, "亲！下单已成功");
                    dialog.dismiss();
                    break;
            }
        }
    };

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @SuppressLint("SetTextI18n")
    @Override
    public void onClick(View v) {
        float profitpointsfloat = 0.00f;
        float losspointsfloat = 0.00f;
        String marginrate = "0.00";
        String closefeerate = "0.00";
        if (map != null && map.size() > 0) {
            profitpointsfloat = Float.parseFloat(map.get("profitpoints").toString());
            losspointsfloat = Float.parseFloat(map.get("losspoints").toString());
            marginrate = map.get("marginrate").toString();
            closefeerate = map.get("closefeerate").toString();
        }
        switch (v.getId()) {
            case R.id.target_profit76:
                profitpoints = profitpointsfloat * 0.76 + "";
                type = 11;
                break;
            case R.id.target_profit20:
                profitpoints = profitpointsfloat * 0.2 + "";
                type = 1;
                break;
            case R.id.target_profit40:
                profitpoints = profitpointsfloat * 0.4 + "";
                type = 2;
                break;
            case R.id.target_profit60:
                profitpoints = profitpointsfloat * 0.6 + "";
                type = 3;
                break;
            case R.id.target_profit80:
                profitpoints = profitpointsfloat * 0.8 + "";
                type = 4;
                break;
            case R.id.target_profit100:
                profitpoints = profitpointsfloat * 1 + "";
                type = 5;
                break;
            case R.id.stop_loss76:
                losspoints = losspointsfloat * 0.76 + "";
                type = 12;
                break;
            case R.id.stop_loss20:
                losspoints = losspointsfloat * 0.2 + "";
                type = 6;
                break;
            case R.id.stop_loss30:
                losspoints = losspointsfloat * 0.3 + "";
                type = 7;
                break;
            case R.id.stop_loss40:
                losspoints = losspointsfloat * 0.4 + "";
                type = 8;
                break;
            case R.id.stop_loss50:
                losspoints = losspointsfloat * 0.5 + "";
                type = 9;
                break;
            case R.id.stop_loss60:
                losspoints = losspointsfloat * 0.6 + "";
                type = 10;
                break;
            case R.id.rule_image_webview:
                Intent intent = new Intent(context, WebViewActivity.class);
                intent.putExtra("url", HttpImplements.Companion.get().getHttpS() + "order_rules.html");
                intent.putExtra("type", "0");
                context.startActivity(intent);
                break;
            case R.id.designates_dialog_plus:
                if (Float.parseFloat(money) >= Float.parseFloat(marginrate) * orderlimit) {
                    if (select_coupons_text.getText().toString().equals("0")) {
                        if (orderlimit < orderlimitqry) {
                            orderlimit++;
                            SpannableString spannableString1 = new SpannableString("总    计  " + Float.parseFloat(marginrate) * orderlimit + "元");
                            spannableString1.setSpan(new ForegroundColorSpan(Color.parseColor("#FF6D64")), 6, spannableString1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            total_text.setText(spannableString1);
                            designates_dialog_text.setText(orderlimit + "");
                            total_cost_text.setText("(手续费 " + (Float.parseFloat(closefeerate) * orderlimit) + "元，平仓时收取)");

                            SpannableString spannableString2 = new SpannableString("波动1个点赚" + (Float.parseFloat(map.get("holdfeerate").toString()) * orderlimit) + "元");
                            spannableString2.setSpan(new ForegroundColorSpan(Color.parseColor("#FF6D64")), 6, spannableString2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                            designates_dialog_wave.setText(spannableString2);
                        } else {
                            ToatUtils.Companion.showShort1(context, "单笔数量已达到上线");

                        }
                    } else {
                        ToatUtils.Companion.showShort1(context, "您在使用体验券，只能用一张买一手哦");
                    }
                } else {
                    ToatUtils.Companion.showShort1(context, "您的余额不足，请先充值");
                }
                break;
            case R.id.designates_dialog_minus_sign:
                if (select_coupons_text.getText().toString().equals("0")) {
                    if (orderlimit > 1) {
                        orderlimit--;
                        SpannableString spannableString1 = new SpannableString("总    计  " + Float.parseFloat(marginrate) * orderlimit + "元");
                        spannableString1.setSpan(new ForegroundColorSpan(Color.parseColor("#FF6D64")), 6, spannableString1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        total_text.setText(spannableString1);
                        designates_dialog_text.setText(orderlimit + "");
                        total_cost_text.setText("(手续费 " + (Float.parseFloat(closefeerate) * orderlimit) + "元，平仓时收取)");

                        SpannableString spannableString2 = new SpannableString("波动1个点赚" + (Float.parseFloat(map.get("holdfeerate").toString()) * orderlimit) + "元");
                        spannableString2.setSpan(new ForegroundColorSpan(Color.parseColor("#FF6D64")), 6, spannableString2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                        designates_dialog_wave.setText(spannableString2);
                    } else {
                        ToatUtils.Companion.showShort1(context, "单笔数量过低");
                    }
                } else {
                    ToatUtils.Companion.showShort1(context, "您在使用体验券，只能用一张买一手哦");
                }
                break;
        }
        getType(type);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    private void getType(int type) {
        switch (type) {
            case 0:
                target_profit76.setBackgroundResource(R.drawable.shape_c20_red);
                stop_loss76.setBackgroundResource(R.drawable.shape_corner_green);
                target_profit76.setTextColor(Color.WHITE);
                stop_loss76.setTextColor(Color.WHITE);

                target_profit20.setBackgroundResource(R.drawable.shape_corner_red_while);
                target_profit40.setBackgroundResource(R.drawable.shape_corner_red_while);
                target_profit60.setBackgroundResource(R.drawable.shape_corner_red_while);
                target_profit80.setBackgroundResource(R.drawable.shape_corner_red_while);
                target_profit100.setBackgroundResource(R.drawable.shape_corner_red_while);
                stop_loss20.setBackgroundResource(R.drawable.shape_c5_green_whlie);
                stop_loss30.setBackgroundResource(R.drawable.shape_c5_green_whlie);
                stop_loss40.setBackgroundResource(R.drawable.shape_c5_green_whlie);
                stop_loss50.setBackgroundResource(R.drawable.shape_c5_green_whlie);
                stop_loss60.setBackgroundResource(R.drawable.shape_c5_green_whlie);

                break;
            case 1:
                target_profit76.setTextColor(Color.parseColor("#333333"));
                target_profit76.setBackgroundResource(R.drawable.shape_corner_red_while);
                target_profit20.setBackgroundResource(R.drawable.shape_c20_red);
                target_profit40.setBackgroundResource(R.drawable.shape_corner_red_while);
                target_profit60.setBackgroundResource(R.drawable.shape_corner_red_while);
                target_profit80.setBackgroundResource(R.drawable.shape_corner_red_while);
                target_profit100.setBackgroundResource(R.drawable.shape_corner_red_while);
                break;
            case 2:
                target_profit76.setTextColor(Color.parseColor("#333333"));
                target_profit76.setBackgroundResource(R.drawable.shape_corner_red_while);
                target_profit20.setBackgroundResource(R.drawable.shape_corner_red_while);
                target_profit40.setBackgroundResource(R.drawable.shape_c20_red);
                target_profit60.setBackgroundResource(R.drawable.shape_corner_red_while);
                target_profit80.setBackgroundResource(R.drawable.shape_corner_red_while);
                target_profit100.setBackgroundResource(R.drawable.shape_corner_red_while);
                break;
            case 3:
                target_profit76.setTextColor(Color.parseColor("#333333"));
                target_profit76.setBackgroundResource(R.drawable.shape_corner_red_while);
                target_profit20.setBackgroundResource(R.drawable.shape_corner_red_while);
                target_profit40.setBackgroundResource(R.drawable.shape_corner_red_while);
                target_profit60.setBackgroundResource(R.drawable.shape_c20_red);
                target_profit80.setBackgroundResource(R.drawable.shape_corner_red_while);
                target_profit100.setBackgroundResource(R.drawable.shape_corner_red_while);
                break;
            case 4:
                target_profit76.setTextColor(Color.parseColor("#333333"));
                target_profit76.setBackgroundResource(R.drawable.shape_corner_red_while);
                target_profit20.setBackgroundResource(R.drawable.shape_corner_red_while);
                target_profit40.setBackgroundResource(R.drawable.shape_corner_red_while);
                target_profit60.setBackgroundResource(R.drawable.shape_corner_red_while);
                target_profit80.setBackgroundResource(R.drawable.shape_c20_red);
                target_profit100.setBackgroundResource(R.drawable.shape_corner_red_while);
                break;
            case 5:
                target_profit76.setTextColor(Color.parseColor("#333333"));
                target_profit76.setBackgroundResource(R.drawable.shape_corner_red_while);
                target_profit20.setBackgroundResource(R.drawable.shape_corner_red_while);
                target_profit40.setBackgroundResource(R.drawable.shape_corner_red_while);
                target_profit60.setBackgroundResource(R.drawable.shape_corner_red_while);
                target_profit80.setBackgroundResource(R.drawable.shape_corner_red_while);
                target_profit100.setBackgroundResource(R.drawable.shape_c20_red);
                break;
            case 6:
                stop_loss76.setTextColor(Color.parseColor("#333333"));
                stop_loss76.setBackgroundResource(R.drawable.shape_c5_green_whlie);
                stop_loss20.setBackgroundResource(R.drawable.shape_corner_green);
                stop_loss30.setBackgroundResource(R.drawable.shape_c5_green_whlie);
                stop_loss40.setBackgroundResource(R.drawable.shape_c5_green_whlie);
                stop_loss50.setBackgroundResource(R.drawable.shape_c5_green_whlie);
                stop_loss60.setBackgroundResource(R.drawable.shape_c5_green_whlie);
                break;
            case 7:
                stop_loss76.setTextColor(Color.parseColor("#333333"));
                stop_loss76.setBackgroundResource(R.drawable.shape_c5_green_whlie);
                stop_loss20.setBackgroundResource(R.drawable.shape_c5_green_whlie);
                stop_loss30.setBackgroundResource(R.drawable.shape_corner_green);
                stop_loss40.setBackgroundResource(R.drawable.shape_c5_green_whlie);
                stop_loss50.setBackgroundResource(R.drawable.shape_c5_green_whlie);
                stop_loss60.setBackgroundResource(R.drawable.shape_c5_green_whlie);
                break;
            case 8:
                stop_loss76.setTextColor(Color.parseColor("#333333"));
                stop_loss76.setBackgroundResource(R.drawable.shape_c5_green_whlie);
                stop_loss20.setBackgroundResource(R.drawable.shape_c5_green_whlie);
                stop_loss30.setBackgroundResource(R.drawable.shape_c5_green_whlie);
                stop_loss40.setBackgroundResource(R.drawable.shape_corner_green);
                stop_loss50.setBackgroundResource(R.drawable.shape_c5_green_whlie);
                stop_loss60.setBackgroundResource(R.drawable.shape_c5_green_whlie);
                break;
            case 9:
                stop_loss76.setTextColor(Color.parseColor("#333333"));
                stop_loss76.setBackgroundResource(R.drawable.shape_c5_green_whlie);
                stop_loss20.setBackgroundResource(R.drawable.shape_c5_green_whlie);
                stop_loss30.setBackgroundResource(R.drawable.shape_c5_green_whlie);
                stop_loss40.setBackgroundResource(R.drawable.shape_c5_green_whlie);
                stop_loss50.setBackgroundResource(R.drawable.shape_corner_green);
                stop_loss60.setBackgroundResource(R.drawable.shape_c5_green_whlie);
                break;
            case 10:
                stop_loss76.setTextColor(Color.parseColor("#333333"));
                stop_loss76.setBackgroundResource(R.drawable.shape_c5_green_whlie);
                stop_loss20.setBackgroundResource(R.drawable.shape_c5_green_whlie);
                stop_loss30.setBackgroundResource(R.drawable.shape_c5_green_whlie);
                stop_loss40.setBackgroundResource(R.drawable.shape_c5_green_whlie);
                stop_loss50.setBackgroundResource(R.drawable.shape_c5_green_whlie);
                stop_loss60.setBackgroundResource(R.drawable.shape_corner_green);
                break;
            case 11:
                target_profit76.setTextColor(Color.parseColor("#FFFFFF"));
                target_profit76.setBackgroundResource(R.drawable.shape_c20_red);
                target_profit20.setBackgroundResource(R.drawable.shape_corner_red_while);
                target_profit40.setBackgroundResource(R.drawable.shape_corner_red_while);
                target_profit60.setBackgroundResource(R.drawable.shape_corner_red_while);
                target_profit80.setBackgroundResource(R.drawable.shape_corner_red_while);
                target_profit100.setBackgroundResource(R.drawable.shape_corner_red_while);
                break;
            case 12:
                stop_loss76.setTextColor(Color.parseColor("#FFFFFF"));
                stop_loss76.setBackgroundResource(R.drawable.shape_corner_green);
                stop_loss20.setBackgroundResource(R.drawable.shape_c5_green_whlie);
                stop_loss30.setBackgroundResource(R.drawable.shape_c5_green_whlie);
                stop_loss40.setBackgroundResource(R.drawable.shape_c5_green_whlie);
                stop_loss50.setBackgroundResource(R.drawable.shape_c5_green_whlie);
                stop_loss60.setBackgroundResource(R.drawable.shape_c5_green_whlie);
                break;
        }

    }
}

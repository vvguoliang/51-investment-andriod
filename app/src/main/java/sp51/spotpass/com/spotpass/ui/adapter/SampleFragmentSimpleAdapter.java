package sp51.spotpass.com.spotpass.ui.adapter;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import sp51.spotpass.com.spotpass.ui.activity.DesignatesActivity;
import sp51.spotpass.com.spotpass.ui.activity.RechargeActivity;
import sp51.spotpass.com.spotpass.ui.baseEntity.QryStockNoName;
import sp51.spotpass.com.spotpass.ui.http.HttpALl;
import sp51.spotpass.com.spotpass.ui.utils.AppUtil;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sp51.spotpass.com.spotpass.R;
import sp51.spotpass.com.spotpass.ui.recyclerview.recyclerview.BaseRecyclerAdapter;
import sp51.spotpass.com.spotpass.ui.utils.SPUtils;
import sp51.spotpass.com.spotpass.ui.utils.ToatUtils;
import sp51.spotpass.com.spotpass.ui.utils.Util;
import sp51.spotpass.com.spotpass.ui.view.Dialog.DesignatesDialogBuilder;
import sp51.spotpass.com.spotpass.ui.view.Dialog.PubilcDialogBuilder;

@SuppressWarnings("EqualsBetweenInconvertibleTypes")
public class SampleFragmentSimpleAdapter extends BaseRecyclerAdapter<SampleFragmentSimpleAdapter.SimpleAdapterViewHolder> implements AdapterView.OnItemClickListener {
    private List<List<Map<String, Object>>> listData;
    private List<Map<String, Object>> list;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private List<Map<String, String>> mapList;
    private int type = 0;

    private Context context;

    private int getType = 0;

    private boolean memo = true;

    private int position_button = 0;

    private RecyclerView recycler_view_test_rv;

    private List<TransactionViewSimpleAdapter> transactionViewSimpleAdapters = new ArrayList<>();

    public SampleFragmentSimpleAdapter(Context context) {
        this.context = context;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(SimpleAdapterViewHolder holder, @SuppressLint("RecyclerView") int position, boolean isItem) {
        if (type == 1) {
            holder.transaction_linear.setVisibility(View.GONE);
            holder.transaction_linear_no_data.setVisibility(View.VISIBLE);
            holder.history_data.setVisibility(View.GONE);
            holder.transaction_relative.setVisibility(View.GONE);

            if (list != null && list.size() > 0) {
                if (list.get(position).get("bsflag").equals("1")) {
                    holder.single_text.setText("买涨单");
                    holder.single_text.setTextColor(Color.parseColor("#FF6D64"));
                } else {
                    holder.single_text.setText("买跌单");
                    holder.single_text.setTextColor(Color.parseColor("#028845"));
                }
                holder.single_target_profit_text.setText("止盈价" + list.get(position).get("profitprice"));
                holder.single_stop_loss_text.setText("止损价" + list.get(position).get("stopprice"));
                holder.single_time_text.setText(list.get(position).get("opentime").toString());
                holder.single_name_text.setText(list.get(position).get("stkname").toString());
                holder.single_hand_text.setText(list.get(position).get("holdqty").toString() + "手");
                if (list.get(position).get("tyqflag").toString().equals("Y")) {
                    holder.single_rate_text.setVisibility(View.GONE);
                    holder.single_rate_image.setVisibility(View.VISIBLE);
                } else {
                    holder.single_rate_image.setVisibility(View.GONE);
                    holder.single_rate_text.setVisibility(View.VISIBLE);
                    holder.single_rate_text.setText("/" + list.get(position).get("holdmargin").toString() + "元");
                }
                if (Float.parseFloat(list.get(position).get("current_price").toString()) > 0) {
                    holder.single_rate_text1.setTextColor(Color.parseColor("#FF6D64"));
                } else {
                    holder.single_rate_text1.setTextColor(Color.parseColor("#028845"));
                }
                holder.single_rate_text1.setText(list.get(position).get("current_price").toString());
            }
        } else if (type == 2) {
            holder.transaction_linear.setVisibility(View.GONE);
            holder.history_data.setVisibility(View.GONE);
            holder.transaction_linear_no_data.setVisibility(View.GONE);
            holder.transaction_relative.setVisibility(View.VISIBLE);

            if (list.get(position).get("type").equals("1")) {
                holder.register_linear1.setVisibility(View.GONE);
                holder.register_linear2.setVisibility(View.VISIBLE);
                holder.register_linear3.setVisibility(View.GONE);
                holder.register_linear4.setVisibility(View.GONE);
                holder.register_button.setVisibility(View.VISIBLE);
                holder.register_expected_cost1.setText("预计花费");

                String bsflag = "";
                if (list.get(position).get("bsflag").toString().equals("1")) {
                    holder.register_buy_text.setBackgroundResource(R.drawable.shape_right_while_red);
                    bsflag = context.getString(R.string.textView_buy_more);
                } else {
                    bsflag = context.getString(R.string.textView_buy_fall);
                    holder.register_buy_text.setBackgroundResource(R.drawable.shape_right_green_while);
                }
                holder.register_buy_text.setText(bsflag);
                holder.register_name.setText(list.get(position).get("qtecodeName").toString());
                holder.register_name2.setText(list.get(position).get("orderprice").toString());
//                holder.orderprice_text.setText(list.get(position).get("orderprice").toString());

                SpannableString style = new SpannableString("止盈" + Double.parseDouble(list.get(position).get("profitpoints").toString()) * 100 + "%");
                style.setSpan(new ForegroundColorSpan(Color.RED), 2, style.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                holder.profitpoints_text.setText(style);

                style = new SpannableString("止损" + Double.parseDouble(list.get(position).get("losspoints").toString()) * 100 + "%");
                style.setSpan(new ForegroundColorSpan(Color.GREEN), 2, style.length(), Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
                holder.losspoints_text.setText(style);
                holder.register_buy.setText(list.get(position).get("orderqty").toString() + "手");
                holder.register_expected_cost.setText(list.get(position).get("price").toString() + "元");
                holder.register_time.setText(list.get(position).get("createdTime").toString());
                holder.register_study.setText("正在挂单中...");
            } else {
                holder.register_linear1.setVisibility(View.VISIBLE);
                holder.register_linear2.setVisibility(View.GONE);
                holder.register_linear3.setVisibility(View.VISIBLE);
                holder.register_linear4.setVisibility(View.VISIBLE);
                holder.register_button.setVisibility(View.GONE);
                holder.register_expected_cost1.setText("花        费");
            }

//            if (list != null && list.size() > 0) {
//                int bsflag = (int) list.get(position).get("bsflag");
//                if (bsflag == 1) {
//                    holder.register_buy_text.setText("买涨");
//                    holder.register_buy_text.setBackgroundResource(R.drawable.shape_right_while_red);
//                } else {
//                    holder.register_buy_text.setText("买跌");
//                    holder.register_buy_text.setBackgroundResource(R.drawable.shape_right_green_while);
//                }
//                holder.register_name.setText(list.get(position).get("qtecodeName").toString());
//                holder.register_expected_cost.setText(list.get(position).get("orderprice").toString());
//                holder.register_time.setText(list.get(position).get("createdTime").toString());
//                if (!list.get(position).containsKey("id")) {
//                    holder.register_study.setText("历史挂单");
//                    holder.register_button.setVisibility(View.GONE);
//                } else {
//                    holder.register_study.setText("正在挂单");
//                    holder.register_button.setVisibility(View.VISIBLE);
//                }
//            }
        } else if (type == 3) {
            holder.history_data.setVisibility(View.VISIBLE);
            holder.transaction_linear.setVisibility(View.GONE);
            holder.transaction_linear_no_data.setVisibility(View.GONE);
            holder.transaction_relative.setVisibility(View.GONE);

        } else {
            this.getType = position;
            holder.transaction_linear.setVisibility(View.VISIBLE);
            holder.transaction_linear_no_data.setVisibility(View.GONE);
            holder.history_data.setVisibility(View.GONE);
            holder.transaction_relative.setVisibility(View.GONE);
            holder.sample_name.setText(list.get(position).get("name").toString());
            String[] trdTime = list.get(position).get("trdTime").toString().split("#");
            String[] trdTime1 = list.get(position).get("trdTime").toString().split(",");
            holder.qryQuoter_text.setText(trdTime[0] + "到次日" + trdTime[1].substring(0, 5));
            memo = list.get(position).get("memo").toString().equals("交易日");
            if (!memo) {
                holder.memo_text.setText("休");
            } else {
                holder.memo_text.setText(list.get(position).get("memo").toString());
            }
//            getRecycler_view_test_rv(position);
//            transactionViewSimpleAdapters.get(position).setData(listData.get(position));
            DecimalFormat df = new DecimalFormat("#####0.0");
            if (mapList != null && mapList.size() > 0) {
                if (Float.parseFloat(mapList.get(position).get("price")) < Float.parseFloat(mapList.get(position).get("lastPrice"))) {
                    holder.qey_text.setTextColor(Color.parseColor("#55C774"));
                } else {
                    holder.qey_text.setTextColor(Color.parseColor("#FF6D64"));
                }
                holder.qey_text.setText(df.format(Double.parseDouble(mapList.get(position).get("price"))) + "       " + df.format(Double.parseDouble(mapList.get(position).get("floatPercent"))) + "%");
            }
        }
    }

//    @SuppressLint("SetTextI18n")
//    @Override
//    public void onBindViewHolder(@NonNull SimpleAdapterViewHolder holder, int position,
//                                 @NonNull List<Object> payloads) {
//        super.onBindViewHolder(holder, position, payloads);
//        if (type == 0) {
//            if (!payloads.isEmpty()) {
//                if (mapList != null && mapList.size() > 0) {
//                    if (ConvertUtil.Companion.get().convertToFloat(mapList.get(position).get("floatPrice"), 0) <= 0 ||
//                            ConvertUtil.Companion.get().convertToFloat(mapList.get(position).get("floatPercent"), 0) <= 0) {
//                        holder.qey_text.setTextColor(Color.parseColor("#55C774"));
//                        holder.qey_text.setText(mapList.get(position).get("price") + " " + mapList.get(position).get("floatPrice") + " " + mapList.get(position).get("floatPercent") + "%");
//                    } else {
//                        holder.qey_text.setText(mapList.get(position).get("price") + " +" + mapList.get(position).get("floatPrice") + " +" + mapList.get(position).get("floatPercent") + "%");
//                        holder.qey_text.setTextColor(Color.parseColor("#FF6D64"));
//                    }
//                }
//            } else {
//                onBindViewHolder(holder, position);
//            }
//        } else {
//            onBindViewHolder(holder, position);
//        }
//    }

    @Override
    public int getAdapterItemViewType(int position) {
        return 0;
    }

    @Override
    public int getAdapterItemCount() {
        if (list != null && list.size() > 0)
            return list.size();
        else
            return 0;
    }

    @Override
    public SimpleAdapterViewHolder getViewHolder(View view) {
        return new SimpleAdapterViewHolder(view, false);
    }

    public void setData(int type, List<Map<String, Object>>
            list, List<List<Map<String, Object>>> listData) {
        if (type == 0) {
            List<Map<String, Object>> list1 = new ArrayList<>();
            for (int k = 0; list.size() > k; k++) {
                for (int j = 0; listData.size() > j; j++) {
                    if (list.get(k).get("qtecode").equals(listData.get(j).get(0).get("qtecode"))) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("memo", list.get(k).get("memo"));
                        map.put("trdTime", list.get(k).get("trdTime"));
                        map.put("setTime", list.get(k).get("setTime"));
                        map.put("exchange_id", list.get(k).get("exchange_id"));
                        map.put("name", list.get(k).get("name"));
                        map.put("qtecode", list.get(k).get("qtecode"));
                        list1.add(map);
                    }
                }
            }
            this.listData = listData;
            this.list = list1;
        } else {
            this.list = list;
        }
        this.type = type;
        notifyDataSetChanged();
    }

    public void getshijian(List<Map<String, String>> mapList) {
//        this.mapList = new ArrayList<>();
//        for (int i = 0; mapList.size() > i; i++) {
//            if (Math.abs(Float.parseFloat(mapList.get(i).get("floatPercent"))) > 0 && Math.abs(Float.parseFloat(mapList.get(i).get("floatPrice"))) > 0) {
//                Map<String, String> map = new HashMap<>();
//                map.put("floatPercent", mapList.get(i).get("floatPercent"));
//                map.put("floatPrice", mapList.get(i).get("floatPrice"));
//                map.put("price", mapList.get(i).get("price"));
//                map.put("quote", mapList.get(i).get("quote"));
//                this.mapList.add(map);
//            }
//        }
        this.mapList = mapList;
        notifyDataSetChanged();
    }

    @Override
    public SimpleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType,
                                                      boolean isItem) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.view_sample_fragment_simple_adapter, parent, false);
        return new SimpleAdapterViewHolder(v, true);
    }

    public void insert(Map<String, Object> person, int position) {
        insert(list, person, position);
    }

    public void remove(int position) {
        remove(list, position);
    }

    public void clear() {
        clear(list);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    public class SimpleAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LinearLayout profit_list_liner;
        LinearLayout transaction_linear;
        RelativeLayout transaction_linear1;
        RelativeLayout transaction_linear_no_data;
        RelativeLayout transaction_relative;
        TextView sample_name;
        TextView qryQuoter_text;
        TextView memo_text;
        TextView qey_text;

        TextView single_text;
        TextView single_name_text;
        TextView single_hand_text;
        TextView single_rate_text;
        Button single_position_button;
        TextView single_target_profit_text;
        TextView single_stop_loss_text;
        TextView single_time_text;
        TextView single_rate_text1;
        ImageView single_rate_image;

        TextView register_name;
        TextView register_buy_text;
        TextView register_expected_cost;
        TextView register_time;
        TextView register_study;
        Button register_button;

        RelativeLayout history_data;
        TextView history_buy;
        TextView history_teime;
        TextView history_name;
        TextView history_num;
        TextView history_money;

        TextView register_name2;
        TextView orderprice_text;
        TextView profitpoints_text;
        TextView losspoints_text;
        TextView register_buy;

        LinearLayout register_linear1;
        LinearLayout register_linear2;
        LinearLayout register_linear3;
        LinearLayout register_linear4;
        TextView register_expected_cost1;


        SimpleAdapterViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                profit_list_liner = itemView.findViewById(R.id.profit_list_liner);
                profit_list_liner.setOnClickListener(this);
                transaction_linear = itemView.findViewById(R.id.transaction_linear);
                transaction_linear_no_data = itemView.findViewById(R.id.transaction_linear_no_data);
                transaction_relative = itemView.findViewById(R.id.transaction_relative);
                transaction_linear1 = itemView.findViewById(R.id.transaction_linear1);
                transaction_linear1.setOnClickListener(this);
                recycler_view_test_rv = itemView.findViewById(R.id.recycler_view_test_rv);
                qryQuoter_text = itemView.findViewById(R.id.qryQuoter_text);
                memo_text = itemView.findViewById(R.id.memo_text);
                qey_text = itemView.findViewById(R.id.qey_text);
                sample_name = itemView.findViewById(R.id.sample_name);
                single_rate_text1 = itemView.findViewById(R.id.single_rate_text1);
//                itemView.findViewById(R.id.transaction_buy_one).setOnClickListener(this);
//                itemView.findViewById(R.id.transaction_buy_and_fall).setOnClickListener(this);
//                itemView.findViewById(R.id.transaction_buy_register).setOnClickListener(this);


                register_name = itemView.findViewById(R.id.register_name);
                register_buy_text = itemView.findViewById(R.id.register_buy_text);
                register_expected_cost = itemView.findViewById(R.id.register_expected_cost);
                register_time = itemView.findViewById(R.id.register_time);
                register_study = itemView.findViewById(R.id.register_study);
                register_button = itemView.findViewById(R.id.register_button);
                register_button.setOnClickListener(this);

                single_text = itemView.findViewById(R.id.single_text);
                single_name_text = itemView.findViewById(R.id.single_name_text);
                single_hand_text = itemView.findViewById(R.id.single_hand_text);
                single_rate_text = itemView.findViewById(R.id.single_rate_text);
                single_rate_image = itemView.findViewById(R.id.single_rate_image);
                single_position_button = itemView.findViewById(R.id.single_position_button);
                single_target_profit_text = itemView.findViewById(R.id.single_target_profit_text);
                single_stop_loss_text = itemView.findViewById(R.id.single_stop_loss_text);
                single_time_text = itemView.findViewById(R.id.single_time_text);

                single_position_button.setOnClickListener(this);

                history_data = itemView.findViewById(R.id.history_data);
                history_buy = itemView.findViewById(R.id.history_buy);
                history_teime = itemView.findViewById(R.id.history_teime);
                history_name = itemView.findViewById(R.id.history_name);
                history_num = itemView.findViewById(R.id.history_num);
                history_money = itemView.findViewById(R.id.history_money);


                register_linear1 = itemView.findViewById(R.id.register_linear1);
                register_linear2 = itemView.findViewById(R.id.register_linear2);
                register_linear3 = itemView.findViewById(R.id.register_linear3);
                register_linear4 = itemView.findViewById(R.id.register_linear4);
                register_expected_cost1 = itemView.findViewById(R.id.register_expected_cost1);
                register_name2 = itemView.findViewById(R.id.register_name2);
                orderprice_text = itemView.findViewById(R.id.orderprice_text);
                profitpoints_text = itemView.findViewById(R.id.profitpoints_text);
                losspoints_text = itemView.findViewById(R.id.losspoints_text);
                register_buy = itemView.findViewById(R.id.register_buy);
            }
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onClick(View v) {
            final Intent[] intent = {null};
            switch (v.getId()) {
                case R.id.transaction_buy_one://买多
                    if (TextUtils.isEmpty(SPUtils.Companion.getInstance(context, "Authorization").getString("Authorization"))) {
                        Util.INSTANCE.registerA1(context);
                    } else if (!AppUtil.Companion.getInstance().isFastDoubleClick()) {
                        new DesignatesDialogBuilder(context).title("琴童46780")
                                .isboolean(false)
                                .setCancelOnClickListener(v1 -> {
                                    intent[0] = new Intent(context, RechargeActivity.class);
                                    intent[0].putExtra("type", 0);
                                    context.startActivity(intent[0]);
                                })
                                .setSureOnClickListener(v12 -> Toast.makeText(context, "去下单", Toast.LENGTH_LONG).show())
                                .build().show();
                    }
                    break;
                case R.id.transaction_buy_and_fall://卖空
                    if (TextUtils.isEmpty(SPUtils.Companion.getInstance(context, "Authorization").getString("Authorization"))) {
                        Util.INSTANCE.registerA1(context);
                    } else if (!AppUtil.Companion.getInstance().isFastDoubleClick()) {
                        new DesignatesDialogBuilder(context).title("琴童46780")
                                .isboolean(true)
                                .setCancelOnClickListener(v1 -> {
                                    intent[0] = new Intent(context, RechargeActivity.class);
                                    intent[0].putExtra("type", 0);
                                    context.startActivity(intent[0]);
                                })
                                .setSureOnClickListener(v12 -> Toast.makeText(context, "去下单", Toast.LENGTH_LONG).show())
                                .build().show();
                    }
                    break;
                case R.id.transaction_buy_register:
                    if (mapList != null && mapList.size() > 0 && list != null && list.size() > 0) {
                        Intent intent1 = new Intent(context, DesignatesActivity.class);
                        intent1.putExtra("stkcode", list.get(getAdapterPosition()).get("qtecode").toString());
                        intent1.putExtra("quoteName", list.get(getAdapterPosition()).get("name").toString());
                        intent1.putExtra("price", mapList.get(getAdapterPosition()).get("price"));
                        context.startActivity(intent1);
                    }
                    break;
                case R.id.profit_list_liner:
                case R.id.register_button:
                case R.id.transaction_linear1:
                    if (memo) {
                        if (mOnItemClickListener != null) {
                            //注意这里使用getTag方法获取数据
                            mOnItemClickListener.onItemClick(v, type, getAdapterPosition());
                        }
                    } else {
                        ToatUtils.Companion.showShort1(context, "今天不是交易日，亲，休息一下");
                    }
                    break;
                case R.id.single_position_button:

                    new PubilcDialogBuilder(context).title("确定平仓抛出？").cancelText("取消").sureText("确定").message("").setCancelOnClickListener(v13 -> {
                    }).setSureOnClickListener(v14 -> {
                        position_button = getAdapterPosition() - 1;
                        HttpALl.Companion.get().setLastPrice(list.get(getAdapterPosition() - 1).get("stkcode").toString().substring(0, 3), mHandler, context);
                    }).build().show();
                    break;
            }
        }
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {

        @SuppressLint("SetTextI18n")
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1007:
                    QryStockNoName qryStockNoName = (QryStockNoName) msg.obj;
                    String bsflag;
                    if (list.get(position_button).get("bsflag").toString().equals("1")) {
                        bsflag = "2";
                    } else {
                        bsflag = "1";
                    }
                    HttpALl.Companion.get().setClose("", "", "",
                            list.get(position_button).get("stkcode").toString(), bsflag, list.get(position_button).get("holdno").toString(),
                            qryStockNoName.getData().get(0).getPrice(), list.get(position_button).get("holdqty").toString(),
                            list.get(position_button).get("tyqflag").toString(),
                            "3", mHandler, context);
                    break;
                case 1000:
                    ToatUtils.Companion.showShort1(context, "已平仓");
                    break;
            }
        }
    };

    private void getRecycler_view_test_rv(int position) {
        recycler_view_test_rv.setHasFixedSize(false);
        recycler_view_test_rv.setTag(position);
        transactionViewSimpleAdapters.add(position, new TransactionViewSimpleAdapter(context));
        //设置布局管理器
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recycler_view_test_rv.setLayoutManager(linearLayoutManager);
        recycler_view_test_rv.setAdapter(transactionViewSimpleAdapters.get(position));
        transactionViewSimpleAdapters.get(position).setOnItemClickListener((view1, data) -> {
            if (view1.getTag() == listData.get(position).get(data).get("stkname").toString()) {
                transactionViewSimpleAdapters.get(position).getTransactionView(data, view1.getTag().toString());
            }
        });
    }


    public Map<String, Object> getItem(int position) {
        if (position < listData.size())
            return list.get(position);
        else
            return null;
    }

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int type, int data);

    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

}
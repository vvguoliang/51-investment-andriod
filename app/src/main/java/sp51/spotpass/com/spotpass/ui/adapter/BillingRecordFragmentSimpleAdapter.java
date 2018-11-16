package sp51.spotpass.com.spotpass.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import sp51.spotpass.com.spotpass.R;
import sp51.spotpass.com.spotpass.ui.recyclerview.recyclerview.BaseRecyclerAdapter;

import static java.lang.Integer.parseInt;

public class BillingRecordFragmentSimpleAdapter extends BaseRecyclerAdapter<BillingRecordFragmentSimpleAdapter.SimpleAdapterViewHolder> {
    private List<Map<String, Object>> list;
    private int type = 0;
    private Context context;

    public BillingRecordFragmentSimpleAdapter(Context context) {
        this.context = context;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(SimpleAdapterViewHolder holder, int position, boolean isItem) {
        if (type == 1) {
            holder.transaction_linear1.setVisibility(View.VISIBLE);
            holder.transaction_linear2.setVisibility(View.GONE);
            holder.transaction_linear3.setVisibility(View.GONE);

            holder.billing_name1.setText(list.get(position).get("name").toString());
            String[] time = list.get(position).get("time").toString().split(" ");
            holder.billing_bank1.setText(time[1]);
            if (Float.parseFloat(list.get(position).get("closeprofit").toString()) > 0) {
                holder.billing_money1.setTextColor(Color.parseColor("#FF6D64"));
            } else {
                holder.billing_money1.setTextColor(Color.parseColor("#028845"));
            }
            holder.billing_money1.setText(list.get(position).get("closeprofit").toString());
            String bsflag;
            if (list.get(position).get("bsflag").toString().equals("1")) {
                bsflag = "买涨";
                holder.billing_bank5.setTextColor(Color.parseColor("#FF6D64"));
            } else {
                bsflag = "买跌";
                holder.billing_bank5.setTextColor(Color.parseColor("#028845"));
            }
            holder.billing_bank5.setText(bsflag);
            holder.billing_name4.setText(list.get(position).get("orderqty") + "手");
        } else if (type == 2) {
            holder.transaction_linear1.setVisibility(View.GONE);
            holder.transaction_linear2.setVisibility(View.VISIBLE);
            holder.transaction_linear3.setVisibility(View.GONE);

            String applystate = list.get(position).get("applystate").toString();
            String applystate1 = "";
            if (applystate.equals("1")) {
                applystate1 = "预约中...";
            } else if (applystate.equals("2")) {
                applystate1 = "已提现";
            } else if (applystate.equals("3")) {
                applystate1 = "提现失败";
            } else if (applystate.equals("4")) {
                applystate1 = "驳回";
            }
            holder.billing_name2.setText(applystate1);
            holder.billing_bank2.setText("提现到" + list.get(position).get("usrname").toString().substring(0, 1) + "**的银行卡");
            holder.billing_money2.setText("-" + list.get(position).get("paycapital").toString());
            holder.billing_tiem2.setText(list.get(position).get("applytime").toString());
        } else {
            holder.transaction_linear1.setVisibility(View.GONE);
            holder.transaction_linear2.setVisibility(View.GONE);
            holder.transaction_linear3.setVisibility(View.VISIBLE);

            String applystate = list.get(position).get("orderstatus").toString();
            String applystate1 = "";
            if (applystate.equals("1")) {
                applystate1 = "未支付";
            } else if (applystate.equals("2")) {
                applystate1 = "已支付，自动入金";
            } else if (applystate.equals("3")) {
                applystate1 = "支付失败";
            } else if (applystate.equals("4")) {
                applystate1 = "已支付，未入金";
            } else if (applystate.equals("5")) {
                applystate1 = "已支付，手动入金";
            }
//            map["applycapital"] = index.applycapital
//            map["applystate"] = index.applystate
//            map["applytime"] = index.applytime
//            map["bankName"] = index.bankName
//            map["bankType"] = index.bankType
//            map["cardNo"] = index.cardNo
//            map["iscitywide"] = index.iscitywide
//            map["checktime"] = index.checktime
//            map["paycapital"] = index.paycapital
//            map["subBankName"] = index.subBankName
//            map["usrname"] = index.usrname
//            map["usrtype"] = index.usrtype

            holder.billing_name3.setText(applystate1);
            holder.billing_bank3.setText("充值到" + list.get(position).get("orderno").toString().substring(0, 4) + "**的订单中");
            holder.billing_money3.setText("+" + list.get(position).get("ordercapital").toString());
            holder.billing_tiem3.setText(list.get(position).get("ordertime").toString());
        }
    }

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

    public void setData(int type, List<Map<String, Object>> list) {
        this.list = list;
        this.type = type;
        notifyDataSetChanged();
    }

    @Override
    public SimpleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.view_billingrecord_fragment_simple_adapter, parent, false);
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

    public class SimpleAdapterViewHolder extends RecyclerView.ViewHolder {

        LinearLayout transaction_linear1;
        LinearLayout transaction_linear2;
        LinearLayout transaction_linear3;

        TextView billing_name1;
        TextView billing_name2;
        TextView billing_name3;
        TextView billing_bank5;
        TextView billing_name4;

        TextView billing_bank1;
        TextView billing_bank2;
        TextView billing_bank3;

        TextView billing_money1;
        TextView billing_money2;
        TextView billing_money3;

        TextView billing_tiem2;
        TextView billing_tiem3;

        SimpleAdapterViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                transaction_linear1 = itemView.findViewById(R.id.transaction_linear1);
                transaction_linear2 = itemView.findViewById(R.id.transaction_linear2);
                transaction_linear3 = itemView.findViewById(R.id.transaction_linear3);

                billing_name1 = itemView.findViewById(R.id.billing_name1);
                billing_name2 = itemView.findViewById(R.id.billing_name2);
                billing_name3 = itemView.findViewById(R.id.billing_name3);
                billing_bank1 = itemView.findViewById(R.id.billing_bank1);
                billing_bank2 = itemView.findViewById(R.id.billing_bank2);
                billing_bank3 = itemView.findViewById(R.id.billing_bank3);
                billing_money1 = itemView.findViewById(R.id.billing_money1);
                billing_money2 = itemView.findViewById(R.id.billing_money2);
                billing_money3 = itemView.findViewById(R.id.billing_money3);
                billing_tiem2 = itemView.findViewById(R.id.billing_tiem2);
                billing_tiem3 = itemView.findViewById(R.id.billing_tiem3);
                billing_bank5 = itemView.findViewById(R.id.billing_bank5);
                billing_name4 = itemView.findViewById(R.id.billing_name4);

            }
        }
    }

    public Map<String, Object> getItem(int position) {
        if (position < list.size())
            return list.get(position);
        else
            return null;
    }

}
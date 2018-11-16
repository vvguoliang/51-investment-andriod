package sp51.spotpass.com.spotpass.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import sp51.spotpass.com.spotpass.ui.utils.AppUtil;

import java.util.List;

import sp51.spotpass.com.spotpass.R;
import sp51.spotpass.com.spotpass.ui.kchartlib.chatr.ExampleActivity;
import sp51.spotpass.com.spotpass.ui.recyclerview.recyclerview.BaseRecyclerAdapter;
import sp51.spotpass.com.spotpass.ui.utils.DensityUtil;
import sp51.spotpass.com.spotpass.ui.view.Dialog.DesignatesDialogBuilder;

public class DocumentaryFragmentSimpleAdapter extends BaseRecyclerAdapter<DocumentaryFragmentSimpleAdapter.SimpleAdapterViewHolder> {
    private List<String> list;
    private int largeCardHeight, smallCardHeight;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private int type = 0;

    private Context context;

    public DocumentaryFragmentSimpleAdapter(Context context) {
        this.context = context;
        largeCardHeight = DensityUtil.Companion.getInstance().dip2px(context, 150f);
        smallCardHeight = DensityUtil.Companion.getInstance().dip2px(context, 100f);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(SimpleAdapterViewHolder holder, int position, boolean isItem) {
        if (type == 1) {
          ViewGroup.LayoutParams lp;
            lp = holder.transaction_linear1.getLayoutParams();
            lp.width = RelativeLayout.MarginLayoutParams.MATCH_PARENT;
            lp.height = RelativeLayout.MarginLayoutParams.MATCH_PARENT;
            holder.transaction_linear1.setLayoutParams(lp);
            holder.transaction_linear.setVisibility(View.GONE);
            holder.transaction_linear_no_data.setVisibility(View.VISIBLE);
            holder.transaction_relative.setVisibility(View.GONE);
        } else if (type == 2) {
            ViewGroup.LayoutParams lp;
            lp = holder.transaction_linear1.getLayoutParams();
            lp.width = RelativeLayout.MarginLayoutParams.MATCH_PARENT;
            lp.height = RelativeLayout.MarginLayoutParams.MATCH_PARENT;
            holder.transaction_linear1.setLayoutParams(lp);
            holder.transaction_linear.setVisibility(View.GONE);
            holder.transaction_linear_no_data.setVisibility(View.VISIBLE);
            holder.transaction_relative.setVisibility(View.GONE);
        } else {
            ViewGroup.LayoutParams lp;
            lp = holder.transaction_linear1.getLayoutParams();
            lp.width = RelativeLayout.MarginLayoutParams.MATCH_PARENT;
            lp.height = RelativeLayout.MarginLayoutParams.MATCH_PARENT;
            holder.transaction_linear1.setLayoutParams(lp);
            holder.transaction_linear.setVisibility(View.GONE);
            holder.transaction_linear_no_data.setVisibility(View.VISIBLE);
            holder.transaction_relative.setVisibility(View.GONE);
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

    public void setData(int type, List<String> list) {
        this.list = list;
        this.type = type;
        notifyDataSetChanged();
    }

    @Override
    public SimpleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.view_sample_fragment_simple_adapter, parent, false);
        return new SimpleAdapterViewHolder(v, true);
    }

    public void insert(String person, int position) {
        insert(list, person, position);
    }

    public void remove(int position) {
        remove(list, position);
    }

    public void clear() {
        clear(list);
    }

    public class SimpleAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LinearLayout profit_list_liner;
        LinearLayout transaction_linear;
        RelativeLayout transaction_linear1;
        RelativeLayout transaction_linear_no_data;
        RelativeLayout transaction_relative;

//        LinearLayout transaction1;
//        LinearLayout transaction2;
//        LinearLayout transaction3;

        SimpleAdapterViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                profit_list_liner = itemView.findViewById(R.id.profit_list_liner);
//                transaction1 = itemView.findViewById(R.id.transaction1);
//                transaction2 = itemView.findViewById(R.id.transaction2);
//                transaction3 = itemView.findViewById(R.id.transaction3);
                profit_list_liner.setOnClickListener(this);
//                transaction1.setOnClickListener(this);
//                transaction2.setOnClickListener(this);
//                transaction3.setOnClickListener(this);
                transaction_linear = itemView.findViewById(R.id.transaction_linear);
                transaction_linear_no_data = itemView.findViewById(R.id.transaction_linear_no_data);
                transaction_relative = itemView.findViewById(R.id.transaction_relative);
                transaction_linear1 = itemView.findViewById(R.id.transaction_linear1);
                transaction_linear1.setOnClickListener(this);
                itemView.findViewById(R.id.transaction_buy_one).setOnClickListener(this);
                itemView.findViewById(R.id.transaction_buy_and_fall).setOnClickListener(this);
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.profit_list_liner:
                    context.startActivity(new Intent(context, ExampleActivity.class));
                    break;
//                case R.id.transaction1:
//                    getBack(1);
//                    break;
//                case R.id.transaction2:
//                    getBack(2);
//                    break;
//                case R.id.transaction3:
//                    getBack(3);
//                    break;
                case R.id.transaction_buy_one://买多
                    if (!AppUtil.Companion.getInstance().isFastDoubleClick()) {
                        new DesignatesDialogBuilder(context).title("琴童46780")
                                .isboolean(false)
                                .setCancelOnClickListener(v1 -> Toast.makeText(context, "去充值", Toast.LENGTH_LONG).show())
                                .setSureOnClickListener(v12 -> Toast.makeText(context, "去下单", Toast.LENGTH_LONG).show())
                                .build().show();
                    }
                    break;
                case R.id.transaction_buy_and_fall://卖空
                    if (!AppUtil.Companion.getInstance().isFastDoubleClick()) {
                        new DesignatesDialogBuilder(context).title("琴童46780")
                                .isboolean(true)
                                .setCancelOnClickListener(v1 -> Toast.makeText(context, "去充值", Toast.LENGTH_LONG).show())
                                .setSureOnClickListener(v12 -> Toast.makeText(context, "去下单", Toast.LENGTH_LONG).show())
                                .build().show();
                    }
                    break;
                case R.id.transaction_linear1:

                    break;
            }

        }

//        private void getBack(int type) {
//            switch (type) {
//                case 1:
//                    transaction1.setBackgroundResource(R.drawable.shape_c2_blue_grle);
//                    transaction2.setBackgroundResource(R.drawable.shape_c2_while_grle);
//                    transaction3.setBackgroundResource(R.drawable.shape_c2_while_grle);
//                    break;
//                case 2:
//                    transaction1.setBackgroundResource(R.drawable.shape_c2_while_grle);
//                    transaction2.setBackgroundResource(R.drawable.shape_c2_blue_grle);
//                    transaction3.setBackgroundResource(R.drawable.shape_c2_while_grle);
//                    break;
//                case 3:
//                    transaction1.setBackgroundResource(R.drawable.shape_c2_while_grle);
//                    transaction2.setBackgroundResource(R.drawable.shape_c2_while_grle);
//                    transaction3.setBackgroundResource(R.drawable.shape_c2_blue_grle);
//
//                    break;
//            }
//        }
    }

    public String getItem(int position) {
        if (position < list.size())
            return list.get(position);
        else
            return null;
    }

    //define interface
    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int data);

    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

}
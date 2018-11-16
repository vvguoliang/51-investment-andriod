package sp51.spotpass.com.spotpass.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import sp51.spotpass.com.spotpass.R;
import sp51.spotpass.com.spotpass.ui.kchartlib.utils.ConvertUtil;
import sp51.spotpass.com.spotpass.ui.recyclerview.recyclerview.BaseRecyclerAdapter;
import sp51.spotpass.com.spotpass.ui.utils.DensityUtil;
import sp51.spotpass.com.spotpass.ui.utils.ToatUtils;

public class TransactionViewSimpleAdapter extends BaseRecyclerAdapter<TransactionViewSimpleAdapter.SimpleAdapterViewHolder> {
    private List<Map<String, Object>> list;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    private Context context;
    private int Transact = 0;

    private String tag = "";
    private boolean isboolean = false;

    private int position1 = 0;

    public TransactionViewSimpleAdapter(Context context) {
        this.context = context;
    }

    public void getTransactionView(int Transact, String tag) {
        isboolean = true;
        this.Transact = Transact;
        this.tag = tag;
//        notifyItemChanged(this.Transact, "isboolean");
        notifyDataSetChanged();
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(SimpleAdapterViewHolder holder, @SuppressLint("RecyclerView") int position, boolean isItem) {
        holder.setIsRecyclable(false);
        Map<String, Object> map = list.get(position);
        holder.transaction_text1.setText(map.get("stkname").toString());
        holder.transaction_text2.setText(map.get("marginrate").toString());
        holder.transaction_text3.setText("波动1个点赚" + map.get("holdfeerate").toString() + "元");
        holder.rootView.setTag(map.get("stkname").toString());
        this.position1 = position;
//        if (isboolean) {
        if (Transact == this.position1) {
            holder.transaction1.setBackgroundResource(R.drawable.shape_c2_blue_grle);
        } else {
            holder.transaction1.setBackgroundResource(R.drawable.shape_c2_while_grle);
        }
//        } else {
//            if (Transact == this.position1) {
//                holder.transaction1.setBackgroundResource(R.drawable.shape_c2_blue_grle);
//            } else {
//                holder.transaction1.setBackgroundResource(R.drawable.shape_c2_while_grle);
//            }
//        }
    }

//    @Override
//    public void onBindViewHolder(@NonNull SimpleAdapterViewHolder holder, int position, @NonNull List<Object> payloads) {
//        super.onBindViewHolder(holder, position, payloads);
//        if (!payloads.isEmpty()) {
//            if (isboolean) {
//                if (Transact == this.position1 && holder.rootView.getTag() == tag) {
//                    holder.transaction1.setBackgroundResource(R.drawable.shape_c2_blue_grle);
//                } else {
//                    holder.transaction1.setBackgroundResource(R.drawable.shape_c2_while_grle);
//                }
//            } else {
//                if (Transact == this.position1) {
//                    holder.transaction1.setBackgroundResource(R.drawable.shape_c2_blue_grle);
//                } else {
//                    holder.transaction1.setBackgroundResource(R.drawable.shape_c2_while_grle);
//                }
//            }
//        } else {
//            onBindViewHolder(holder, position);
//        }
//    }

    @Override
    public int getAdapterItemViewType(int position) {
        return position;
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

    public void setData(List<Map<String, Object>> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public SimpleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.view_transaction_view_item_adapter, parent, false);
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

    public class SimpleAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LinearLayout rootView;
        LinearLayout transaction1;
        TextView transaction_text1;
        TextView transaction_text2;
        TextView transaction_text3;

        SimpleAdapterViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                rootView = itemView.findViewById(R.id.card_view);
                rootView.setOnClickListener(this);

                transaction1 = itemView.findViewById(R.id.transaction1);
                transaction_text1 = itemView.findViewById(R.id.transaction_text1);
                transaction_text2 = itemView.findViewById(R.id.transaction_text2);
                transaction_text3 = itemView.findViewById(R.id.transaction_text3);
            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.card_view:
                    if (mOnItemClickListener != null) {
                        //注意这里使用getTag方法获取数据
                        mOnItemClickListener.onItemClick(v, getAdapterPosition());
                    }
                    break;
            }
        }
    }

    public Map<String, Object> getItem(int position) {
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
package sp51.spotpass.com.spotpass.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Map;

import sp51.spotpass.com.spotpass.R;
import sp51.spotpass.com.spotpass.ui.recyclerview.recyclerview.BaseRecyclerAdapter;
import sp51.spotpass.com.spotpass.ui.utils.image.CircleImageView;

public class TransactionFragmentSimpleAdapter extends BaseRecyclerAdapter<TransactionFragmentSimpleAdapter.SimpleAdapterViewHolder> {
    private List<Map<String, Object>> list;
    private Context context;

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    public TransactionFragmentSimpleAdapter(Context context) {
        this.context = context;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(SimpleAdapterViewHolder holder, int position, boolean isItem) {
        if (list != null && list.size() > 0) {
            if (Double.parseDouble(list.get(position).get("8").toString()) >= 0) {
                holder.transaction_text3.setTextColor(Color.parseColor("#FF6D64"));
                holder.transaction_text2.setTextColor(Color.parseColor("#FF6D64"));
            } else {
                holder.transaction_text3.setTextColor(Color.parseColor("#55C774"));
                holder.transaction_text2.setTextColor(Color.parseColor("#55C774"));
            }
            holder.transaction_name_text.setText(list.get(position).get("0").toString());
            String for1 = list.get(position).get("1").toString();
            String s1 = "";
            NumberFormat format1 = new DecimalFormat("###,###.####");
            if (for1.contains("-")) {
                s1 = format1.format(Double.parseDouble(for1.replace("-", ""))); // 123,456,789.99
            } else if (for1.contains("+")) {
                s1 = format1.format(Double.parseDouble(for1.replace("+", ""))); // 123,456,789.99
            } else {
                s1 = format1.format(Double.parseDouble(for1)); // 123,456,789.99
            }
            holder.transaction_text3.setText(s1);
            holder.transaction_text1.setText(list.get(position).get("Name").toString());
            NumberFormat format = new DecimalFormat("###,###.####");
            String for2 = list.get(position).get("9").toString();
            String s = "";
            if (for2.contains("-")) {
                s = format.format(Double.parseDouble(for2.replace("-", ""))); // 123,456,789.99
            } else if (for2.contains("+")) {
                s = format.format(Double.parseDouble(for2.replace("+", ""))); // 123,456,789.99
            } else {
                s = format.format(Double.parseDouble(for2)); // 123,456,789.99
            }
            holder.transaction_text2.setText(s + "%");
        }
    }

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
                R.layout.view_transaction_fragment_simple_adapter, parent, false);
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

        TextView transaction_name_text;
        TextView transaction_text1;
        TextView transaction_text2;
        TextView transaction_text3;

        SimpleAdapterViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                itemView.findViewById(R.id.transaction_relative).setOnClickListener(this);
                transaction_name_text = itemView.findViewById(R.id.transaction_name_text);
                transaction_text1 = itemView.findViewById(R.id.transaction_text1);
                transaction_text2 = itemView.findViewById(R.id.transaction_text2);
                transaction_text3 = itemView.findViewById(R.id.transaction_text3);
            }
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                //注意这里使用getTag方法获取数据
                mOnItemClickListener.onItemClick(v, getAdapterPosition());
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
package sp51.spotpass.com.spotpass.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import sp51.spotpass.com.spotpass.R;
import sp51.spotpass.com.spotpass.ui.recyclerview.recyclerview.BaseRecyclerAdapter;
import sp51.spotpass.com.spotpass.ui.utils.image.CircleImageView;

public class ProfitSimpleAdapter extends BaseRecyclerAdapter<ProfitSimpleAdapter.SimpleAdapterViewHolder> {
    private List<Map<String, String>> list;
    private int largeCardHeight, smallCardHeight;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    private Context context;

    public ProfitSimpleAdapter(Context context) {
        this.context = context;
//        largeCardHeight = DensityUtil.Companion.getInstance().dip2px(context, 150f);
//        smallCardHeight = DensityUtil.Companion.getInstance().dip2px(context, 100f);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(SimpleAdapterViewHolder holder, int position, boolean isItem) {
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

    public void setData(List<Map<String, String>> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public SimpleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.view_profit_adapter_item, parent, false);
        return new SimpleAdapterViewHolder(v, true);
    }

    public void insert(Map<String, String> person, int position) {
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

        SimpleAdapterViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                profit_list_liner = itemView.findViewById(R.id.profit_list_liner);
                profit_list_liner.setOnClickListener(this);
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

    public Map<String, String> getItem(int position) {
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
package sp51.spotpass.com.spotpass.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;
import java.util.Map;

import sp51.spotpass.com.spotpass.R;
import sp51.spotpass.com.spotpass.ui.recyclerview.recyclerview.BaseRecyclerAdapter;
import sp51.spotpass.com.spotpass.ui.utils.DensityUtil;
import sp51.spotpass.com.spotpass.ui.utils.image.CircleImageView;

public class IntegralMallSimpleAdapter extends BaseRecyclerAdapter<IntegralMallSimpleAdapter.SimpleAdapterViewHolder> {
    private List<Map<String, Object>> list;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    private Context context;

    public IntegralMallSimpleAdapter(Context context) {
        this.context = context;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(SimpleAdapterViewHolder holder, int position, boolean isItem) {
        if (list != null && list.size() > 0) {
            holder.integral_mall_text1.setText(list.get(position).get("price").toString() + "元");
            Long integ = Long.parseLong(list.get(position).get("price").toString()) * 100;
            holder.integral_mall_text3.setText(integ.toString()+"金币");
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

    public void setData(List<Map<String, Object>> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public SimpleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.view_integral_mall_simple_adapter, parent, false);
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

        LinearLayout integral_mall_liner;
        TextView integral_mall_text1;
        TextView integral_mall_text2;
        TextView integral_mall_text3;
        TextView integral_mall_text4;
        TextView integral_mall_text5;

        SimpleAdapterViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                integral_mall_liner = itemView.findViewById(R.id.integral_mall_liner);
                integral_mall_text1 = itemView.findViewById(R.id.integral_mall_text1);
                integral_mall_text2 = itemView.findViewById(R.id.integral_mall_text2);
                integral_mall_text3 = itemView.findViewById(R.id.integral_mall_text3);
                integral_mall_text4 = itemView.findViewById(R.id.integral_mall_text4);
                integral_mall_text5 = itemView.findViewById(R.id.integral_mall_text5);
                integral_mall_liner.setOnClickListener(this);
                integral_mall_text5.setOnClickListener(this);
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
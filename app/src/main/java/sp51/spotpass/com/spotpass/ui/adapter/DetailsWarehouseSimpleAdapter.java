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

import sp51.spotpass.com.spotpass.R;
import sp51.spotpass.com.spotpass.ui.recyclerview.recyclerview.BaseRecyclerAdapter;
import sp51.spotpass.com.spotpass.ui.utils.DensityUtil;
import sp51.spotpass.com.spotpass.ui.utils.image.CircleImageView;

public class DetailsWarehouseSimpleAdapter extends BaseRecyclerAdapter<DetailsWarehouseSimpleAdapter.SimpleAdapterViewHolder> {
    private List<String> list;
    private int largeCardHeight, smallCardHeight;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    private Context context;

    public DetailsWarehouseSimpleAdapter(Context context) {
        this.context = context;
        largeCardHeight = DensityUtil.Companion.getInstance().dip2px(context, 150f);
        smallCardHeight = DensityUtil.Companion.getInstance().dip2px(context, 100f);
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

    public void setData(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public SimpleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.details_warehouse_adapter, parent, false);
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

        View detaile_warehouse_view;
        TextView detaile_warehouse_text;
        TextView detaile_warehouse_text1;
        TextView detaile_warehouse_building;
        TextView detaile_warehouse_time;
        TextView detaile_warehouse_exit;
        TextView detaile_warehouse_exit_time;
        TextView detaile_warehouse_exit_type;
        TextView detaile_warehouse_charge;
        TextView detaile_warehouse_exit_charge;
        TextView detaile_warehouse_profit_loss;

        SimpleAdapterViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                profit_list_liner = itemView.findViewById(R.id.profit_list_liner);
                profit_list_liner.setOnClickListener(this);

                detaile_warehouse_view = itemView.findViewById(R.id.detaile_warehouse_view); // 改变线颜色
                detaile_warehouse_text = itemView.findViewById(R.id.detaile_warehouse_text); // 改变字体颜色
                detaile_warehouse_text1 = itemView.findViewById(R.id.detaile_warehouse_text1); // 名字
                detaile_warehouse_building = itemView.findViewById(R.id.detaile_warehouse_building);
                detaile_warehouse_time = itemView.findViewById(R.id.detaile_warehouse_time);
                detaile_warehouse_exit = itemView.findViewById(R.id.detaile_warehouse_exit);
                detaile_warehouse_exit_time = itemView.findViewById(R.id.detaile_warehouse_exit_time);
                detaile_warehouse_exit_type = itemView.findViewById(R.id.detaile_warehouse_exit_type);
                detaile_warehouse_charge = itemView.findViewById(R.id.detaile_warehouse_charge);
                detaile_warehouse_exit_charge = itemView.findViewById(R.id.detaile_warehouse_exit_charge);
                detaile_warehouse_profit_loss = itemView.findViewById(R.id.detaile_warehouse_profit_loss);
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
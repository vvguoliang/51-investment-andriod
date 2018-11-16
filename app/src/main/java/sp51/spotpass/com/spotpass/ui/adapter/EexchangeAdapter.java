package sp51.spotpass.com.spotpass.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
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
import sp51.spotpass.com.spotpass.ui.utils.image.CircleImageView;

public class EexchangeAdapter extends BaseRecyclerAdapter<EexchangeAdapter.SimpleAdapterViewHolder> {
    private List<Map<String, String>> list;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    private Context context;

    private String type = "1";

    public EexchangeAdapter(Context context) {
        this.context = context;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(SimpleAdapterViewHolder holder, @SuppressLint("RecyclerView") int position, boolean isItem) {
        if (type.equals("1")) {
            holder.eexchang_line1.setVisibility(View.VISIBLE);
            holder.eexchang_line2.setVisibility(View.GONE);
//            map1["time"] = AppUtil.instance.getTime().toString()
//            map1["operation"] = "+50"
//            map1["source"] = "0"
            holder.eexchang_time1.setText(list.get(position).get("time"));
            holder.eexchang_operation1.setText(list.get(position).get("operation"));
        } else {
            holder.eexchang_line1.setVisibility(View.GONE);
            holder.eexchang_line2.setVisibility(View.VISIBLE);
            holder.eexchang_time2.setText(list.get(position).get("time"));
            holder.eexchang_source2.setText(list.get(position).get("source"));
            holder.eexchang_operation2.setText(list.get(position).get("operation"));
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

    public void setData(List<Map<String, String>> list, String type) {
        this.list = list;
        this.type = type;
        notifyDataSetChanged();
    }

    @Override
    public SimpleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.view_adapter_eexchange, parent, false);
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

        LinearLayout eexchang_line1;
        LinearLayout eexchang_line2;

        TextView eexchang_time1;
        TextView eexchang_time2;
        TextView eexchang_operation1;
        TextView eexchang_operation2;
        TextView eexchang_source2;

        SimpleAdapterViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                eexchang_line1 = itemView.findViewById(R.id.eexchang_line1);
                eexchang_line2 = itemView.findViewById(R.id.eexchang_line2);
                eexchang_time1 = itemView.findViewById(R.id.eexchang_time1);
                eexchang_time2 = itemView.findViewById(R.id.eexchang_time2);
                eexchang_operation1 = itemView.findViewById(R.id.eexchang_operation1);
                eexchang_operation2 = itemView.findViewById(R.id.eexchang_operation2);
                eexchang_source2 = itemView.findViewById(R.id.eexchang_source2);
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
package sp51.spotpass.com.spotpass.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

import sp51.spotpass.com.spotpass.R;
import sp51.spotpass.com.spotpass.ui.recyclerview.recyclerview.BaseRecyclerAdapter;

public class SignLogSimpleAdapter extends BaseRecyclerAdapter<SignLogSimpleAdapter.SimpleAdapterViewHolder> {
    private List<Map<String, Object>> list;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    private Context context;

    public SignLogSimpleAdapter(Context context) {
        this.context = context;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(SimpleAdapterViewHolder holder, int position, boolean isItem) {
        if (list != null && list.size() > 0) {
            holder.new_text_context.setText("您已" + list.get(position).get("type_name") + ":" + list.get(position).get("points").toString() + "金币");
            holder.new_text_time.setText(list.get(position).get("created_time").toString());
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
                R.layout.view_new_simple_adapter, parent, false);
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

        TextView new_text_context;
        TextView new_text_time;

        SimpleAdapterViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                itemView.findViewById(R.id.transaction_linear).setOnClickListener(this);
                new_text_context = itemView.findViewById(R.id.new_text_context);
                new_text_time = itemView.findViewById(R.id.new_text_time);
            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.transaction_linear:
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
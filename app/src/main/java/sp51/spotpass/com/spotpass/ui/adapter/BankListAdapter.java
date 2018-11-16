package sp51.spotpass.com.spotpass.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;
import java.util.Map;

import sp51.spotpass.com.spotpass.R;
import sp51.spotpass.com.spotpass.ui.recyclerview.recyclerview.BaseRecyclerAdapter;
import sp51.spotpass.com.spotpass.ui.utils.image.CircleImageView;

public class BankListAdapter extends BaseRecyclerAdapter<BankListAdapter.SimpleAdapterViewHolder> {
    private List<Map<String, Object>> list;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    private Context context;

    public BankListAdapter(Context context) {
        this.context = context;
    }

    @SuppressLint({"SetTextI18n", "CheckResult"})
    @Override
    public void onBindViewHolder(SimpleAdapterViewHolder holder, @SuppressLint("RecyclerView") int position, boolean isItem) {
        holder.bank_list_name.setText(list.get(position).get("name").toString());
        if (!TextUtils.isEmpty(list.get(position).get("url").toString())) {
            RequestOptions options = new RequestOptions();
            options.centerCrop().placeholder(R.mipmap.ic_banner).error(R.mipmap.ic_banner).fallback(R.mipmap.ic_banner);
            Glide.with(context).load(list.get(position).get("url").toString()).apply(options).into(holder.bank_list_image);
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
                R.layout.view_bank_list_adapter, parent, false);
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

        LinearLayout bank_linear;

        CircleImageView bank_list_image;
        TextView bank_list_name;

        SimpleAdapterViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                bank_linear = itemView.findViewById(R.id.bank_linear);
                bank_list_image = itemView.findViewById(R.id.bank_list_image);
                bank_list_name = itemView.findViewById(R.id.bank_list_name);
                bank_linear.setOnClickListener(this);
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
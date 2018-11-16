package sp51.spotpass.com.spotpass.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;
import java.util.Map;

import sp51.spotpass.com.spotpass.R;
import sp51.spotpass.com.spotpass.ui.activity.MainInteractionDetailsActivity;
import sp51.spotpass.com.spotpass.ui.recyclerview.recyclerview.BaseRecyclerAdapter;
import sp51.spotpass.com.spotpass.ui.utils.DensityUtil;
import sp51.spotpass.com.spotpass.ui.utils.image.CircleImageView;
import sp51.spotpass.com.spotpass.ui.view.Dialog.Vison.VisionShowDialog;

public class SimpleAdapter extends BaseRecyclerAdapter<SimpleAdapter.SimpleAdapterViewHolder> {
    private List<Map<String, Object>> list;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    private Context context;

    public SimpleAdapter(Context context) {
        this.context = context;
    }

    @SuppressLint({"SetTextI18n", "CheckResult"})
    @Override
    public void onBindViewHolder(SimpleAdapterViewHolder holder, int position, boolean isItem) {
        if(list != null && list.size() > 0){
            holder.home_name_text.setText(list.get(position).get("name").toString());
            holder.home_name_context.setText(list.get(position).get("context").toString());
            holder.home_time.setText(list.get(position).get("time").toString());
            holder.home_expert_interpretation.setText("专家解读");//list.get(position).get("role_name").toString());
            RequestOptions options = new RequestOptions();
            options.centerCrop().placeholder(R.mipmap.ic_gethead).error(R.mipmap.ic_gethead).fallback(R.mipmap.ic_gethead);
            Glide.with(context).load(list.get(position).get("avatar").toString()).apply(options).into(holder.home_name_avatar);
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
                R.layout.xrefreshview_item_recylerview, parent, false);
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
        TextView home_name_text;
        TextView home_name_context;
        Button home_expert_interpretation;
        TextView home_time;
        CircleImageView home_name_avatar;

        SimpleAdapterViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                rootView = itemView.findViewById(R.id.card_view);
                rootView.setOnClickListener(this);

                home_name_text = itemView.findViewById(R.id.home_name_text);
                home_name_context = itemView.findViewById(R.id.home_name_context);
                home_expert_interpretation = itemView.findViewById(R.id.home_expert_interpretation);
                home_time = itemView.findViewById(R.id.home_time);
                home_name_avatar = itemView.findViewById(R.id.home_name_avatar);
                home_expert_interpretation.setOnClickListener(this);
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
                case R.id.home_expert_interpretation:
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
package sp51.spotpass.com.spotpass.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;
import java.util.Map;

import sp51.spotpass.com.spotpass.R;
import sp51.spotpass.com.spotpass.ui.activity.RegisterActivity;
import sp51.spotpass.com.spotpass.ui.baseEntity.CirClessCommemts;
import sp51.spotpass.com.spotpass.ui.baseEntity.CirclessInfo;
import sp51.spotpass.com.spotpass.ui.recyclerview.recyclerview.BaseRecyclerAdapter;
import sp51.spotpass.com.spotpass.ui.utils.DensityUtil;
import sp51.spotpass.com.spotpass.ui.utils.SPUtils;
import sp51.spotpass.com.spotpass.ui.utils.Util;
import sp51.spotpass.com.spotpass.ui.utils.image.CircleImageView;

public class InteractionDetailSimpleAdapter extends BaseRecyclerAdapter<InteractionDetailSimpleAdapter.SimpleAdapterViewHolder> {
    private List<CirClessCommemts.Data.Data> dataList;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    private String type = "0";

    private Context context;

    public InteractionDetailSimpleAdapter(Context context) {
        this.context = context;
    }

    @SuppressLint({"SetTextI18n", "CheckResult"})
    @Override
    public void onBindViewHolder(SimpleAdapterViewHolder holder, int position, boolean isItem) {
        if (type.equals("0") || type.equals("0")) {
            RequestOptions options = new RequestOptions();
            options.centerCrop().placeholder(R.mipmap.ic_banner).error(R.mipmap.ic_banner).fallback(R.mipmap.ic_banner);
            Glide.with(context).load(dataList.get(position).getUser().getAvatar()).apply(options).into(holder.interaction_simple_details_image);
            holder.interaction_simple_details_name.setText(dataList.get(position).getUser().getName());
            holder.interaction_simple_content.setText(dataList.get(position).getContent());
            holder.interaction_simple_details_time.setText(dataList.get(position).getCreated());
        } else {
//            holder.interaction_simple_details_time.setVisibility(View.VISIBLE);
//            holder.interaction_simple_image.setVisibility(View.VISIBLE);
//            holder.interaction_simple_details_text.setVisibility(View.VISIBLE);
//            holder.interaction_simple_details_name.setText(list.get(position).get("name"));
//            holder.interaction_simple_content.setText(list.get(position).get("context"));
        }

    }

    @Override
    public int getAdapterItemViewType(int position) {
        return 0;
    }

    @Override
    public int getAdapterItemCount() {
        if (dataList != null && dataList.size() > 0)
            return dataList.size();
        else
            return 0;
    }

    @Override
    public SimpleAdapterViewHolder getViewHolder(View view) {
        return new SimpleAdapterViewHolder(view, false);
    }

    public void setData(List<CirClessCommemts.Data.Data> data, String type) {
        this.dataList = data;
        this.type = type;
        notifyDataSetChanged();
    }

    @Override
    public SimpleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.view_interaction_detail_adapter, parent, false);
        return new SimpleAdapterViewHolder(v, true);
    }

    public void insert(CirClessCommemts.Data.Data person, int position) {
        insert(dataList, person, position);
    }

    public void remove(int position) {
        remove(dataList, position);
    }

    public void clear() {
        clear(dataList);
    }

    public class SimpleAdapterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        LinearLayout profit_list_liner;
        CircleImageView interaction_simple_details_image;
        TextView interaction_simple_details_name;
        TextView interaction_simple_content;
        TextView interaction_simple_details_time;
        TextView interaction_simple_details_text;
        ImageView interaction_simple_image;

        SimpleAdapterViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                profit_list_liner = itemView.findViewById(R.id.profit_list_liner);
                profit_list_liner.setOnClickListener(this);

                interaction_simple_details_image = itemView.findViewById(R.id.interaction_simple_details_image);
                interaction_simple_details_name = itemView.findViewById(R.id.interaction_simple_details_name);
                interaction_simple_content = itemView.findViewById(R.id.interaction_simple_content);
                interaction_simple_details_time = itemView.findViewById(R.id.interaction_simple_details_time);
                interaction_simple_image = itemView.findViewById(R.id.interaction_simple_image);
                interaction_simple_image.setOnClickListener(this);
                interaction_simple_details_text = itemView.findViewById(R.id.interaction_simple_details_text);
                interaction_simple_details_text.setOnClickListener(this);
            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.interaction_simple_details_text:
                case R.id.interaction_simple_image:
                    if (TextUtils.isEmpty(SPUtils.Companion.getInstance(context, "Authorization").getString("Authorization"))) {
                        Util.INSTANCE.registerA1(context);
                    } else {

                    }
                    break;
                case R.id.profit_list_liner:
                    if (mOnItemClickListener != null) {
                        //注意这里使用getTag方法获取数据
                        mOnItemClickListener.onItemClick(v, getAdapterPosition());
                    }
                    break;
            }
        }
    }

    public CirClessCommemts.Data.Data getItem(int position) {
        if (position < dataList.size())
            return dataList.get(position);
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
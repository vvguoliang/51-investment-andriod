package sp51.spotpass.com.spotpass.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
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
import sp51.spotpass.com.spotpass.ui.recyclerview.recyclerview.BaseRecyclerAdapter;
import sp51.spotpass.com.spotpass.ui.utils.DensityUtil;
import sp51.spotpass.com.spotpass.ui.utils.image.CircleImageView;

public class ProfitListSimpleAdapter extends BaseRecyclerAdapter<ProfitListSimpleAdapter.SimpleAdapterViewHolder> {
    private List<Map<String, String>> list;
    private int largeCardHeight, smallCardHeight;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    private Context context;

    public ProfitListSimpleAdapter(Context context) {
        this.context = context;
//        largeCardHeight = DensityUtil.Companion.getInstance().dip2px(context, 150f);
//        smallCardHeight = DensityUtil.Companion.getInstance().dip2px(context, 100f);
    }

    @SuppressLint({"SetTextI18n", "CheckResult"})
    @Override
    public void onBindViewHolder(SimpleAdapterViewHolder holder, int position, boolean isItem) {
        if(list != null && list.size() > 0){
            RequestOptions options = new RequestOptions();
            options.centerCrop().placeholder(R.mipmap.ic_banner).error(R.mipmap.ic_banner).fallback(R.mipmap.ic_banner);
            Glide.with(context).load(list.get(position).get("image")).apply(options).into(holder.profit_list_image);
            holder.profit_list_num.setText((position + 3) + "");
            holder.profit_list_name.setText(list.get(position).get("name") + (position + 3));
            String[] ss = list.get(position).get("context").split("%");
            SpannableStringBuilder style = new SpannableStringBuilder(ss[0] + "%" + ss[1]);
            style.setSpan(new ForegroundColorSpan(Color.parseColor("#FF6D64")), 2, (ss[0] + "%").length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            holder.profit_list_context.setText(style);
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

    public void setData(List<Map<String, String>> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public SimpleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.profit_list_simple_adapter, parent, false);
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
        CircleImageView profit_list_image;
        TextView profit_list_num;
        TextView profit_list_name;
        TextView profit_list_context;

        SimpleAdapterViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                profit_list_image = itemView.findViewById(R.id.profit_list_image);
                profit_list_liner = itemView.findViewById(R.id.profit_list_liner);
                profit_list_num = itemView.findViewById(R.id.profit_list_num);
                profit_list_name = itemView.findViewById(R.id.profit_list_name);
                profit_list_context = itemView.findViewById(R.id.profit_list_context);
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
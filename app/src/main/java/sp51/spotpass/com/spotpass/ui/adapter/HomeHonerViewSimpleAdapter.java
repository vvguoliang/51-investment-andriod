package sp51.spotpass.com.spotpass.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

import sp51.spotpass.com.spotpass.R;
import sp51.spotpass.com.spotpass.ui.kchartlib.utils.ConvertUtil;
import sp51.spotpass.com.spotpass.ui.recyclerview.recyclerview.BaseRecyclerAdapter;
import sp51.spotpass.com.spotpass.ui.utils.DensityUtil;

public class HomeHonerViewSimpleAdapter extends BaseRecyclerAdapter<HomeHonerViewSimpleAdapter.SimpleAdapterViewHolder> {
    private List<Map<String, Object>> list;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    private Context context;

    private String floatPrice = "";
    private String floatPercent = "";
    private String price = "";
    private String quoteName = "";

    public HomeHonerViewSimpleAdapter(Context context) {
        this.context = context;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(SimpleAdapterViewHolder holder, int position, boolean isItem) {
        if (list != null && list.size() > 0) {
            floatPrice = list.get(position).get("floatPrice").toString();
            floatPercent = list.get(position).get("floatPercent").toString();
            price = list.get(position).get("price").toString();
            quoteName = list.get(position).get("quoteName").toString();
           String lastPrice = list.get(position).get("lastPrice").toString();
            if (Float.parseFloat(price) < Float.parseFloat(lastPrice)) {
                holder.home_banner_variety_money.setTextColor(Color.parseColor("#028845"));
                holder.home_banner_variety_increase.setTextColor(Color.parseColor("#028845"));
            } else {
                holder.home_banner_variety_money.setTextColor(Color.parseColor("#FF6D64"));
                holder.home_banner_variety_increase.setTextColor(Color.parseColor("#FF6D64"));
            }
            holder.home_banner_variety_money.setText(price);
            holder.home_banner_variety_increase.setText(floatPrice + "    " + floatPercent + "%");
            holder.home_banner_variety_name.setText(quoteName);
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
                R.layout.view_homehoner_view_item_adapter, parent, false);
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
        LinearLayout home_banner_k;
        TextView home_banner_variety_name;
        TextView home_banner_variety_increase;
        TextView home_banner_variety_money;

        SimpleAdapterViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                rootView = itemView.findViewById(R.id.card_view);
                rootView.setOnClickListener(this);

                home_banner_k = itemView.findViewById(R.id.home_banner_k);
                home_banner_variety_name = itemView.findViewById(R.id.home_banner_variety_name);
                home_banner_variety_money = itemView.findViewById(R.id.home_banner_variety_money);
                home_banner_variety_increase = itemView.findViewById(R.id.home_banner_variety_increase);

            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
//                case R.id.home_expert_interpretation:
//                    Intent intent = new Intent(context, MainInteractionDetailsActivity.class);
//                    intent.putExtra("type", "1");
//                    intent.putExtra("name","");
//                    context.startActivity(intent);
//                    break;
                case R.id.card_view:
                    if (mOnItemClickListener != null) {
                        //注意这里使用getTag方法获取数据
                        mOnItemClickListener.onItemClick(v, getAdapterPosition());
                    }
                    break;
            }
        }
    }

    /**
     * 透明效果
     *
     * @return
     */
    public Animation getAlphaAnimationIn() {
//实例化 AlphaAnimation 主要是改变透明度
//透明度 从 1-不透明 0-完全透明
        Animation animation = new AlphaAnimation(1.0f, 0);
//设置动画插值器 被用来修饰动画效果,定义动画的变化率
        animation.setInterpolator(new DecelerateInterpolator());
//设置动画执行时间
        animation.setDuration(2000);
        return animation;
    }

    public Animation getAlphaAnimationOut() {
//实例化 AlphaAnimation 主要是改变透明度
//透明度 从 1-不透明 0-完全透明
        Animation animation = new AlphaAnimation(0, 1.0f);
//设置动画插值器 被用来修饰动画效果,定义动画的变化率
        animation.setInterpolator(new DecelerateInterpolator());
//设置动画执行时间
        animation.setDuration(2000);
        return animation;
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
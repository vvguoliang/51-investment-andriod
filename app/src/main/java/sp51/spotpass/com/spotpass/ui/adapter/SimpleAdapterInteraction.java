package sp51.spotpass.com.spotpass.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import sp51.spotpass.com.spotpass.R;
import sp51.spotpass.com.spotpass.ui.baseEntity.CirclessList;
import sp51.spotpass.com.spotpass.ui.baseEntity.ViDeoList;
import sp51.spotpass.com.spotpass.ui.http.HttpALl;
import sp51.spotpass.com.spotpass.ui.http.HttpImplements;
import sp51.spotpass.com.spotpass.ui.recyclerview.recyclerview.BaseRecyclerAdapter;
import sp51.spotpass.com.spotpass.ui.utils.SPUtils;
import sp51.spotpass.com.spotpass.ui.utils.Util;
import sp51.spotpass.com.spotpass.ui.utils.image.CircleImageView;

public class SimpleAdapterInteraction extends BaseRecyclerAdapter<SimpleAdapterInteraction.SimpleAdapterViewHolder> {
    private List<CirclessList.Data.Data> list;
    private List<ViDeoList.Data.Data> listvideo;
    private OnRecyclerViewItemClickListener mOnItemClickListener = null;
    private int type = 0;

    private Context context;

    private ListMsgAdapter listMsgAdapter;

    public SimpleAdapterInteraction(Context context) {
        this.context = context;
    }

    @SuppressLint({"SetTextI18n", "CheckResult"})
    @Override
    public void onBindViewHolder(SimpleAdapterViewHolder holder, int position, boolean isItem) {
        RequestOptions options = new RequestOptions();
        options.centerCrop().placeholder(R.mipmap.ic_gethead).error(R.mipmap.ic_gethead).fallback(R.mipmap.ic_gethead);
        if (type == 1) {
            holder.interaction_simple_linear.setVisibility(View.VISIBLE);
            holder.interaction_simple_vedio_linear.setVisibility(View.GONE);
            String role = "";
            String avatar = "";
            role = list.get(position).getUser().getName();
            avatar = list.get(position).getUser().getAvatar();

            if (list.get(position).getImgs().size() > 0) {
                for (int i = 0; list.get(position).getImgs().size() > i; i++) {
                    if (i == 0) {
                        holder.interaction_simple_image1.setVisibility(View.VISIBLE);
                        Glide.with(context).load(list.get(position).getImgs().get(i).getImg()).apply(options).into(holder.interaction_simple_image1);
                    } else if (i == 1) {
                        holder.interaction_simple_image2.setVisibility(View.VISIBLE);
                        Glide.with(context).load(list.get(position).getImgs().get(i).getImg()).apply(options).into(holder.interaction_simple_image2);
                    } else {
                        holder.interaction_simple_image3.setVisibility(View.VISIBLE);
                        Glide.with(context).load(list.get(position).getImgs().get(i).getImg()).apply(options).into(holder.interaction_simple_image3);
                    }
                }
            }
            Glide.with(context).load(avatar).apply(options).into(holder.profit_list_image);
            holder.interaction_simple_name.setText(role);
            holder.interaction_simple_name_time.setText(list.get(position).getCreated());
            holder.interaction_simple_content.setText(list.get(position).getContent());
            holder.interaction_simple_cmment.setText("评论  (" + list.get(position).getCommentsNum() + ")");
            holder.interaction_simple_give_thumbs.setText("点赞  (" + list.get(position).getLikesNum() + ")");
            if (listMsgAdapter != null) {
                listMsgAdapter.getData(list.get(position).getComments());
            } else {
                listMsgAdapter = new ListMsgAdapter(context);
                holder.interaction_simple_listview.setAdapter(listMsgAdapter);
                listMsgAdapter.getData(list.get(position).getComments());
            }
            setListViewHeightBasedOnChildren(holder.interaction_simple_listview);
        } else {
            holder.interaction_simple_linear.setVisibility(View.GONE);
            holder.interaction_simple_vedio_linear.setVisibility(View.VISIBLE);
            if (listvideo != null && listvideo.size() > 0) {
                Glide.with(context).load(HttpImplements.Companion.get().getHttps51() + listvideo.get(position).getCoverUrl()).apply(options).into(holder.interaction_simple_vedio_image);
                holder.interaction_simple_vedio_name.setText(listvideo.get(position).getContent());
                holder.interaction_simple_vedio_content.setText(listvideo.get(position).getLearnNum() + "人观看");
            }
        }
    }

    private void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
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

    public void setData(int type, List<CirclessList.Data.Data> list) {
        this.list = list;
        this.type = type;
        notifyDataSetChanged();
    }

    public void setDatavideo(int type, List<ViDeoList.Data.Data> list) {
        this.listvideo = list;
        this.type = type;
        notifyDataSetChanged();
    }

    @Override
    public SimpleAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType,
                                                      boolean isItem) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.view_interaction_simple_adapter, parent, false);
        return new SimpleAdapterViewHolder(v, true);
    }

    public void insert(CirclessList.Data.Data person, int position) {
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
        LinearLayout interaction_simple_linear;
        LinearLayout interaction_simple_vedio_linear;
        CircleImageView profit_list_image;
        TextView interaction_simple_name;
//        ImageView interaction_simple_name_image;
        TextView interaction_simple_name_time;
        TextView interaction_simple_content;
        TextView interaction_simple_cmment;
        TextView interaction_simple_give_thumbs;
        ImageView interaction_simple_vedio_image;
        TextView interaction_simple_vedio_name;
        TextView interaction_simple_vedio_content;
        ImageView interaction_simple_image1;
        ImageView interaction_simple_image2;
        ImageView interaction_simple_image3;

        ListView interaction_simple_listview;

        SimpleAdapterViewHolder(View itemView, boolean isItem) {
            super(itemView);
            if (isItem) {
                profit_list_liner = itemView.findViewById(R.id.profit_list_liner);
                profit_list_liner.setOnClickListener(this);
                interaction_simple_linear = itemView.findViewById(R.id.interaction_simple_linear);
                interaction_simple_vedio_linear = itemView.findViewById(R.id.interaction_simple_vedio_linear);
                profit_list_image = itemView.findViewById(R.id.profit_list_image);
                interaction_simple_name = itemView.findViewById(R.id.interaction_simple_name);
//                interaction_simple_name_image = itemView.findViewById(R.id.interaction_simple_name_image);
                interaction_simple_name_time = itemView.findViewById(R.id.interaction_simple_name_time);
                interaction_simple_content = itemView.findViewById(R.id.interaction_simple_content);
                interaction_simple_cmment = itemView.findViewById(R.id.interaction_simple_cmment);
                interaction_simple_give_thumbs = itemView.findViewById(R.id.interaction_simple_give_thumbs);
                interaction_simple_vedio_image = itemView.findViewById(R.id.interaction_simple_vedio_image);
                interaction_simple_vedio_name = itemView.findViewById(R.id.interaction_simple_vedio_name);
                interaction_simple_vedio_content = itemView.findViewById(R.id.interaction_simple_vedio_content);
                itemView.findViewById(R.id.interaction_simple_image).setOnClickListener(this); // 点赞
                itemView.findViewById(R.id.interaction_simple_give_thumbs).setOnClickListener(this);

                interaction_simple_image1 = itemView.findViewById(R.id.interaction_simple_image1);
                interaction_simple_image2 = itemView.findViewById(R.id.interaction_simple_image2);
                interaction_simple_image3 = itemView.findViewById(R.id.interaction_simple_image3);
                interaction_simple_listview = itemView.findViewById(R.id.interaction_simple_listview);
                listMsgAdapter = new ListMsgAdapter(context);
                interaction_simple_listview.setAdapter(listMsgAdapter);
            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.interaction_simple_give_thumbs:
                case R.id.interaction_simple_image:
                    if (TextUtils.isEmpty(SPUtils.Companion.getInstance(context, "Authorization").getString("Authorization"))) {
                        Util.INSTANCE.registerA1(context);
                    } else {
                        HttpALl.Companion.get().setCIRCLESSLIKES(getAdapterPosition(), mHandler, context);
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

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

        }
    };

    public CirclessList.Data.Data getItem(int position) {
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
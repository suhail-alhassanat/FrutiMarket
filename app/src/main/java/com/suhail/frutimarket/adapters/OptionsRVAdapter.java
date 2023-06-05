package com.suhail.frutimarket.adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.suhail.frutimarket.R;
import com.suhail.frutimarket.listeners.OnOptionClickedListener;
import com.suhail.frutimarket.models.OptionsItem;

import java.util.List;

public class OptionsRVAdapter extends RecyclerView.Adapter<OptionsRVAdapter.OptionsViewHolder> {
    List<OptionsItem> items;
    Context context;
    OnOptionClickedListener listener;

    public OptionsRVAdapter(List<OptionsItem> items, Context context,OnOptionClickedListener listener) {
        this.items = items;
        this.context = context;
        this.listener=listener;
    }

    @NonNull
    @Override
    public OptionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OptionsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_account_choices_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull OptionsViewHolder holder, int position) {
        holder.tv_text.setText(items.get(position).getText());
        holder.iv_icon.setImageResource(items.get(position).getImg_res_id());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onOptionClicked(holder.tv_text.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class OptionsViewHolder extends RecyclerView.ViewHolder{
        TextView tv_text;
        ImageView iv_icon;
        public OptionsViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_text=itemView.findViewById(R.id.tv_text);
            iv_icon=itemView.findViewById(R.id.iv_icon);
        }
    }
}

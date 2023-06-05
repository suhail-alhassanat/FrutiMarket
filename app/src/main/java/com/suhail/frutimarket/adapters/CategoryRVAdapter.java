package com.suhail.frutimarket.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.suhail.frutimarket.R;
import com.suhail.frutimarket.listeners.OnCategoryClickedListener;
import com.suhail.frutimarket.models.Category;
import com.suhail.frutimarket.ui.activities.MainActivity;

import java.util.List;


public class CategoryRVAdapter extends RecyclerView.Adapter<CategoryRVAdapter.CategoryViewHolder> {
   List<Category> categoryList;
    OnCategoryClickedListener listener;
    public void addListener(OnCategoryClickedListener listener){
        this.listener=listener;
    }
    public void removeListener(){
        this.listener=null;
    }
    public CategoryRVAdapter(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_category_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.tv_cat.setText(categoryList.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Category category=categoryList.get(position);
                listener.onCatClicke(category.getId(),category.getTitle(),category.getDescription());
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView tv_cat;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_cat=itemView.findViewById(R.id.tv_category_name_custom);
        }
    }
}

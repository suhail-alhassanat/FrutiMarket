package com.suhail.frutimarket.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;
import com.suhail.frutimarket.R;
import com.suhail.frutimarket.models.Product;

import java.util.List;

public class ProductRVAdapter extends RecyclerView.Adapter<ProductRVAdapter.productViewHolder>{
    List<Product> products;

    public ProductRVAdapter(List<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public productViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new productViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_product_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull productViewHolder holder, int position) {
        Picasso.get().load(products.get(position).getImage()).into(holder.iv_image);
        holder.tv_name.setText(products.get(position).getName());
        holder.tv_price.setText(products.get(position).getPrice()+"$");
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class productViewHolder extends RecyclerView.ViewHolder{
        RoundedImageView iv_image;
        TextView tv_name,tv_price;
        public productViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_image=itemView.findViewById(R.id.iv_product_image);
            tv_name=itemView.findViewById(R.id.tv_product_name);
            tv_price=itemView.findViewById(R.id.tv_price);
        }
    }
}

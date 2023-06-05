package com.suhail.frutimarket.ui.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.suhail.frutimarket.R;
import com.suhail.frutimarket.adapters.CategoryRVAdapter;
import com.suhail.frutimarket.adapters.ProductRVAdapter;
import com.suhail.frutimarket.apis.RequestManager;
import com.suhail.frutimarket.databinding.FragmentHomeBinding;
import com.suhail.frutimarket.listeners.OnCategoryClickedListener;
import com.suhail.frutimarket.models.Category;
import com.suhail.frutimarket.models.CategoryResponse;
import com.suhail.frutimarket.models.ProductResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements OnCategoryClickedListener{

    int categoryId=1;
    TextView tv_catName,tv_catAbout,tv_catName2,tv_catAbout2;
    ProductRVAdapter productAdapter;

    ProgressDialog dialog;
    RecyclerView rvProducts1;
    RecyclerView rvProducts2;
    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentHomeBinding binding;
        binding=FragmentHomeBinding.inflate(getLayoutInflater());
        tv_catName=binding.tvCategoryName;
        tv_catAbout=binding.tvAboutCategory;
        rvProducts1=binding.rvProducts1;
        rvProducts2=binding.rvProducts2;
        tv_catName2=binding.tvCategoryName2;
        tv_catAbout2=binding.tvAboutCategory2;
        dialog=new ProgressDialog(getActivity());
        dialog.setMessage("Loading..");
        dialog.show();
        Call<CategoryResponse> callCategory= RequestManager.getClient().getCategories();
        callCategory.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if(response.isSuccessful()){
                    CategoryRVAdapter adapter=new CategoryRVAdapter(response.body().getData());
                    adapter.addListener(HomeFragment.this);
                    binding.rvCategories.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));
                    binding.rvCategories.setAdapter(adapter);
                    binding.tvCategoryName.setText(response.body().getData().get(0).getTitle());
                    binding.tvAboutCategory.setText(response.body().getData().get(0).getDescription());
                    binding.tvCategoryName2.setText(response.body().getData().get(0).getTitle());
                    binding.tvAboutCategory2.setText(response.body().getData().get(0).getDescription());
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        Call<ProductResponse> callProduct=RequestManager.getClient().getProductsByCategoryId(categoryId);
        callProduct.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if(response.isSuccessful()){
                    dialog.dismiss();
                    productAdapter=new ProductRVAdapter(response.body().getData());
                    binding.rvProducts1.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
                    binding.rvProducts1.setAdapter(productAdapter);
                    rvProducts2.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
                    rvProducts2.setAdapter(productAdapter);
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onCatClicke(int catId, String catTitle, String description) {
        dialog.show();
        categoryId=catId;
        tv_catName.setText(catTitle);
        tv_catAbout.setText(description);
        tv_catName2.setText(catTitle);
        tv_catAbout2.setText(description);
        Call<ProductResponse> callProduct=RequestManager.getClient().getProductsByCategoryId(categoryId);
        callProduct.enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                dialog.dismiss();
                productAdapter=new ProductRVAdapter(response.body().getData());
                rvProducts1.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
                rvProducts1.setAdapter(productAdapter);
                rvProducts2.setLayoutManager(new LinearLayoutManager(getActivity(),RecyclerView.HORIZONTAL,false));
                rvProducts2.setAdapter(productAdapter);
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
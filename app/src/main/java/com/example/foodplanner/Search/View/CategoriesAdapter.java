package com.example.foodplanner.Search.View;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter extends
        RecyclerView.Adapter<CategoriesAdapter.MyViewHolder>{


    private static final String TAG = "MyRecyclerAdapter";
    Context context;

    Category category = new Category("","");
    List<Category> categoryList=new ArrayList<Category>();

    //Button btnAdd;


    //OnProductsClickListener onProductsClickListener;

    public CategoriesAdapter(Context context,
                                  List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
        //mealList=new ArrayList<Meal>();
    }

    public void setMyList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    @NonNull
    @Override
    public CategoriesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.search_card,parent,false);
        CategoriesAdapter.MyViewHolder myViewHolder = new CategoriesAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoriesAdapter.MyViewHolder holder, int position) {
        Log.i(TAG, "position: "+position);
        Log.i(TAG, "name[0]: "+categoryList.get(position).getName());
        holder.nameView.setText(categoryList.get(position).getName());
        Glide.with(context).load(categoryList.get(position).getThumbnail())
                .into(holder.categoryImg);
    }


    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nameView;
        ImageView categoryImg;

        //public Button btnAdd;
        public MyViewHolder(View v) {
            super(v);
            nameView=v.findViewById(R.id.search_name);
            categoryImg=v.findViewById(R.id.search_img);
        }
    }
}


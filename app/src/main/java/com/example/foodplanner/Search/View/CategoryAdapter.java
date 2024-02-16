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
import com.example.foodplanner.HomeScreen.View.OnClickMealInter;
import com.example.foodplanner.Model.Category;
import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends
        RecyclerView.Adapter<CategoryAdapter.MyViewHolder>{


    private static final String TAG = "MyRecyclerAdapter";
    Context context;

    Category category = new Category("","");
    List<Category> categoryList=new ArrayList<Category>();

    //click on meal
    static OnClickMealInter onClickMealInter;

    public CategoryAdapter(Context context,
                           List<Category> categoryList) {
        this.context = context;
        this.categoryList = categoryList;
        //mealList=new ArrayList<Meal>();
    }

    public void setMyList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    //click on meal
    public void setClickMeal(OnClickMealInter onClickMealInter){
        this.onClickMealInter=onClickMealInter;
    }

    @NonNull
    @Override
    public CategoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.search_card,parent,false);
        CategoryAdapter.MyViewHolder myViewHolder = new CategoryAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.MyViewHolder holder, int position) {
        Log.i(TAG, "position: "+position);
        Log.i(TAG, "name[0]: "+categoryList.get(position).getName());
        holder.nameView.setText(categoryList.get(position).getName());
        Glide.with(context).load(categoryList.get(position).getThumbnail())
                .into(holder.categoryImg);
    }

    public Category getItemCategory(int position){
        return categoryList.get(position);
    }


    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nameView;
        ImageView categoryImg;

        //public Button btnAdd;
        public MyViewHolder(View v) {
            super(v);
            nameView=v.findViewById(R.id.search_name);
            categoryImg=v.findViewById(R.id.search_img);
            //click on meal
            v.setOnClickListener(this);
        }
        //click on meal
        @Override
        public void onClick(View v) {
            onClickMealInter.onClick(v,getAdapterPosition());
        }
    }
}


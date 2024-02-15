package com.example.foodplanner.HomeScreen.View;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;

public class ChickenCategoryAdapter extends
        RecyclerView.Adapter<ChickenCategoryAdapter.MyViewHolder>{


    HomeFragmentInter homeFragmentInter;
    private static final String TAG = "MyRecyclerAdapter";
    Context context;
    Meal meal = new Meal(1,"","","","","");
    List<Meal> mealList=new ArrayList<Meal>();

    Button btnAdd;


    //OnProductsClickListener onProductsClickListener;

    public ChickenCategoryAdapter(Context context,
                                  List<Meal> mealList) {
        this.context = context;
        this.mealList = mealList;
        this.homeFragmentInter=homeFragmentInter;
        //mealList=new ArrayList<Meal>();
    }

    public void setMyList(List<Meal> myList) {
        this.mealList = myList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.wrapped_meal_card,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Log.i(TAG, "position: "+position);
        Log.i(TAG, "name[0]: "+mealList.get(position).getName());
        holder.nameView.setText(mealList.get(position).getName());
        Glide.with(context).load(mealList.get(position).getThumbnail())
                .into(holder.mealImg);

        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                homeFragmentInter.onProductClick(meal);
            }
        });
    }


    @Override
    public int getItemCount() {
        return mealList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nameView;
        ImageView mealImg;

        Button btnAdd;
        public MyViewHolder(View v) {
            super(v);
            nameView=v.findViewById(R.id.wrapped_meal_name);
            mealImg=v.findViewById(R.id.wrapped_meal_img);
            btnAdd=v.findViewById(R.id.btn_add);
        }
    }
}

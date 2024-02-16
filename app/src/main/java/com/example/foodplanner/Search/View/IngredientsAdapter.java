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
import com.example.foodplanner.Model.Area;
import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Ingredient;
import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;

public class IngredientsAdapter extends
        RecyclerView.Adapter<IngredientsAdapter.MyViewHolder>{


    private static final String TAG = "MyRecyclerAdapter";
    private static final String TAG_IMG = "IMG";

    static OnClickMealInter onClickMealInterIngredients;


    Context context;

    List<Ingredient> ingredientList=new ArrayList<Ingredient>();


    public IngredientsAdapter(Context context,
                              List<Ingredient> ingredientList) {
        this.context = context;
        this.ingredientList = ingredientList;
    }

    public void setMyList(List<Ingredient> ingredientList) {
        this.ingredientList = ingredientList;
    }

    //click on meal
    public void setClickMealIngredints(OnClickMealInter onClickMealInter){
        this.onClickMealInterIngredients=onClickMealInter;
    }
    @NonNull
    @Override
    public IngredientsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.search_card,parent,false);
        IngredientsAdapter.MyViewHolder myViewHolder = new IngredientsAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsAdapter.MyViewHolder holder, int position) {
        Log.i(TAG, "position: "+position);
        if(!ingredientList.get(position).getIngredientName().equals("")){
            holder.nameView.setText(ingredientList.get(position).getIngredientName());
            String imgURL="https://www.themealdb.com/images/ingredients/"+
                    ingredientList.get(position).getIngredientName()+".png";
            Log.i(TAG_IMG, "img: "+imgURL);

            Glide.with(context).load(imgURL)
                    .into(holder.ingredientsImg);
        }
    }

    public Ingredient getItemIngredient (int position){
        return ingredientList.get(position);
    }



    @Override
    public int getItemCount() {
        return ingredientList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView nameView;
        ImageView ingredientsImg;

        public MyViewHolder(View v) {
            super(v);
            nameView=v.findViewById(R.id.search_name);
            ingredientsImg=v.findViewById(R.id.search_img);
            //click on meal
            v.setOnClickListener(this);
        }
        //click on meal
        @Override
        public void onClick(View v) {
            onClickMealInterIngredients.onClick(v,getAdapterPosition());
        }
    }
}


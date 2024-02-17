package com.example.foodplanner.Favourate.View;

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

public class FavMealsAdapter extends
        RecyclerView.Adapter<FavMealsAdapter.MyViewHolder>{


    InterFavProductsView interFavProductsView;
    private static final String TAG = "MyRecyclerAdapter";
    Context context;
//    Meal meal = new Meal("","","","","","");
    List<Meal> mealList=new ArrayList<Meal>();

    Button btnRemove;


    OnMealClickListener onMealClickListener;

    public FavMealsAdapter(Context context,
                               List<Meal> mealList,OnMealClickListener onMealClickListener) {
        this.context = context;
        this.mealList = mealList;
        this.onMealClickListener=onMealClickListener;
    }

    public void setMyList(List<Meal> myList) {
        this.mealList = myList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.remove_meal_card,parent,false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Meal meal=mealList.get(position);
        Log.i(TAG, "position: "+position);
        Log.i(TAG, "name[0]: "+mealList.get(position).getName());
        holder.nameView.setText(mealList.get(position).getName());
        Glide.with(context).load(mealList.get(position).getThumbnail())
                .into(holder.mealImg);
        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMealClickListener.onFavClick(meal);
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

        public Button btnRemove;
        public MyViewHolder(View v) {
            super(v);
            nameView=v.findViewById(R.id.fav_meal_name);
            mealImg=v.findViewById(R.id.fav_img);
            btnRemove=v.findViewById(R.id.btn_remove);
        }
    }
}

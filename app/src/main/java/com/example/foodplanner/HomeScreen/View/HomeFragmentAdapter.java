package com.example.foodplanner.HomeScreen.View;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragmentAdapter extends
        RecyclerView.Adapter<HomeFragmentAdapter.MyViewHolder>{


    private static final String TAG = "MyRecyclerAdapter";
    Context context;
    Meal meal = new Meal(1,"","","","","");
    List<Meal> mealList=new ArrayList<Meal>();

    //Button btnAdd;


    //OnProductsClickListener onProductsClickListener;

    public HomeFragmentAdapter(Context context,
              List<Meal> mealList) {
        this.context = context;
        this.mealList = mealList;
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

        //meal=mealList.get(position);
        holder.nameView.setText(mealList.get(position).getName());
        Glide.with(context).load(mealList.get(position).getThumbnail())
                .into(holder.mealImg);

//        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i(TAG, "btnAdd: "+ "added");
//                onProductsClickListener.onProductClick(product);
//            }
//        });


    }


    @Override
    public int getItemCount() {
        return mealList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nameView;
        ImageView mealImg;

        //public Button btnAdd;
        public MyViewHolder(View v) {
            super(v);
            nameView=v.findViewById(R.id.wrapped_meal_name);
            mealImg=v.findViewById(R.id.wrapped_meal_img);
        }

    }
}

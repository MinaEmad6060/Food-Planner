package com.example.foodplanner.HomeScreen.View;

import android.annotation.SuppressLint;
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
import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Plans.View.Plan.OnAddPlanMealListener;
import com.example.foodplanner.Plans.View.Plan.OnRemovePlanMealListener;
import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;

public class HomeCategoryAdapter extends
        RecyclerView.Adapter<HomeCategoryAdapter.MyViewHolder>{


    HomeFragmentInter homeFragmentInter;
    private static final String TAG = "MyRecyclerAdapter";
    private static final String TAG2 = "FPR";

    Context context;
//    Meal meal = new Meal("","","","","","");
    List<Meal> mealList=new ArrayList<Meal>();

    //click on meal
    OnClickMealInter onClickMealInter;

    OnAddMealListener onAddMealListener;

    OnAddPlanMealListener onAddPlanMealListener;
    OnRemovePlanMealListener onRemovePlanMealListener;

    char favOrPlan;



    public HomeCategoryAdapter(Context context,
                               List<Meal> mealList,
                               HomeFragmentInter homeFragmentInter,
                               OnAddMealListener onAddMealListener) {
        this.context = context;
        this.mealList = mealList;
        this.homeFragmentInter=homeFragmentInter;
        this.onAddMealListener=onAddMealListener;
        //add to fav
        favOrPlan='f';
    }
    public HomeCategoryAdapter(Context context,
                               List<Meal> mealList,
                               HomeFragmentInter homeFragmentInter,
                               OnAddPlanMealListener onAddPlanMealListener) {
        this.context = context;
        this.mealList = mealList;
        this.homeFragmentInter=homeFragmentInter;
        this.onAddPlanMealListener=onAddPlanMealListener;
        //add to plan
        favOrPlan='p';
    }

    public HomeCategoryAdapter(Context context,
                               List<Meal> mealList,
                               HomeFragmentInter homeFragmentInter,
                               OnRemovePlanMealListener onRemovePlanMealListener) {
        this.context = context;
        this.mealList = mealList;
        this.homeFragmentInter=homeFragmentInter;
        this.onRemovePlanMealListener=onRemovePlanMealListener;
        //add to plan
        favOrPlan='r';
    }

    public void setMyList(List<Meal> myList) {
        this.mealList = myList;
    }

    //click on meal
    public void setClickMeal(OnClickMealInter onClickMealInter){
        this.onClickMealInter=onClickMealInter;
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
        Meal meal=mealList.get(position);
        Log.i(TAG, "position: "+position);
        Log.i(TAG, "name[0]: "+mealList.get(position).getName());
        holder.nameView.setText(mealList.get(position).getName());
        Glide.with(context).load(mealList.get(position).getThumbnail())
                .into(holder.mealImg);

        Log.i(TAG2, "test FPR : "+ favOrPlan);
            if(favOrPlan=='f'){
                holder.btnAdd.setText("Like It");
            }else if(favOrPlan=='p'){
                holder.btnAdd.setText("Add");
            }else if(favOrPlan=='r'){
                holder.btnAdd.setText("Remove");
            }


        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if(favOrPlan=='f'){
                    onAddMealListener.onMealClick(meal);
                }else if(favOrPlan=='p'){
                    onAddPlanMealListener.onPlanMealClick(meal,"");
                }else if(favOrPlan=='r'){
                    onRemovePlanMealListener.onRemoveFavClick(meal);
                }
            }
        });
    }



    public Meal getItem (int position){
        return mealList.get(position);
    }


    @Override
    public int getItemCount() {
        return mealList.size();
    }


    //click on meal
    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView nameView;
        ImageView mealImg;

        Button btnAdd;
        public MyViewHolder(View v) {
            super(v);
            nameView=v.findViewById(R.id.wrapped_meal_name);
            mealImg=v.findViewById(R.id.wrapped_meal_img);
            btnAdd=v.findViewById(R.id.btn_add);
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

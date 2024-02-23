package com.example.foodplanner.HomeScreen.View;

import static com.example.foodplanner.HomeScreen.View.HomeFragment.userName;
import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
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
        //remove from plan
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
            if((favOrPlan=='f')&&(!userName.equals(""))){
                holder.btnAdd.setText("Like It");
            }else if((favOrPlan=='p')&&(!userName.equals(""))){
                holder.btnAdd.setText("Add");
            }else if((favOrPlan=='r')&&(!userName.equals(""))){
                holder.btnAdd.setText("Remove");
            }

        holder.btnAdd.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                if((favOrPlan=='f')&&(!userName.equals(""))){
                    onAddMealListener.onMealClick(meal);
                }else if((favOrPlan=='p')&&(!userName.equals(""))){
                    onAddPlanMealListener.onPlanMealClick(meal,"");
                }else if((favOrPlan=='r')&&(!userName.equals(""))){
                    onRemovePlanMealListener.onRemovePlanMealClick(meal);
                }else{
                    Toast.makeText(context, "Login and try again", Toast.LENGTH_SHORT).show();
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
        @Override
        public void onClick(View v) {
            onClickMealInter.onClick(v,getAdapterPosition());
        }
    }
}

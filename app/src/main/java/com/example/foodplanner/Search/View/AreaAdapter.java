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
import com.example.foodplanner.Model.Area;
import com.example.foodplanner.Model.Category;
import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;

public class AreaAdapter extends
        RecyclerView.Adapter<AreaAdapter.MyViewHolder>{


    private static final String TAG = "MyRecyclerAdapter";
    Context context;

    List<Area> areaList =new ArrayList<Area>();


    public AreaAdapter(Context context,
                       List<Area> areaList) {
        this.context = context;
        this.areaList = areaList;
        //mealList=new ArrayList<Meal>();
    }

    public void setMyList(List<Area> areaList) {
        this.areaList = areaList;
    }

    @NonNull
    @Override
    public AreaAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = (LayoutInflater)
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.search_card,parent,false);
        AreaAdapter.MyViewHolder myViewHolder = new AreaAdapter.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AreaAdapter.MyViewHolder holder, int position) {
        Log.i(TAG, "position: "+position);
        Log.i(TAG, "name[0]: "+ areaList.get(position).getAreaName());
        holder.nameView.setText(areaList.get(position).getAreaName());
        holder.worldImg.setImageResource(R.drawable.world);
    }


    @Override
    public int getItemCount() {
        return areaList.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView nameView;
        ImageView worldImg;

        //public Button btnAdd;
        public MyViewHolder(View v) {
            super(v);
            nameView=v.findViewById(R.id.search_name);
            worldImg=v.findViewById(R.id.search_img);
        }
    }
}


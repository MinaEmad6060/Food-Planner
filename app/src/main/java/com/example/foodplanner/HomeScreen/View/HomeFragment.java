package com.example.foodplanner.HomeScreen.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.DetailsOfMealActivity;
import com.example.foodplanner.HomeScreen.Presenter.HomeScreenPresenter;
import com.example.foodplanner.HomeScreen.Presenter.HomeScreenPresenterInter;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealRepository;
import com.example.foodplanner.R;
import com.example.foodplanner.Search.View.Category.MealsOfCategoryActivity;
import com.example.foodplanner.db.FavLocalDataSource;
import com.example.foodplanner.network.MealsRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment
            implements HomeFragmentInter{

    //click on meal
    public static final String EXTRA_MEAL = "mealTag";
    RecyclerView chickenRecyclerView;
    RecyclerView beefRecyclerView;
    RecyclerView seaFoodRecyclerView;

    TextView randomMealName;

    View viewFrag;
    ImageView randomMealImg;

    HomeScreenPresenterInter homeScreenPresenterInter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        randomMealName=view.findViewById(R.id.random_name);
        randomMealImg=view.findViewById(R.id.random_img);
        viewFrag=view;

        chickenRecyclerView=view.findViewById(R.id.Chicken_View);
        beefRecyclerView=view.findViewById(R.id.Beef_view);
        seaFoodRecyclerView=view.findViewById(R.id.SeaFood_view);

        homeScreenPresenterInter = new HomeScreenPresenter(
                this,
                MealRepository.getInstance(
                        MealsRemoteDataSource.getInstance()
                        , FavLocalDataSource.getInstance(viewFrag.getContext())));

        homeScreenPresenterInter.getRandomMealPres();
        homeScreenPresenterInter.getMealsOfCategoryPres("Chicken");
        homeScreenPresenterInter.getMealsOfCategoryPres("Beef");
        homeScreenPresenterInter.getMealsOfCategoryPres("Seafood");
    }

    @Override
    public void showChickenCategory(List<Meal> meals) {
        setRecyclerViewAdpter(chickenRecyclerView,meals);
    }

    @Override
    public void showBeefCategory(List<Meal> meals) {
        setRecyclerViewAdpter(beefRecyclerView,meals);
    }

    @Override
    public void showSeaFoodCategory(List<Meal> meals) {
        setRecyclerViewAdpter(seaFoodRecyclerView,meals);
    }



    @Override
    public void showRandomMeal(List<Meal> meals) {
        randomMealName.setText(meals.get(0).getName());
            Glide.with(viewFrag.getContext()).load(meals.get(0).getThumbnail())
                    .into(randomMealImg);
    }

    @Override
    public void showErr(String err) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(homeActivity);
//        builder.setMessage(err).setTitle("An Error Occurred");
//        AlertDialog dialog = builder.create();
//        dialog.show();
    }

    @Override
    public void onMealClick(Meal meal) {
        homeScreenPresenterInter.addFavMeal(meal);
    }


    public void setRecyclerViewAdpter(RecyclerView recyclerView, List<Meal> meals){
        LinearLayoutManager linearManager = new LinearLayoutManager(viewFrag.getContext());
        linearManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        HomeCategoryAdapter homeCategoryAdapter =
                new HomeCategoryAdapter(viewFrag.getContext(), new ArrayList<>(),this);
        recyclerView.setLayoutManager(linearManager);
        recyclerView.setAdapter(homeCategoryAdapter);

        //click on meal
        homeCategoryAdapter.setClickMeal((view1, position) -> {
            String mealName = homeCategoryAdapter.getItem(position).getName();
            Intent intent = new Intent(viewFrag.getContext(),
                    DetailsOfMealActivity.class);
            intent.putExtra(EXTRA_MEAL,mealName);
            startActivity(intent);
        });

        homeCategoryAdapter.setMyList(meals);
        homeCategoryAdapter.notifyDataSetChanged();
    }
}
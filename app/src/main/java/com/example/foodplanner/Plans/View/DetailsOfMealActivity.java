package com.example.foodplanner.Plans.View;

import static com.example.foodplanner.HomeScreen.View.HomeFragment.EXTRA_MEAL;
import static com.example.foodplanner.Search.View.IngredientsActivity.CATEGORY_Ingredients;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.HomeScreen.View.HomeActivity;
import com.example.foodplanner.Model.Ingredient;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealRepository;
import com.example.foodplanner.Plans.Presenter.DetailsOfMealActivityPres;
import com.example.foodplanner.Plans.Presenter.DetailsOfMealActivityPresInter;
import com.example.foodplanner.R;
import com.example.foodplanner.Search.View.Ingredients.MealsOfIngredientActivity;
import com.example.foodplanner.Search.View.IngredientsAdapter;
import com.example.foodplanner.db.FavDB.FavLocalDataSource;
import com.example.foodplanner.network.MealsRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

public class DetailsOfMealActivity extends AppCompatActivity
        implements DetailsOfMealViewInter{



    public static final String VIDEO_URI = "VIDEO";
    ImageView mealImg;


    TextView nameOfMeal;


    TextView areaOfMeal;
    TextView instructionsContent;
    Button btnVideo;

    LinearLayoutManager linearManager;

    DetailsOfMealActivityPresInter detailsOfMealActivityPresInter;

    RecyclerView mealDetailsRecyclerView;

    IngredientsAdapter ingredientsAdapter;

    String videoURI;


    private static final String TAG = "MealDetails";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_of_meal);
        nameOfMeal=findViewById(R.id.meal_name_detailes);
        mealImg=findViewById(R.id.meal_img);
        btnVideo=findViewById(R.id.btn_Video);
        areaOfMeal=findViewById(R.id.Area_text);
        instructionsContent=findViewById(R.id.instructions_content);


        //click on meal
        Intent listenMessage = getIntent();
        String message = listenMessage.getStringExtra(EXTRA_MEAL);
        Log.i(TAG, "message: "+message);



        mealDetailsRecyclerView =findViewById(R.id.ingredients_meal_recyckerView);
        detailsOfMealActivityPresInter = new DetailsOfMealActivityPres(
                this,
                MealRepository.getInstance(
                        MealsRemoteDataSource.getInstance()
                        , FavLocalDataSource.getInstance(this))) {
        };

        detailsOfMealActivityPresInter.getAllMealDetailsPres(message);

        linearManager = new LinearLayoutManager(this);
        linearManager.setOrientation(LinearLayoutManager.VERTICAL);
        ingredientsAdapter =
                new IngredientsAdapter(this, new ArrayList<>());
        mealDetailsRecyclerView.setLayoutManager(linearManager);
        mealDetailsRecyclerView.setAdapter(ingredientsAdapter);
        ingredientsAdapter.setClickMealIngredints((view, position) -> {
            String mealName = ingredientsAdapter.getItemIngredient(position).getIngredientName();
            Intent intent = new Intent(getApplicationContext(),
                    MealsOfIngredientActivity.class);
            intent.putExtra(CATEGORY_Ingredients,mealName);
            startActivity(intent);
        });




        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsOfMealActivity.this,
                        VideoMealActivity.class);
                intent.putExtra(VIDEO_URI,videoURI);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showMealDetails(List<Meal> meals) {
        List<Ingredient> ingredList=new ArrayList<Ingredient>();
        Log.i(TAG, "Ingredient1: "+meals.get(0).getIngredient1());

        nameOfMeal.setText(meals.get(0).getName());
        areaOfMeal.setText(meals.get(0).getArea());
        instructionsContent.setText(meals.get(0).getInstructions());
        videoURI=meals.get(0).getVideoLink();

        Glide.with(this).load(meals.get(0).getThumbnail())
                .into(mealImg);

        ingredList.add(new Ingredient(meals.get(0).getIngredient1()));
        ingredList.add(new Ingredient(meals.get(0).getIngredient2()));
        ingredList.add(new Ingredient(meals.get(0).getIngredient3()));
        ingredList.add(new Ingredient(meals.get(0).getIngredient4()));
        ingredList.add(new Ingredient(meals.get(0).getIngredient5()));
        ingredList.add(new Ingredient(meals.get(0).getIngredient6()));
        ingredList.add(new Ingredient(meals.get(0).getIngredient7()));
        ingredList.add(new Ingredient(meals.get(0).getIngredient8()));
        ingredList.add(new Ingredient(meals.get(0).getIngredient9()));
        ingredList.add(new Ingredient(meals.get(0).getIngredient10()));
        ingredList.add(new Ingredient(meals.get(0).getIngredient11()));
        ingredList.add(new Ingredient(meals.get(0).getIngredient12()));
        ingredList.add(new Ingredient(meals.get(0).getIngredient13()));
        ingredList.add(new Ingredient(meals.get(0).getIngredient14()));
        ingredList.add(new Ingredient(meals.get(0).getIngredient15()));
        ingredList.add(new Ingredient(meals.get(0).getIngredient16()));
        ingredList.add(new Ingredient(meals.get(0).getIngredient17()));
        ingredList.add(new Ingredient(meals.get(0).getIngredient18()));
        ingredList.add(new Ingredient(meals.get(0).getIngredient19()));
        ingredList.add(new Ingredient(meals.get(0).getIngredient20()));

        ingredientsAdapter.setMyList(ingredList);
        ingredientsAdapter.notifyDataSetChanged();

    }
}
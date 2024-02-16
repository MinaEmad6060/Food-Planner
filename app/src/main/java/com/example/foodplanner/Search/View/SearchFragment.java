package com.example.foodplanner.Search.View;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.foodplanner.HomeScreen.View.HomeCategoryAdapter;
import com.example.foodplanner.HomeScreen.View.HomeActivity;
import com.example.foodplanner.HomeScreen.View.HomeFragment;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealRepository;
import com.example.foodplanner.R;
import com.example.foodplanner.Search.Presenter.SearchFragmentPresenter;
import com.example.foodplanner.Search.Presenter.SearchFragmentPresenterInter;
import com.example.foodplanner.db.FavLocalDataSource;
import com.example.foodplanner.network.MealsRemoteDataSource;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchFragment extends Fragment implements SearchViewInter{

    private static final String TAG = "SearchFragment";
    SearchFragmentPresenterInter searchFragmentPresenterInter;

    LinearLayoutManager linearManager;
    LinearLayoutManager linearManagerSearch;

    HomeActivity homeActivity;
    View viewFrag;

    RecyclerView categoriesRecyclerView;

    Chip category;

    Chip area;

    Chip ingredients;


    HomeCategoryAdapter adapter;

    EditText searchEditText;
    List<Meal> searchMeals;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }


    @SuppressLint("CheckResult")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchMeals=new ArrayList<>();
        searchEditText =view.findViewById(R.id.searchEditText);
        category=view.findViewById(R.id.cat_chip);
        area=view.findViewById(R.id.area_chip);
        ingredients =view.findViewById(R.id.ingredients_chip);

        viewFrag=view;

        homeActivity=(HomeActivity)getActivity();

        searchFragmentPresenterInter = new SearchFragmentPresenter(
                this,
                MealRepository.getInstance(
                        MealsRemoteDataSource.getInstance()
                        , FavLocalDataSource.getInstance(viewFrag.getContext())));
        //Name
        categoriesRecyclerView =view.findViewById(R.id.categories_recyclerView);
        linearManagerSearch = new LinearLayoutManager(view.getContext());
        linearManagerSearch.setOrientation(LinearLayoutManager.VERTICAL);
        adapter = new HomeCategoryAdapter(viewFrag.getContext(), new ArrayList<>(),new HomeFragment());
        categoriesRecyclerView.setLayoutManager(linearManagerSearch);
        categoriesRecyclerView.setAdapter(adapter);
        searchFragmentPresenterInter.getSearchMealsPres("");




        //Category
//        linearManager = new LinearLayoutManager(view.getContext());
//        linearManager.setOrientation(LinearLayoutManager.VERTICAL);
//        categoriesAdapter =
//                new CategoriesAdapter(viewFrag.getContext(), new ArrayList<>());
//        categoriesRecyclerView.setLayoutManager(linearManager);
//        categoriesRecyclerView.setAdapter(categoriesAdapter);

        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                searchFragmentPresenterInter.getAllCategoriesPres();
                Intent transferData = new Intent(homeActivity,CategoryActivity.class);
                startActivity(transferData);
            }
        });


        area.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                searchFragmentPresenterInter.getAllCategoriesPres();
                Intent transferData = new Intent(homeActivity,AreaActivity.class);
                startActivity(transferData);
            }
        });


        ingredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent transferData = new Intent(homeActivity,IngredientsActivity.class);
                startActivity(transferData);
            }
        });

        // Creating an observable for text changes in the EditText
        Observable.create((ObservableOnSubscribe<String>) emitter ->
                        searchEditText.addTextChangedListener(new TextWatcher() {
                            @Override
                            public void beforeTextChanged(CharSequence charSequence, int start, int before, int count) {
                                Log.i(TAG, "beforeTextChanged: ");
                                emitter.onNext(charSequence.toString());
                            }

                            @Override
                            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                                emitter.onNext(charSequence.toString());
                                Log.i(TAG, "onTextChanged: ");
                            }

                            @Override
                            public void afterTextChanged(Editable editable) {
                                Log.i(TAG, "afterTextChanged: ");
                            }
                        }))
                .debounce(300, TimeUnit.MILLISECONDS) // Add a debounce to avoid rapid searches
                .map(String::toLowerCase)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(searchTerm -> {
//                    searchFragmentPresenterInter.getSearchMealsPres(searchTerm);
                    List<Meal> filteredNames = new ArrayList<>();
                    Log.i(TAG, "Observer sizeOfsearchMeals: "+searchMeals.size());
                    for (Meal searchMeal : searchMeals) {
                        if (searchMeal.getName().toLowerCase().contains(searchTerm)) {
                            filteredNames.add(searchMeal);
                        }
                    }
//                    searchFragmentPresenterInter.getSearchMealsPres(searchTerm);
                    Log.i(TAG, "sizeOfMeals: "+filteredNames.size());
                    adapter.setMyList(filteredNames);
                    adapter.notifyDataSetChanged();
                },
                err-> Log.i(TAG, "error: ")
                );
    }


    @Override
    public void showSearchMeals(List<Meal> meals) {
        searchMeals=meals;
        Log.i(TAG, "showSearchMeals sizeOfsearchMeals: "+searchMeals.size());
    }
}
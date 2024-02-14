package com.example.foodplanner.Search.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.HomeScreen.Presenter.HomeScreenPresenter;
import com.example.foodplanner.HomeScreen.View.SeaFoodCategoryAdapter;
import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.MealRepository;
import com.example.foodplanner.R;
import com.example.foodplanner.Search.Presenter.SearchFragmentPresenter;
import com.example.foodplanner.Search.Presenter.SearchFragmentPresenterInter;
import com.example.foodplanner.db.FavLocalDataSource;
import com.example.foodplanner.network.MealsRemoteDataSource;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements SearchViewInter{

    private static final String TAG = "SearchFragment";
    SearchFragmentPresenterInter searchFragmentPresenterInter;

    LinearLayoutManager linearManager;

    View viewFrag;

    RecyclerView categoriesRecyclerView;

    CategoriesAdapter categoriesAdapter;
    Chip category;

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


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        category=view.findViewById(R.id.cat_chip);
        viewFrag=view;

        searchFragmentPresenterInter = new SearchFragmentPresenter(
                this,
                MealRepository.getInstance(
                        MealsRemoteDataSource.getInstance()
                        , FavLocalDataSource.getInstance(viewFrag.getContext())));



        categoriesRecyclerView =view.findViewById(R.id.categories_recyclerView);
        linearManager = new LinearLayoutManager(view.getContext());
        linearManager.setOrientation(LinearLayoutManager.VERTICAL);
        categoriesAdapter =
                new CategoriesAdapter(viewFrag.getContext(), new ArrayList<>());
        categoriesRecyclerView.setLayoutManager(linearManager);
        categoriesRecyclerView.setAdapter(categoriesAdapter);

        category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "SearchFragment: selected");
                searchFragmentPresenterInter.getAllCategoriesPres();
            }
        });
    }

    @Override
    public void showCategories(List<Category> categories) {
        categoriesAdapter.setMyList(categories);
        categoriesAdapter.notifyDataSetChanged();
    }
}
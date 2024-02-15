package com.example.foodplanner.Search.View;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.foodplanner.HomeScreen.View.ChickenCategoryAdapter;
import com.example.foodplanner.Model.Category;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.R;
import com.example.foodplanner.Search.Presenter.SearchFragmentPresenterInter;
import com.example.foodplanner.network.CallBackInter;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;

public class CategoryFragment extends Fragment implements CategoryViewInter{

    LinearLayoutManager linearManager;


    View viewFrag;

    RecyclerView categoriesRecyclerView;

    CategoriesAdapter categoriesAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_category, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        categoriesRecyclerView = view.findViewById(R.id.category_recycler);

        //Category
        linearManager = new LinearLayoutManager(view.getContext());
        linearManager.setOrientation(LinearLayoutManager.VERTICAL);
        categoriesAdapter =
                new CategoriesAdapter(viewFrag.getContext(), new ArrayList<>());
        categoriesRecyclerView.setLayoutManager(linearManager);
        categoriesRecyclerView.setAdapter(categoriesAdapter);
    }

    @Override
    public void showCategories(List<Category> categories) {
        categoriesAdapter.setMyList(categories);
        categoriesAdapter.notifyDataSetChanged();
    }
}
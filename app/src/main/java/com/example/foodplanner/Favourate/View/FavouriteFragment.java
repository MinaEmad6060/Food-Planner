package com.example.foodplanner.Favourate.View;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.Favourate.Presenter.FavMealsPresenter;
import com.example.foodplanner.Favourate.Presenter.InterFavMealsPresenter;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealRepository;
import com.example.foodplanner.Model.MealRepositoryInter;
import com.example.foodplanner.R;
import com.example.foodplanner.db.FavLocalDataSource;
import com.example.foodplanner.network.MealsRemoteDataSource;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class FavouriteFragment extends Fragment implements InterFavProductsView{

    private static final String TAG = "FavProductsActivity";
    View viewFrag;
    RecyclerView recyclerView;
    LinearLayoutManager linearManager;

    FavMealsAdapter favMealsAdapter;
    MealRepositoryInter mealRepositoryInter;

    InterFavMealsPresenter interFavMealsPresenter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.Fav_Recycler_List);
        viewFrag=view;
        linearManager = new LinearLayoutManager(viewFrag.getContext());
        linearManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        mealRepositoryInter = MealRepository.getInstance(
                MealsRemoteDataSource.getInstance(),
                FavLocalDataSource.getInstance(viewFrag.getContext()));
        interFavMealsPresenter = new FavMealsPresenter(mealRepositoryInter);


        favMealsAdapter =
                new FavMealsAdapter(
                        viewFrag.getContext(), new ArrayList<>());
        recyclerView.setLayoutManager(linearManager);
        recyclerView.setAdapter(favMealsAdapter);


        showData(interFavMealsPresenter.getStoredDataDB());
    }

    //    @Override
//    public void onFavClick(Product product) {
//
//    }

    @SuppressLint({"CheckResult", "NotifyDataSetChanged"})
    @Override
    public void showData(Flowable<List<Meal>> meals) {
        meals.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        productList ->{
                            favMealsAdapter.setMyList(productList);
                            favMealsAdapter.notifyDataSetChanged();
                        }
                );
    }

    @Override
    public void onFavClick(Meal meal) {
        interFavMealsPresenter.removeFavProduct(meal);
    }
}
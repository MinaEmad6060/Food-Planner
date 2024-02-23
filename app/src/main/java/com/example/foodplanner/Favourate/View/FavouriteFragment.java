package com.example.foodplanner.Favourate.View;

import static com.example.foodplanner.Online.Presenter.LoginFragmentPres.SHARED_PREF;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import android.widget.Toast;
import com.example.foodplanner.Favourate.Presenter.FavMealsPresenter;
import com.example.foodplanner.Favourate.Presenter.InterFavMealsPresenter;
import com.example.foodplanner.HomeScreen.View.HomeActivity;
import com.example.foodplanner.Model.Meal;
import com.example.foodplanner.Model.MealRepository;
import com.example.foodplanner.Model.MealRepositoryInter;
import com.example.foodplanner.R;
import com.example.foodplanner.db.FavDB.FavLocalDataSource;
import com.example.foodplanner.network.MealsRemoteDataSource;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class FavouriteFragment extends Fragment
        implements InterFavMealsView, OnRemoveMealClickListener {
    private static final String TAG = "FavProductsActivity";
    View viewFrag;
    RecyclerView recyclerView;
    LinearLayoutManager linearManager;
    FavMealsAdapter favMealsAdapter;
    MealRepositoryInter mealRepositoryInter;
    InterFavMealsPresenter interFavMealsPresenter;
    HomeActivity homeActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homeActivity=(HomeActivity)getActivity();
        SharedPreferences sharedPreferences =
                homeActivity.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        String userName = sharedPreferences.getString("name","");
        Log.i(TAG, "userName: "+userName);
        if(userName.equals("")){
            Toast.makeText(homeActivity, "Login to access Favourite meals",
                    Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(homeActivity.getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favourite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.Fav_Recycler_List);
        viewFrag=view;
        linearManager = new LinearLayoutManager(viewFrag.getContext());
        linearManager.setOrientation(LinearLayoutManager.VERTICAL);
        mealRepositoryInter = MealRepository.getFavInstance(
                MealsRemoteDataSource.getInstance(),
                FavLocalDataSource.getInstance(viewFrag.getContext()));
        interFavMealsPresenter = new FavMealsPresenter(mealRepositoryInter);
        favMealsAdapter =
                new FavMealsAdapter(
                        viewFrag.getContext(), new ArrayList<>(),this);
        recyclerView.setLayoutManager(linearManager);
        recyclerView.setAdapter(favMealsAdapter);
        showData(interFavMealsPresenter.getStoredDataDB());
    }

    @SuppressLint({"CheckResult", "NotifyDataSetChanged"})
    @Override
    public void showData(Flowable<List<Meal>> meals) {
        meals.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        mealList ->{
                            Log.i(TAG, "succes: ");
                            favMealsAdapter.setMyList(mealList);
                            favMealsAdapter.notifyDataSetChanged();
                        },
                        err -> Log.i(TAG, "error: ")
                );
    }

    @Override
    public void onRemoveFavClick(Meal meal) {
        Toast.makeText(homeActivity, "Remove from favourite!", Toast.LENGTH_SHORT).show();
        interFavMealsPresenter.removeFavMeal(meal);
    }
}
package com.example.foodplanner.HomeScreen.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.foodplanner.Favourate.View.FavouriteFragment;
import com.example.foodplanner.Plans.View.PlanOfWeekFragment;
import com.example.foodplanner.R;
import com.example.foodplanner.Search.View.SearchFragment;
import com.example.foodplanner.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    HomeFragment homeFragment;
    ActivityHomeBinding activityMainBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if(savedInstanceState == null){
            homeFragment = new HomeFragment();
            FragmentManager fragManager = getSupportFragmentManager();
            FragmentTransaction transFragment = fragManager.beginTransaction();
            transFragment.add(R.id.home_Fragment, homeFragment, "DYNAMIC");
            transFragment.commit();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        activityMainBinding=ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        replacementFragment(new HomeFragment());

        activityMainBinding.buttomNav.setOnItemSelectedListener(item -> {

            if(item.getItemId()==R.id.homeFragment){
                Log.i(TAG, "homeFrag: ");
                replacementFragment(new HomeFragment());
            }else if(item.getItemId()==R.id.planOfWeekFragment){
                replacementFragment(new PlanOfWeekFragment());
            }else if(item.getItemId()==R.id.favouriteFragment){
                replacementFragment(new FavouriteFragment());
            }else if(item.getItemId()==R.id.searchFragment){
                replacementFragment(new SearchFragment());
            }

            return true;
        });

    }

    private void replacementFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.home_Fragment,fragment);
        fragmentTransaction.commit();
    }
}
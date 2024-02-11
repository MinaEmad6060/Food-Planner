package com.example.foodplanner.ConnectOnline.View;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.foodplanner.HomeScreen.View.HomeActivity;
import com.example.foodplanner.R;


public class LoginFragment extends Fragment {

    Button btnGuest;
    TextView signUp;

    StartActivity startActivity;

//    Button btnGoogleLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        signUp=view.findViewById(R.id.Sign_Up);
        btnGuest=view.findViewById(R.id.btn_guest);
        startActivity=(StartActivity)getActivity();
//        btnGoogleLogin=view.findViewById(R.id.btn_Google);
//        btnGoogleLogin.setOnClickListener(v -> {
//            NavDirections action = LoginFragmentDirections.actionLoginFragmentToSignUpFragment();
//            Navigation.findNavController(v).navigate(action);
//        });

        signUp.setOnClickListener(v -> {
            NavDirections action = LoginFragmentDirections.actionLoginFragmentToSignUpFragment();
            Navigation.findNavController(v).navigate(action);
        });

        btnGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveToHome = new Intent(startActivity, HomeActivity.class);
                startActivity(moveToHome);
            }
        });





    }
}
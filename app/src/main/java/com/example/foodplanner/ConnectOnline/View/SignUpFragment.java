package com.example.foodplanner.ConnectOnline.View;

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

import com.example.foodplanner.R;

public class SignUpFragment extends Fragment {


    Button btnSignUp;
//    Button btnGoogleSignUp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnSignUp=view.findViewById(R.id.btn_signup);
        btnSignUp.setOnClickListener(v -> {
            NavDirections action = SignUpFragmentDirections.actionSignUpFragmentToLoginFragment();
            Navigation.findNavController(v).navigate(action);
        });
    }
}
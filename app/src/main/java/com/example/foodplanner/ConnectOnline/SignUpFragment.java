package com.example.foodplanner.ConnectOnline;

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
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpFragment extends Fragment {
    private EditText email;
    private EditText password;

    private FirebaseAuth mAuth;
    StartActivity startActivity;
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

        startActivity = (StartActivity) getActivity();

        mAuth = FirebaseAuth.getInstance();

        email=view.findViewById(R.id.SignUP_Email);
        password=view.findViewById(R.id.SignUP_Password);
        btnSignUp=view.findViewById(R.id.btn_signup);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String user = email.getText().toString().trim();
                String pass = password.getText().toString().trim();

                if (user.isEmpty()){
                    email.setError("Email cannot be empty");
                }else if (pass.isEmpty()) {
                    password.setError("Password cannot be empty");
                }else{
                    mAuth.createUserWithEmailAndPassword(user, pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(startActivity, "SignUp Successful", Toast.LENGTH_SHORT).show();
                                        NavDirections action = SignUpFragmentDirections.actionSignUpFragmentToLoginFragment();
                                        Navigation.findNavController(view).navigate(action); }
                                    else {
                                        Toast.makeText(startActivity, "SignUp Failed", Integer.parseInt(task.getException().getMessage())).show();
                                    }
                                }
                            });
                }}});


    }
}